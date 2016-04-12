package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.constant.NewCateGoryCnstant;
import com.gome.storefeedback.controller.app.NewCategoryReportContorller;
import com.gome.storefeedback.dao.NewCategoryDao;
import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.entity.NewCategory;
import com.gome.storefeedback.entity.NewCategoryRelKey;
import com.gome.storefeedback.entity.NewMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.FeedbackMessageSender;
import com.gome.storefeedback.jms.SapFeedbackPushSender;
import com.gome.storefeedback.service.CategoryPositionService;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.NewMessagePushRecordService;
import com.gome.storefeedback.util.CollectionUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;
@Service("newCategoryService")
public class NewCategoryServiceImpl implements com.gome.storefeedback.service.NewCategoryService {
	 private static final Logger logger = LoggerFactory.getLogger(NewCategoryServiceImpl.class);
	
	 @Resource
    private EmployeeRemoteService employeeRemoteService;
    @Resource
    private FeedbackMessageSender feedbackMessageSender;
    @Resource
    private NewMessagePushRecordService newMessagePushRecordService;
    @Resource
    private  SapFeedbackPushSender sapFeedbackPushSender;
    @Resource
    private CategoryPositionService categoryPositionService;
  
    private NewCategoryDao newCategoryDao;
	
	
	public NewCategoryDao getNewCategoryDao() {
		return newCategoryDao;
	}
	
	@Autowired
    public void setNewCategoryDao(@Qualifier("newCategoryDao") NewCategoryDao newCategoryDao) {
        this.newCategoryDao = newCategoryDao;
    }
 
	@Override
	public void deleteByPrimaryKey(String id) throws BaseException {
		this.newCategoryDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public void insertCategory(NewCategory record) throws BaseException {
		  Map<String, Object> pmap = new HashMap<String, Object>();
		  String code = "";
		  if(StringUtils.isNotBlank(record.getCategoryCode())){
			  code = record.getCategoryCode();
		  }else{
			  code = NewCateGoryCnstant.CATEGORY_CODE_000000;
		  }
		  pmap.put("categoryCode", code);
		List<CategoryPosition> list   = categoryPositionService.findCategoryPositionAll(pmap);
		if(list != null && list.size() > 0){
			for(CategoryPosition cate : list){
				List<Employee> employees = null;
				try {
					employees = this.employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(record.getReportCompanyId(), cate.getPositionCode(), null);
					employees = new ArrayList<Employee>(new HashSet<Employee>(employees));
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<NewMessagePushRecord> sendMessages = null;
				/*Employee ee = new Employee();
				ee.setEmployeeId("00000333");
				ee.setEmployeeName("孙兰兰");
				ee.setCompanyId("10000");
				ee.setId("22");
				employees.add(ee);*/
				
				if (employees != null && employees.size() > 0) {
					//List<NewCategoryRelKey> newCategoryRecievers = new ArrayList<NewCategoryRelKey>();
					sendMessages = new ArrayList<NewMessagePushRecord>();
					for (Employee employee : employees) {
						//newCategoryRecievers.add(getFeedbackReciever(record, employee));
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
			this.newCategoryDao.insertCategory(record);
		}else{
			logger.error("请对品类："+record.getCategoryDesc()+" 设置处理人职位！");
		}
	}


	@Override
	public NewCategory selectByPrimaryKey(String id) throws BaseException {
		return this.newCategoryDao.selectByPrimaryKey(id);
	}


	@Override
	public void updateByPrimaryKey(NewCategory record) throws BaseException {
		this.newCategoryDao.updateByPrimaryKey(record);
		
	}

	@Override
	public int checkNewCategory(NewCategory record) throws BaseException {
		return this.newCategoryDao.checkNewCategory(record);
	}

	@Override
	public int checkNewModel(NewCategory record) throws BaseException {
		return this.newCategoryDao.checkNewModel(record);
	}

	@Override
	public void updateByPrimaryKeySelective(NewCategory record)
			throws BaseException {
		this.newCategoryDao.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public List<NewCategory> selectList(Map<String, Object> cate)
			throws BaseException {
		 return this.newCategoryDao.selectList(cate);
	}
	
	
	 private static NewCategoryRelKey getFeedbackReciever(NewCategory newCategory, Employee employee) {
		  NewCategoryRelKey categoryRel = new NewCategoryRelKey();
		  categoryRel.setId(newCategory.getId());
		  categoryRel.setHandleEmpId(employee.getId());
	        return categoryRel;
	    }

	    private static NewMessagePushRecord getSendMessage(NewCategory feedback, Employee employee) {
	    	NewMessagePushRecord sendMessage = new NewMessagePushRecord();
	        sendMessage.setId(UUIDUtil.getUUID());
	        sendMessage.setContent("您有一条新品类提报需要处理");
	        sendMessage.setType("110");
	        sendMessage.setTitle("新品类提报通知");
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
