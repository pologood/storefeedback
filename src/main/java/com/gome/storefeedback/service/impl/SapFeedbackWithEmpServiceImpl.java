package com.gome.storefeedback.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.constant.SapFeedbackConstant;
import com.gome.storefeedback.dao.SapFeedbackDicaiDao;
import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.entity.SapFeedbackDicai;
import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackPushCategoryConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.service.SapFeedbackWithEmpService;
import com.gome.storefeedback.util.DateTimeUtil;
import com.gome.storefeedback.util.UUIDUtil;

@Component("sapFeedbackWithEmpService")
public class SapFeedbackWithEmpServiceImpl implements SapFeedbackWithEmpService {
	
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
	

	@Override
	public void pushWithEmp(Date currentDateTime) {
//		insertCheckEmp();
        try {
        	// 推送总部业务//找到品类//找到职务//查询出人来//查询出要推送的缺断货记录//推送消息及消息入库//推送标志入库
			pushGMZBJicaiBusiness(currentDateTime);
			// 推送分部业务
			pushGMFBDicaiBusiness(currentDateTime);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 把所有缺断货信息跟员工关联之后插入到zmm_ds62_toHr表
     */
    /*@Transactional(readOnly = false)
    private void insertCheckEmp(){
        try{
    		//所有缺断货信息
    		List<SapFeedback> sapFeedbackList = sapFeedbackService.findSapFeedback(null);
    		List<SapFeedbackCheckEmp> sapFeedbackCheckList = new ArrayList<SapFeedbackCheckEmp>();
			int num = 0;
			int n = 0;
			for (SapFeedback sapFeedback : sapFeedbackList) {
				
				//按照缺断货处理
				Map<String, Object> positionMap = new HashMap<String, Object>();
				positionMap.put("categoryCode", sapFeedback.getCategoryCode());
				positionMap.put("orgNumber", sapFeedback.getDivisionCode());
				//领导不推送，不罚款——定义新方法:findPushCategoryPosition——不包括领导
				List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                        .findPushCategoryPosition(positionMap);
				if (positionList != null && positionList.size() > 0) {
					String positionStr = "";
                    for (FeedbackPushCategoryPositionConfig p : positionList ) {
                        positionStr += p.getPositionCode();
                        positionStr += ",";
                    }
                    if (positionStr.trim().length() > 0) {
                        try {
                            List<Employee> empList = employeeRemoteService
                            .findEmployeeByMinistrationCodeAndSaleOrg(sapFeedback.getDivisionCode(), positionStr, null);//销售组织编码，职务编码
                            if (empList != null && empList.size() > 0) {
    							for (Employee emp : empList) {
    								SapFeedbackCheckEmp checkEmp = new SapFeedbackCheckEmp();
    								
    								checkEmp.setRequest(sapFeedback.getRequest());
    								checkEmp.setDatapakid(sapFeedback.getDatapakid());
    								checkEmp.setRecord(sapFeedback.getRecord());
    								checkEmp.setSecondDivisionCode(sapFeedback.getSecondDivisionCode());
    								checkEmp.setDivisionCode(sapFeedback.getDivisionCode());
    								checkEmp.setRegionCode(sapFeedback.getRegionCode());
    								checkEmp.setCategory2Code(sapFeedback.getCategory2Code());
    								checkEmp.setCategoryCode(sapFeedback.getCategoryCode());
    								checkEmp.setBrandCode(sapFeedback.getBrandCode());
    								checkEmp.setGoodsCode(sapFeedback.getGoodsCode());
    								checkEmp.setDataDate(sapFeedback.getDataDate());
    								checkEmp.setStock(sapFeedback.getStock());
    								checkEmp.setUnit(sapFeedback.getUnit());
    								checkEmp.setSaleIncome(sapFeedback.getSaleIncome());
    								checkEmp.setSaleNum(sapFeedback.getSaleNum());
    								checkEmp.setBuyType(sapFeedback.getBuyType());
    								checkEmp.setFeedbackRate(sapFeedback.getFeedbackRate());
    								checkEmp.setFeedbackStatus(sapFeedback.getFeedbackStatus());
    								checkEmp.setStockAmountWithTax(sapFeedback.getStockAmountWithTax());
    								checkEmp.setCurrency(sapFeedback.getCurrency());
    								
    								checkEmp.setEmpNumber(emp.getEmployeeId());
    								checkEmp.setRoleId(positionList.get(0).getRoleId());
    								sapFeedbackCheckList.add(checkEmp);
    								n++;
    							}
                            }
                        }catch (Exception e) {
                            // 没有可以推送的人员
                            throw new BaseException(e);
                        }
                    }
				}
				num++;
			}
			sapFeedbackService.insertIntoHr(sapFeedbackCheckList);
        
	    }catch (Exception e){
	    	e.printStackTrace();
	    	System.out.println(e.getMessage());
	    }
    }*/
    @Transactional(readOnly = false)
    private void pushGMZBJicaiBusiness(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);//品类列表
        List<SapFeedbackCheckEmp> sapFeedbackCheckList = new ArrayList<SapFeedbackCheckEmp>();
        if (categoryList != null && categoryList.size() > 0) {
            for (FeedbackPushCategoryConfig category : categoryList) {
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
                jMap.put("categoryCode", category.getCategoryCode());
                List<SapFeedbackDicai> sapPushList = sapFeedbackDicaiDao.findPushJiCaiByCategory(jMap);//品类对应分部
                for (SapFeedbackDicai sapFeedbackDicai : sapPushList) {
                	if (sapPushList != null && sapPushList.size() > 0) {
                		Map<String, Object> positionMap = new HashMap<String, Object>();
                		
                		positionMap.put("roleId", SapFeedbackConstant.GMZB_BUSINESS);
                		positionMap.put("categoryCode", category.getCategoryCode());
                		
                		List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                				.findPushPosition(positionMap);//品类职务
                		List<Employee> empList = null;
                		if (positionList != null && positionList.size() > 0) {
                			String positionStr = "";
                			for (FeedbackPushCategoryPositionConfig p : positionList) {
                				positionStr += p.getPositionCode();
                				positionStr += ",";
                			}
                			if (positionStr.trim().length() > 0) {
                				positionStr = positionStr.substring(0, positionStr.length() - 1);
                				try {
                					empList = employeeRemoteService.findEmployeeByMinistrationCodeAndSaleOrg(
                							SapFeedbackConstant.GMZB, positionStr, null);
                					if (empList != null && empList.size() > 0) {
                						List<SapFeedbackPush> sapFeedbackPushList = new ArrayList<SapFeedbackPush>();
                						for (Employee emp : empList) {
                							SapFeedbackCheckEmp checkEmp = new SapFeedbackCheckEmp();
                							
                							checkEmp.setRequest(sapFeedbackDicai.getRequest());
                							checkEmp.setDatapakid(sapFeedbackDicai.getDatapakid());
                							checkEmp.setRecord(sapFeedbackDicai.getRecord());
                							checkEmp.setSecondDivisionCode(sapFeedbackDicai.getSecondDivisionCode());
                							checkEmp.setDivisionCode(sapFeedbackDicai.getDivisionCode());
                							checkEmp.setRegionCode(sapFeedbackDicai.getRegionCode());
                							checkEmp.setCategory2Code(sapFeedbackDicai.getCategory2Code());
                							checkEmp.setCategoryCode(category.getCategoryCode());
                							checkEmp.setBrandCode(sapFeedbackDicai.getBrandCode());
                							checkEmp.setGoodsCode(sapFeedbackDicai.getGoodsCode());
                							checkEmp.setDataDate(now);
                							checkEmp.setStock(sapFeedbackDicai.getStock());
                							checkEmp.setUnit(sapFeedbackDicai.getUnit());
                							checkEmp.setSaleIncome(sapFeedbackDicai.getSaleIncome());
                							checkEmp.setSaleNum(sapFeedbackDicai.getSaleNum());
                							checkEmp.setBuyType(sapFeedbackDicai.getBuyType());
                							checkEmp.setFeedbackRate(sapFeedbackDicai.getFeedbackRate());
                							checkEmp.setFeedbackStatus(sapFeedbackDicai.getFeedbackStatus());
                							checkEmp.setStockAmountWithTax(sapFeedbackDicai.getStockAmountWithTax());
                							checkEmp.setCurrency(sapFeedbackDicai.getCurrency());
                							
                							checkEmp.setEmpNumber(emp.getEmployeeId());
                							checkEmp.setRoleId(SapFeedbackConstant.GMZB_BUSINESS);
                							sapFeedbackCheckList.add(checkEmp);
                						}
                                    } else {
                                        // 没有要推送的人员
                                    }
                                } catch (Exception e) {
                                    // 没有可以推送的职位
                                    return;
                                }
                            }
                        } else {
                            // 没有要推送的集采业务
                        }

                    } else {
                        // 当天 该品类没有缺断货信息
                    }
                	}
                }
            } else {
                // 没有需要推送的品类
            }
        if(sapFeedbackCheckList != null && sapFeedbackCheckList.size() > 0){
        	sapFeedbackService.insertIntoHr(sapFeedbackCheckList);
        }
    }
    @Transactional(readOnly = false)
    private void pushGMFBDicaiBusiness(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);
        List<SapFeedbackCheckEmp> sapFeedbackCheckList = new ArrayList<SapFeedbackCheckEmp>();
        if (categoryList != null && categoryList.size() > 0) {
            Date now = null;
            try {
                now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(pushDate) + " 00:00:00");
            } catch (ParseException e1) {
                // 日期格式化错误
                throw new BaseException(e1);
            }
            for (FeedbackPushCategoryConfig category : categoryList) {
                Map<String, Object> positionMap = new HashMap<String, Object>();
                positionMap.put("roleId", SapFeedbackConstant.GMFB_BUSINESS);
                positionMap.put("categoryCode", category.getCategoryCode());
                List<FeedbackPushCategoryPositionConfig> positionList = feedbackPushCategoryPositionConfigService
                        .findPushPosition(positionMap);
                if (positionList != null && positionList.size() > 0) {
                    Map<String, List<FeedbackPushCategoryPositionConfig>> targetOrgList = new HashMap<String, List<FeedbackPushCategoryPositionConfig>>();
                    for (FeedbackPushCategoryPositionConfig p : positionList) {
                        String key = p.getOrgNumber();//推送部门
                        List<FeedbackPushCategoryPositionConfig> tmp = null;
                        if (targetOrgList.containsKey(key)) {
                            tmp = targetOrgList.get(key);
                            tmp.add(p);
                            targetOrgList.put(key, tmp);
                        } else {
                            tmp = new ArrayList<FeedbackPushCategoryPositionConfig>();
                            tmp.add(p);
                            targetOrgList.put(key, tmp);//每个org_name 对应的FeedbackPushCategoryPositionConfig集合
                        }
                    }
                    for (String targetOrg : targetOrgList.keySet()) {//每个销售组织
                        List<FeedbackPushCategoryPositionConfig> pushList = targetOrgList.get(targetOrg);
                        Map<Integer, Set<String>> srcOrgMap = new HashMap<Integer, Set<String>>();
                        if (pushList != null && pushList.size() > 0) {
                            for (FeedbackPushCategoryPositionConfig p : pushList) {
                                Set<String> set = null;
                                if (srcOrgMap.containsKey(p.getSrcOrgType())) {
                                    set = srcOrgMap.get(p.getSrcOrgType());
                                } else {
                                    set = new HashSet<String>();
                                }
                                set.add(p.getSrcOrgNumber());
                                srcOrgMap.put(p.getSrcOrgType(), set);//每个src_org_type下对应的 src_org_number
                            }
                        }
                        Map<String, Object> pmap = new HashMap<String, Object>();
                        pmap.put("dataDate", now);
                        for (Integer key : srcOrgMap.keySet()) {
                            if (key == 1) {
                                pmap.put("srcOrgType1", srcOrgMap.get(key));
                            } else if (key == 2) {
                                pmap.put("srcOrgType2", srcOrgMap.get(key));
                            }
                        }
                        List<String> categorys = new ArrayList<String>();
                        categorys.add(category.getCategoryCode());
                        pmap.put("categoryCodes", categorys);

                        List<SapFeedbackDicai> pushDicaiList = sapFeedbackDicaiDao.findPushSumDiCaiByCategory(pmap);
                        if (pushDicaiList != null && pushDicaiList.size() > 0) {//判断有没有缺断货信息
                        	for (SapFeedbackDicai sapFeedbackDicai : pushDicaiList) {
                        		String positionStr = "";
                        		for (FeedbackPushCategoryPositionConfig p : targetOrgList.get(targetOrg)) {
                        			positionStr += p.getPositionCode();
                        			positionStr += ",";
                        		}
                        		if (positionStr.trim().length() > 0) {
                        			positionStr.substring(0, positionStr.length() - 1);
                        			try {
                        				List<Employee> empList = employeeRemoteService
                        						.findEmployeeByMinistrationCodeAndSaleOrg(targetOrg, positionStr, null);//销售组织编码，职务编码
                        				if (empList != null && empList.size() > 0) {
                        					List<SapFeedbackPush> sapFeedbackPushList = new ArrayList<SapFeedbackPush>();
                        					for (Employee emp : empList) {
                        						SapFeedbackCheckEmp checkEmp = new SapFeedbackCheckEmp();
                        						
                        						checkEmp.setRequest(sapFeedbackDicai.getRequest());
                        						checkEmp.setDatapakid(sapFeedbackDicai.getDatapakid());
                        						checkEmp.setRecord(sapFeedbackDicai.getRecord());
                        						checkEmp.setSecondDivisionCode(sapFeedbackDicai.getSecondDivisionCode());
                        						checkEmp.setDivisionCode(sapFeedbackDicai.getDivisionCode());
	                							checkEmp.setRegionCode(sapFeedbackDicai.getRegionCode());
	                							checkEmp.setCategory2Code(sapFeedbackDicai.getCategory2Code());
                        						checkEmp.setCategoryCode(category.getCategoryCode());
	                							checkEmp.setBrandCode(sapFeedbackDicai.getBrandCode());
	                							checkEmp.setGoodsCode(sapFeedbackDicai.getGoodsCode());
                        						checkEmp.setDataDate(now);
	                							checkEmp.setStock(sapFeedbackDicai.getStock());
	                							checkEmp.setUnit(sapFeedbackDicai.getUnit());
	                							checkEmp.setSaleIncome(sapFeedbackDicai.getSaleIncome());
	                							checkEmp.setSaleNum(sapFeedbackDicai.getSaleNum());
	                							checkEmp.setBuyType(sapFeedbackDicai.getBuyType());
	                							checkEmp.setFeedbackRate(sapFeedbackDicai.getFeedbackRate());
	                							checkEmp.setFeedbackStatus(sapFeedbackDicai.getFeedbackStatus());
	                							checkEmp.setStockAmountWithTax(sapFeedbackDicai.getStockAmountWithTax());
	                							checkEmp.setCurrency(sapFeedbackDicai.getCurrency());
                        						
                        						checkEmp.setEmpNumber(emp.getEmployeeId());
                        						checkEmp.setRoleId(SapFeedbackConstant.GMFB_BUSINESS);
                        						sapFeedbackCheckList.add(checkEmp);
                        					}
                        				} else {
                        					// 没有可以推送的员工
                        				}
                        			} catch (Exception e) {
                        				// 没有可以推送的人员
                        				throw new BaseException(e);
                        			}
                        		}
                        	} 
							}else {
                        		// 当天没有缺断货信息
                        	}
                    }
                } else {
                    // 没有职位
                }
            }
        } else {
            // 没有需要推送的品类
        }
        if(sapFeedbackCheckList != null && sapFeedbackCheckList.size() > 0){
        	sapFeedbackService.insertIntoHr(sapFeedbackCheckList);
        }
    }
}
