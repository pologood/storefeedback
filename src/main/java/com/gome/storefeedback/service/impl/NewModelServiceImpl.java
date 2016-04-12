package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.dao.NewModelDao;
import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.entity.NewCategoryRelKey;
import com.gome.storefeedback.entity.NewMessagePushRecord;
import com.gome.storefeedback.entity.NewModel;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.FeedbackMessageSender;
import com.gome.storefeedback.jms.SapFeedbackPushSender;
import com.gome.storefeedback.service.CategoryPositionService;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.NewMessagePushRecordService;
import com.gome.storefeedback.service.NewModelService;
import com.gome.storefeedback.util.CollectionUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;
@Service("newModelService")
public class NewModelServiceImpl implements NewModelService {
	@Resource
    private EmployeeRemoteService employeeRemoteService;
    @Resource
    private FeedbackMessageSender feedbackMessageSender;
    @Resource
    private NewMessagePushRecordService newMessagePushRecordService;
    
    @Resource
    private CategoryPositionService categoryPositionService;
    @Resource
    private  SapFeedbackPushSender sapFeedbackPushSender;
   
    private NewModelDao newModelDao;
	
	@Autowired
	public void setNewModelDao(@Qualifier("newModelDao")NewModelDao newModelDao) {
		this.newModelDao = newModelDao;
	}

	public NewModelDao getNewModelDao() {
		return newModelDao;
	}
	
	
	@Override
	public void deleteByPrimaryKey(String id) throws BaseException {
		this.newModelDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public void insert(NewModel record) throws BaseException {
		
		  Map<String, Object> pmap = new HashMap<String, Object>();
		  pmap.put("categoryCode", record.getCategoryCode());
		List<CategoryPosition> list   = categoryPositionService.findCategoryPositionAll(pmap);
		if(list != null){
			for(CategoryPosition cate : list){
				List<Employee> employees = null;
				try {
					employees = this.employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(record.getReportCompanyId(), cate.getPositionCode(), null);
					employees = new ArrayList<Employee>(new HashSet<Employee>(employees));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				/*Employee ee = new Employee();
				ee.setEmployeeId(record.getReportEmployeeId());
				ee.setEmployeeName(record.getReportEmployeeName());
				ee.setCompanyId("10000");
				ee.setId("22");
				employees.add(ee);*/
				List<NewMessagePushRecord> sendMessages = null;
				if (employees != null && employees.size() > 0) {
					List<NewCategoryRelKey> newCategoryRecievers = new ArrayList<NewCategoryRelKey>();
					sendMessages = new ArrayList<NewMessagePushRecord>();
					for (Employee employee : employees) {
						NewMessagePushRecord pushRecord = getSendMessage(record, employee);
						newMessagePushRecordService.insertNewMessagePushRecord(pushRecord);//存储推送信息
						sendMessages.add(pushRecord);//组装推送信息
					}
					
				}
				//推送新品类
				if (!CollectionUtil.isEmpty(sendMessages)) {
					for (NewMessagePushRecord sendMessage : sendMessages) {
						
				        Map<String,Object> map = new HashMap<String,Object>();
				        map.put("companyId", "10000");
				        map.put("employeeId", sendMessage.getNotificationEmployeeId());
				        map.put("title",sendMessage.getTitle());
				        map.put("content",sendMessage.getContent());
				        map.put("type", sendMessage.getType()+"");
				        map.put("sender", BusinessGlossary.APP_STOREFEEDBACK);
				        
				        sapFeedbackPushSender.send(JsonUtil.javaObjectToJsonString(map));
						//this.feedbackMessageSender.send(JsonUtil.javaObjectToJsonString(sendMessage));
					}
				}
			}
			this.newModelDao.insert(record);
		}else{
			throw new BaseException("请对品类："+record.getCategoryDesc()+" 设置处理人职位！");
		}
		
		
	}


	@Override
	public NewModel selectByPrimaryKey(String id) throws BaseException {
		return this.newModelDao.selectByPrimaryKey(id);
	}


	@Override
	public void updateByPrimaryKey(NewModel record) throws BaseException {
		this.newModelDao.updateByPrimaryKey(record);
		
	}

	@Override
	public int checkNewCategory(NewModel record) throws BaseException {
		return this.newModelDao.checkNewCategory(record);
	}

	@Override
	public int checkNewModel(NewModel record) throws BaseException {
		return this.newModelDao.checkNewModel(record);
	}

	@Override
	public void updateByPrimaryKeySelective(NewModel record)
			throws BaseException {
		this.newModelDao.updateByPrimaryKeySelective(record);
		
	}
	@Override
	public List<NewModel> selectList(Map<String, Object> cate)
			throws BaseException {
		 return this.newModelDao.selectList(cate);
	}
	

	 private static NewCategoryRelKey getFeedbackReciever(NewModel newCategory, Employee employee) {
		  NewCategoryRelKey categoryRel = new NewCategoryRelKey();
		  categoryRel.setId(newCategory.getId());
		  categoryRel.setHandleEmpId(employee.getId());
	        return categoryRel;
	    }

	    private static NewMessagePushRecord getSendMessage(NewModel feedback, Employee employee) {
	    	NewMessagePushRecord sendMessage = new NewMessagePushRecord();
	    	 sendMessage.setId(UUIDUtil.getUUID());
		        sendMessage.setContent("您有一条新型号提报需要处理");
		        sendMessage.setType("110");
		        sendMessage.setTitle("新型号提报通知");
		        sendMessage.setNotificationId(employee.getId());
		        sendMessage.setNotificationEmployeeId(employee.getEmployeeId());
		        sendMessage.setNotificationEmployeeName(employee.getEmployeeName());
		        sendMessage.setReportId(feedback.getId());
		        sendMessage.setReportEmpId(feedback.getReportEmployeeId());
		        sendMessage.setReportEmpName(feedback.getReportEmployeeName());
		        sendMessage.setNotificationTime(new Date());
		        return sendMessage;
	    }

}
