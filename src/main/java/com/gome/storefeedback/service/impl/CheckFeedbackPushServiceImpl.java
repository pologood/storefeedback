package com.gome.storefeedback.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.dao.SapFeedbackDicaiDao;
import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.entity.SapFeedbackCheckPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.SapFeedbackPushSender;
import com.gome.storefeedback.service.CheckFeedbackPushService;
import com.gome.storefeedback.service.CheckSapFeedbackService;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackCheckConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpService;
import com.gome.storefeedback.service.SapFeedbackCheckPushService;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.util.DateTimeUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月29日上午11:12:30
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("checkFeedbackPushService")
public class CheckFeedbackPushServiceImpl implements CheckFeedbackPushService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackPushServiceImpl.class);
    private static final String GSM_TYPE_112 = "112";
    private static final String GSM_MSG = "缺断货考核通知";
    
    // private static final int GMZB_LEADER = 11111100;// 总部领导
     private static final int GMZB_BUSINESS = 11001110;// 总部业务
    // private static final int GMFB_LEADER = 10110100;// 分部领导
     private static final int GMFB_BUSINESS = 10100001;// 分部业务
	private static final String String = null;
    
    @Resource
    private SapFeedbackService sapFeedbackService;
    @Resource
    private SapFeedbackDicaiDao sapFeedbackDicaiDao;
    @Resource
    private FeedbackPushCategoryConfigService feedbackPushCategoryConfigService;
    @Resource
    private FeedbackPushCategoryPositionConfigService feedbackPushCategoryPositionConfigService;
    @Resource
    private EmployeeRemoteService employeeRemoteService;
    @Resource
    private SapFeedbackHandlerService sapFeedbackHandlerService;
    @Resource
    private SapFeedbackPushSender SapFeedbackCheckPushSender;
    @Resource
    private SapFeedbackCheckPushService sapFeedbackCheckPushService;
    @Resource
    private SapFeedbackCheckEmpService sapFeedbackCheckEmpService;
    /**
     * 缺断货考核配置
     */
    @Resource
    FeedbackCheckConfigService fFeedbackCheckConfigService;
    
    @Resource
    private CheckSapFeedbackService checkSapFeedbackService;

	/**
     * 缺断货 考核推送信息----组装--推送给MQ
     * @param empId
     * @param content
     * @throws BaseException
     */
    private void sendMessage(String empId, String content) throws BaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", "10000");
        map.put("employeeId", empId);
        map.put("title", GSM_MSG);
        map.put("content", content);
        map.put("type", GSM_TYPE_112);
        map.put("sender", BusinessGlossary.APP_STOREFEEDBACK);
        String msg = JsonUtil.javaObjectToJsonString(map);
        SapFeedbackCheckPushSender.send(msg);
        logger.debug("---------> 缺断货信息推送:" + msg);
    }

    
    @Override
    public void pushCheckFeedback(Date pushDate) throws BaseException {
//    	clearCheckEmp();
//    	insertCheckEmp();
    	pushFeedbackCheckBusiness(pushDate);
    	
    }
    private void pushFeedbackCheckBusiness(Date pushDate) throws BaseException{
    	Map<String,Object> inMap = new HashMap<String,Object>();
    	inMap.put("dataDate", getLastFridayDate());
    	List<Map<String, Object>> empMapList = checkSapFeedbackService.getCheckByEmp(inMap);
    	List<SapFeedbackCheckPush> SapFeedbackCheckPushList = new ArrayList<SapFeedbackCheckPush>();
    	for (Map<String, Object> empMap : empMapList) {
    		String empNumber = (String)empMap.get("empNumber");
    		Integer roleId = (Integer)empMap.get("roleId");
    		
    		Date pushTimeStamp = new Date();
    		SapFeedbackCheckPush SapFeedbackCheckPush = new SapFeedbackCheckPush();
    		SapFeedbackCheckPush.setId(UUIDUtil.getUUID());
//		SapFeedbackCheckPush.setPushCategory(checkConfig.getCategoryCode());
    		SapFeedbackCheckPush.setPushContent(DateTimeUtil.formatDate(pushDate) + "日，您有"+empMap.get("checkCount")+"条断货考核信息需要处理");
    		SapFeedbackCheckPush.setPushDataDate(pushDate);
    		SapFeedbackCheckPush.setPushEmpId(empNumber);
    		SapFeedbackCheckPush.setPushEmpName(empNumber);
    		SapFeedbackCheckPush.setPushEmpNumber(empNumber);

    		SapFeedbackCheckPush.setPushTime(pushTimeStamp);

    		SapFeedbackCheckPush.setRoleId(roleId);
   
    		SapFeedbackCheckPushList.add(SapFeedbackCheckPush);
    		sendMessage(empNumber,SapFeedbackCheckPush.getPushContent());
    		
		}
    	if(SapFeedbackCheckPushList != null && SapFeedbackCheckPushList.size() > 0){
    		sapFeedbackCheckPushService.batchSapFeedbackCheckPush(SapFeedbackCheckPushList);
    	}
    	
    	
    }

    /*private void pushFeedbackCheckBusiness(Date pushDate) throws BaseException {
        List<FeedbackCheckConfig> checkList = fFeedbackCheckConfigService.find(null);
        if (checkList != null && checkList.size() > 0) {
            for (FeedbackCheckConfig checkConfig : checkList) {
                Date now = null;
                try {
                    now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(pushDate) + " 00:00:00");
                } catch (ParseException e1) {
                    // 日期格式化错误
                    // return;
                    throw new BaseException(e1);
                }
                Map<String, Object> jMap = new HashMap<String, Object>();
                jMap.put("dataDate", now);
                jMap.put("categoryCode", checkConfig.getCategoryCode());
                jMap.put("brandCode", checkConfig.getBrandCode());
                jMap.put("divisionCode", checkConfig.getOrgNumber());
                List chekFeedbackList  = checkSapFeedbackService.findSapFeedback(jMap);
				if (chekFeedbackList != null && chekFeedbackList.size() > 0) {
					try {
						List<SapFeedbackCheckPush> SapFeedbackCheckPushList = new ArrayList<SapFeedbackCheckPush>();
						Date pushTimeStamp = new Date();
						SapFeedbackCheckPush SapFeedbackCheckPush = new SapFeedbackCheckPush();
						SapFeedbackCheckPush.setId(UUIDUtil.getUUID());
						SapFeedbackCheckPush.setPushCategory(checkConfig.getCategoryCode());
						SapFeedbackCheckPush.setPushContent(DateTimeUtil.formatDate(pushDate) + "日，您有"+chekFeedbackList.size()+"条断货考核信息需要处理,"+ "   品牌： "	+ checkConfig.getBrandName());
						SapFeedbackCheckPush.setPushDataDate(pushDate);
						SapFeedbackCheckPush.setPushEmpId(checkConfig.getId());
						SapFeedbackCheckPush.setPushEmpName(checkConfig.getEmpNumber());
						SapFeedbackCheckPush.setPushEmpNumber(checkConfig.getEmpNumber());
						SapFeedbackCheckPush.setPushOrg(checkConfig.getOrgNumber());
						SapFeedbackCheckPush.setPushTime(pushTimeStamp);
						SapFeedbackCheckPush.setRoleId(SapFeedbackConstant.GMZB_BUSINESS);
						SapFeedbackCheckPushList.add(SapFeedbackCheckPush);
						sendMessage(checkConfig.getEmpNumber(),SapFeedbackCheckPush.getPushContent());
						sapFeedbackCheckPushService.batchSapFeedbackCheckPush(SapFeedbackCheckPushList);
					} catch (Exception e) {
						// 没有可以推送的职位
						return;
					}
				} else {
                        // 没有要推送的集采业务
                    }

            }
        } else {
            // 没有需要推送的品类
        }
    }*/
    
