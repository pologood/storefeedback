package com.gome.storefeedback.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.constant.SapFeedbackConstant;
import com.gome.storefeedback.dao.SapFeedbackCheckEmpToDisplayDao;
import com.gome.storefeedback.dao.SapFeedbackDicaiDao;
import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.SapFeedbackPushSender;
import com.gome.storefeedback.service.CheckSapFeedbackService;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackCheckConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToDisplayService;
import com.gome.storefeedback.service.SapFeedbackCheckPushService;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.util.DateTimeUtil;

@Component("sapFeedbackCheckEmpToDisplayService")
public class SapFeedbackCheckEmpToDisplayServiceImpl implements SapFeedbackCheckEmpToDisplayService {
	
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
	    
	
	@Override
	public void pushToDisplay(Date currentDateTime) {
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

	@Transactional(readOnly = false)
    private void pushGMZBJicaiBusiness(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);//品类列表
        ArrayList<SapFeedbackCheckEmp> sapFeedbackCheckList = new ArrayList<SapFeedbackCheckEmp>();
        Date now = null;
        try {
            now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(pushDate) + " 00:00:00");
        } catch (ParseException e1) {
            // 日期格式化错误
            // return;
            throw new BaseException(e1);
        }
        if (categoryList != null && categoryList.size() > 0) {
            for (FeedbackPushCategoryConfig category : categoryList) {
                
                Map<String, Object> jMap = new HashMap<String, Object>();
                jMap.put("dataDate", now);
                jMap.put("categoryCode", category.getCategoryCode());
                List<SapFeedback> sapFeedbackList = checkSapFeedbackService.findPushJiCaiByCategory(jMap);//品类对应分部
                for (SapFeedback sapFeedback : sapFeedbackList) {
                	if (sapFeedbackList != null && sapFeedbackList.size() > 0) {
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
                							
                							checkEmp.setRequest(sapFeedback.getRequest());
                							checkEmp.setDatapakid(sapFeedback.getDatapakid());
                							checkEmp.setRecord(sapFeedback.getRecord());
                							checkEmp.setSecondDivisionCode(sapFeedback.getSecondDivisionCode());
                							checkEmp.setDivisionCode(sapFeedback.getDivisionCode());
                							checkEmp.setRegionCode(sapFeedback.getRegionCode());
                							checkEmp.setCategory2Code(sapFeedback.getCategory2Code());
                							checkEmp.setCategoryCode(category.getCategoryCode());
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
        	//已经配置的考核信息
    		List<FeedbackCheckConfig> checkList = fFeedbackCheckConfigService.find(null);
    		Set<String> empSet = new HashSet<String>();
    		if(checkList != null && checkList.size() > 0){
				for (FeedbackCheckConfig config : checkList) {
					empSet.add(config.getEmpNumber());
				}
			}
    		List<SapFeedbackCheckEmp> empDelList = new ArrayList<SapFeedbackCheckEmp>();
    		List<SapFeedbackCheckEmp> empAddList = new ArrayList<SapFeedbackCheckEmp>();
    		Set<String> empNumberSet = new HashSet<String>();
    		for (SapFeedbackCheckEmp SapFeedbackCheckEmp : sapFeedbackCheckList) {
    			
				String empNumber = SapFeedbackCheckEmp.getEmpNumber();
				if(empSet.contains(empNumber)){
					empDelList.add(SapFeedbackCheckEmp);
					empNumberSet.add(empNumber);
				}
			
			}

    		Iterator<String> itEmpNumber=empNumberSet.iterator();
    		while(itEmpNumber.hasNext()){
    			String empNumber = itEmpNumber.next();
    			Set<String> empCategoryCode = new HashSet<String>();
				Set<String> brandCode = new HashSet<String>();
				for (FeedbackCheckConfig config : checkList) {
					String empNumber2 = config.getEmpNumber();
					if(empNumber2.equals(empNumber)){
						empCategoryCode.add(config.getCategoryCode());
					}
					
				}
				Iterator<String> it=empCategoryCode.iterator();
				while(it.hasNext()){
					String cateGoryCode = it.next();
					for (FeedbackCheckConfig config : checkList) {
						String empNumber2 = config.getEmpNumber();
						String categoryCode2 = config.getCategoryCode();
						if(empNumber2.equals(empNumber)&&categoryCode2.equals(cateGoryCode)){
							brandCode.add(config.getBrandCode());
						}
					}
					Map<String,Object> jMap = new HashMap<String,Object>();
					jMap.put("dataDate", now);
					jMap.put("categoryCode", cateGoryCode);
					jMap.put("brandCodes", brandCode);
					List<SapFeedback> sapFeedbackList = checkSapFeedbackService.findPushJiCaiByCategory(jMap);
					for (SapFeedback sapFeedback : sapFeedbackList) {
						SapFeedbackCheckEmp checkEmp = new SapFeedbackCheckEmp();
						
						checkEmp.setRequest(sapFeedback.getRequest());
						checkEmp.setDatapakid(sapFeedback.getDatapakid());
						checkEmp.setRecord(sapFeedback.getRecord());
						checkEmp.setSecondDivisionCode(sapFeedback.getSecondDivisionCode());
						checkEmp.setDivisionCode(sapFeedback.getDivisionCode());
						checkEmp.setRegionCode(sapFeedback.getRegionCode());
						checkEmp.setCategory2Code(sapFeedback.getCategory2Code());
						checkEmp.setCategoryCode(cateGoryCode);
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
						
						checkEmp.setEmpNumber(empNumber);
						checkEmp.setRoleId(SapFeedbackConstant.GMZB_BUSINESS);
						sapFeedbackCheckList.add(checkEmp);
					}
				}
    		}
    		sapFeedbackCheckList.removeAll(empDelList);

        	checkSapFeedbackService.batchInsert(sapFeedbackCheckList);
        }
    }
    @Transactional(readOnly = false)
    private void pushGMFBDicaiBusiness(Date pushDate) throws BaseException {
        List<FeedbackPushCategoryConfig> categoryList = feedbackPushCategoryConfigService.find(null);
        ArrayList<SapFeedbackCheckEmp> sapFeedbackCheckList = new ArrayList<SapFeedbackCheckEmp>();
        Date now = null;
        try {
            now = DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(pushDate) + " 00:00:00");
        } catch (ParseException e1) {
            // 日期格式化错误
            throw new BaseException(e1);
        }
        if (categoryList != null && categoryList.size() > 0) {
            
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

                        List<SapFeedback> pushDicaiList = checkSapFeedbackService.findPushDiCaiByCategory(pmap);
                        if (pushDicaiList != null && pushDicaiList.size() > 0) {//判断有没有缺断货信息
                        	for (SapFeedback sapFeedback : pushDicaiList) {
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
                        						
                        						checkEmp.setRequest(sapFeedback.getRequest());
                        						checkEmp.setDatapakid(sapFeedback.getDatapakid());
                        						checkEmp.setRecord(sapFeedback.getRecord());
                        						checkEmp.setSecondDivisionCode(sapFeedback.getSecondDivisionCode());
                        						checkEmp.setDivisionCode(sapFeedback.getDivisionCode());
	                							checkEmp.setRegionCode(sapFeedback.getRegionCode());
	                							checkEmp.setCategory2Code(sapFeedback.getCategory2Code());
                        						checkEmp.setCategoryCode(category.getCategoryCode());
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
        	//已经配置的考核信息
    		List<FeedbackCheckConfig> checkList = fFeedbackCheckConfigService.find(null);
    		Set<String> empSet = new HashSet<String>();
    		if(checkList != null && checkList.size() > 0){
				for (FeedbackCheckConfig config : checkList) {
					empSet.add(config.getEmpNumber());
				}
			}
    		List<SapFeedbackCheckEmp> empDelList = new ArrayList<SapFeedbackCheckEmp>();
    		List<SapFeedbackCheckEmp> empAddList = new ArrayList<SapFeedbackCheckEmp>();
    		Set<String> empNumberSet = new HashSet<String>();
    		for (SapFeedbackCheckEmp SapFeedbackCheckEmp : sapFeedbackCheckList) {
    			
				String empNumber = SapFeedbackCheckEmp.getEmpNumber();
				if(empSet.contains(empNumber)){
					empDelList.add(SapFeedbackCheckEmp);
					empNumberSet.add(empNumber);
				}
			
			}

    		Iterator<String> itEmpNumber=empNumberSet.iterator();
    		while(itEmpNumber.hasNext()){
    			String empNumber = itEmpNumber.next();
    			Set<String> empCategoryCode = new HashSet<String>();
				Set<String> brandCode = new HashSet<String>();
				for (FeedbackCheckConfig config : checkList) {
					String empNumber2 = config.getEmpNumber();
					if(empNumber2.equals(empNumber)){
						empCategoryCode.add(config.getCategoryCode());
					}
					
				}
				Iterator<String> it=empCategoryCode.iterator();
				while(it.hasNext()){
					String cateGoryCode = it.next();
					Map<String,Object> jMap = new HashMap<String,Object>();
					Set<String> orgNumber = new HashSet<String>();
					for (FeedbackCheckConfig config : checkList) {
						String empNumber2 = config.getEmpNumber();
						String categoryCode2 = config.getCategoryCode();
						if(empNumber2.equals(empNumber)&&categoryCode2.equals(cateGoryCode)){
							brandCode.add(config.getBrandCode());
							orgNumber.add(config.getOrgNumber());
						}
					}
					jMap.put("srcOrgType1", orgNumber);
					jMap.put("dataDate", now);
					jMap.put("categoryCode", cateGoryCode);
					jMap.put("brandCodes", brandCode);
					List<SapFeedback> sapFeedbackList = checkSapFeedbackService.findPushDiCaiByCategory(jMap);
					for (SapFeedback sapFeedback : sapFeedbackList) {
						SapFeedbackCheckEmp checkEmp = new SapFeedbackCheckEmp();
						
						checkEmp.setRequest(sapFeedback.getRequest());
						checkEmp.setDatapakid(sapFeedback.getDatapakid());
						checkEmp.setRecord(sapFeedback.getRecord());
						checkEmp.setSecondDivisionCode(sapFeedback.getSecondDivisionCode());
						checkEmp.setDivisionCode(sapFeedback.getDivisionCode());
						checkEmp.setRegionCode(sapFeedback.getRegionCode());
						checkEmp.setCategory2Code(sapFeedback.getCategory2Code());
						checkEmp.setCategoryCode(cateGoryCode);
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
						
						checkEmp.setEmpNumber(empNumber);
						checkEmp.setRoleId(SapFeedbackConstant.GMZB_BUSINESS);
						sapFeedbackCheckList.add(checkEmp);
					}
				}
    		}
    		sapFeedbackCheckList.removeAll(empDelList);

        	checkSapFeedbackService.batchInsert(sapFeedbackCheckList);
        }
    }
	
	

    @Resource
    private SapFeedbackCheckEmpToDisplayDao sapFeedbackCheckEmpToDisplayDao;

	@Override
	public List<SapFeedbackCheckEmp> getAllDisplayByEmp(Map<String, Object> map) {
		List<SapFeedbackCheckEmp> list = new ArrayList<SapFeedbackCheckEmp>();
		try {
			list = sapFeedbackCheckEmpToDisplayDao.getAllDisplayByEmp(map);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return list;
	}
}