//    /**
//     * 缺断货考核配置
//     */
//    @Transactional(readOnly = false)
//    private void insertCheckEmp(){
//        try{
//    		//所有缺断货考核信息
//    		List<SapFeedback> sapFeedbackList = checkSapFeedbackService.findSapFeedback(null);
//    		//已经配置的考核信息
//    		List<FeedbackCheckConfig> checkList = fFeedbackCheckConfigService.find(null);
//    		
//			ArrayList<SapFeedbackCheckEmp> sapFeedbackCheckList = new ArrayList<SapFeedbackCheckEmp>();
//			for (SapFeedback sapFeedback : sapFeedbackList) {
//				String empNumber = "";
//				Integer roleId = 0;
//				boolean flag = false;
//				
//				if(checkList != null && checkList.size() > 0){
//					for (FeedbackCheckConfig config : checkList) {
//						String categoryCode = sapFeedback.getCategoryCode();
//						String brandCode = sapFeedback.getBrandCode();
//						String divisionCode = sapFeedback.getDivisionCode();
//						
//						String brandCode2 = config.getBrandCode();
//						String categoryCode2 = config.getCategoryCode();
//						String orgNumber = config.getOrgNumber();
//						
//						if(categoryCode ==categoryCode2 && brandCode == brandCode2 && divisionCode == orgNumber){
//							flag = true;
//							empNumber = config.getEmpNumber();
//							roleId = config.getRoleId();
//							break;
//						} else {
//							flag = false;
//						}
//					}
//					
//					if(flag){
//						//按照考核处理
//						//批量插入
//						SapFeedbackCheckEmp checkEmp = new SapFeedbackCheckEmp();
//						
//						checkEmp.setRequest(sapFeedback.getRequest());
//						checkEmp.setDatapakid(sapFeedback.getDatapakid());
//						checkEmp.setRecord(sapFeedback.getRecord());
//						checkEmp.setSecondDivisionCode(sapFeedback.getSecondDivisionCode());
//						checkEmp.setDivisionCode(sapFeedback.getDivisionCode());
//						checkEmp.setRegionCode(sapFeedback.getRegionCode());
//						checkEmp.setCategory2Code(sapFeedback.getCategory2Code());
//						checkEmp.setCategoryCode(sapFeedback.getCategoryCode());
//						checkEmp.setBrandCode(sapFeedback.getBrandCode());
//						checkEmp.setGoodsCode(sapFeedback.getGoodsCode());
//						checkEmp.setDataDate(sapFeedback.getDataDate());
//						checkEmp.setStock(sapFeedback.getStock());
//						checkEmp.setUnit(sapFeedback.getUnit());
//						checkEmp.setSaleIncome(sapFeedback.getSaleIncome());
//						checkEmp.setSaleNum(sapFeedback.getSaleNum());
//						checkEmp.setBuyType(sapFeedback.getBuyType());
//						checkEmp.setFeedbackRate(sapFeedback.getFeedbackRate());
//						checkEmp.setFeedbackStatus(sapFeedback.getFeedbackStatus());
//						checkEmp.setStockAmountWithTax(sapFeedback.getStockAmountWithTax());
//						checkEmp.setCurrency(sapFeedback.getCurrency());
//						
//						checkEmp.setEmpNumber(empNumber);
//						checkEmp.setRoleId(roleId);
//						sapFeedbackCheckList.add(checkEmp);
//						
//					} else {
//						//按照缺断货处理
//						Map<String, Object> positionMap = new HashMap<String, Object>();
//						positionMap.put("categoryCode", sapFeedback.getCategoryCode());
//						positionMap.put("srcOrgNumber", sapFeedback.getDivisionCode());
//						List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
//		                        .findPushCategoryPosition(positionMap);
//						if (positionList != null && positionList.size() > 0) {
//							String positionStr = "";
//                            for (FeedbackPushCategoryPositionConfig p : positionList ) {
//                                positionStr += p.getPositionCode();
//                                positionStr += ",";
//                            }
//                            if (positionStr.trim().length() > 0) {
//                                positionStr.substring(0, positionStr.length() - 1);
//                                try {
//                                    List<Employee> empList = employeeRemoteService
//                                            .findEmployeeByMinistrationCodeAndSaleOrg(sapFeedback.getDivisionCode(), positionStr, null);//销售组织编码，职务编码
//                                    if (empList != null && empList.size() > 0) {
//            							for (Employee emp : empList) {
//            								SapFeedbackCheckEmp checkEmp = new SapFeedbackCheckEmp();
//            								
//            								checkEmp.setRequest(sapFeedback.getRequest());
//            								checkEmp.setDatapakid(sapFeedback.getDatapakid());
//            								checkEmp.setRecord(sapFeedback.getRecord());
//            								checkEmp.setSecondDivisionCode(sapFeedback.getSecondDivisionCode());
//            								checkEmp.setDivisionCode(sapFeedback.getDivisionCode());
//            								checkEmp.setRegionCode(sapFeedback.getRegionCode());
//            								checkEmp.setCategory2Code(sapFeedback.getCategory2Code());
//            								checkEmp.setCategoryCode(sapFeedback.getCategoryCode());
//            								checkEmp.setBrandCode(sapFeedback.getBrandCode());
//            								checkEmp.setGoodsCode(sapFeedback.getGoodsCode());
//            								checkEmp.setDataDate(sapFeedback.getDataDate());
//            								checkEmp.setStock(sapFeedback.getStock());
//            								checkEmp.setUnit(sapFeedback.getUnit());
//            								checkEmp.setSaleIncome(sapFeedback.getSaleIncome());
//            								checkEmp.setSaleNum(sapFeedback.getSaleNum());
//            								checkEmp.setBuyType(sapFeedback.getBuyType());
//            								checkEmp.setFeedbackRate(sapFeedback.getFeedbackRate());
//            								checkEmp.setFeedbackStatus(sapFeedback.getFeedbackStatus());
//            								checkEmp.setStockAmountWithTax(sapFeedback.getStockAmountWithTax());
//            								checkEmp.setCurrency(sapFeedback.getCurrency());
//            								
//            								checkEmp.setEmpNumber(emp.getEmployeeId());
//            								checkEmp.setRoleId(positionList.get(0).getRoleId());
//            								
//            								sapFeedbackCheckList.add(checkEmp);
//            							}
//                                    }
//                                }catch (Exception e) {
//                                    // 没有可以推送的人员
//                                    throw new BaseException(e);
//                                }
//                            }
//						}
//					}
//				}
//			}
//			
//			sapFeedbackCheckEmpService.batchInsert(sapFeedbackCheckList);
//        
//	    }catch (Exception e){
//	    	System.out.println(e.getMessage());
//	    }
//    }
    
    /**
     * 清空  sapfeedback_check_emp 表
     */
//    @Transactional
//    private void clearCheckEmp(){
//    	try {
//			sapFeedbackCheckEmpService.clearCheckEmp();
//		} catch (BaseException e) {
//			e.printStackTrace();
//		}
//    }
    /**
     * 获取当前日期 之前最近的周五的日期,用于判断是否是 本周 应该处理的考核数据
     * @return
     */
    public String getLastFridayDate(){
    	// 设置当前日期        
		Calendar aCalendar = Calendar.getInstance();         
		aCalendar.setTime(new Date());           
		// 取当前日期是星期几(week:星期几)         
		int week = aCalendar.get(Calendar.DAY_OF_WEEK);         
		if (week == 1) {             
			week = 7;         
		} else if (week == 0) {             
			week = 6;         
		} else {             
			week -= 1;         
		}           
		// 取距离当前日期之前最近的周五日期与当前日期相差的天数（count：相差的天数，之前最近的周五日期相差的天数）        
		int count = 0;
		if (week < 5) {             
			count = -week-2;         
		} else if (week >= 5) {             
			count = 5 - week;
		}           
		// 获取当前日期之前最近的周五日期         
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");         
		Calendar c = Calendar.getInstance();         
		c.add(Calendar.DAY_OF_WEEK, count);           
		// 格式化并打印出距离当前日期之前最近的周五
		return df.format(c.getTime());
    }
}
