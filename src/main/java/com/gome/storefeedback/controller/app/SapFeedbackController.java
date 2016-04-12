package com.gome.storefeedback.controller.app;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.gsm.entity.org.Employee;
import com.gome.gsm.entity.org.Position;
import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.constant.SapFeedbackConstant;
import com.gome.storefeedback.dao.impl.SapFeedbackDaoImpl;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.entity.SapOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.ResultNoReasonService;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.SapFeedbackPushService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.DateTimeUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * SAP缺断货信息Controller
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月23日下午3:57:12
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/sapFeedbackApp")
public class SapFeedbackController {

    private static final String BUHUO_YES = "1";
    private static final String BUHUO_NO = "0";
    private static final Integer SAPFEEDBACK_HANDLER_YES = 3;
    private static final Integer SAPFEEDBACK_HANDLER_NO = 4;

    private static final Logger logger = LoggerFactory.getLogger(SapFeedbackController.class);

    @Resource
    private FeedbackPushCategoryPositionConfigService feedbackPushCategoryPositionConfigService;

    @Resource
    private SapFeedbackPushService sapFeedbackPushService;

    @Resource
    private SapFeedbackHandlerService sapFeedbackHandlerService;

    @Resource
    private EmployeeRemoteService employeeRemoteService;

    @Resource
    private SapFeedbackService sapFeedbackService;

    @Resource
    private LogMessageSender logMessageSender;

    @Resource
    private ResultNoReasonService resultNoReasonService;

    @Resource
    private SapOrderService sapOrderService;

    /**
     * 1.1 根据用户查询商品缺断货【品类】【集采|地采】【数量】信息
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByEmp", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                    Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
                    if(pmap.get(BUY_TYPE_AUTH_ROLE) != null){
    					inMap.put(BUY_TYPE_AUTH_ROLE, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
    				}else {
    					result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11060);
    					result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11060);
    					logger.info(JsonUtil.javaObjectToJsonString(result));
    			        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    				}
                    inMap.put("srcOrgType1", pmap.get("srcOrgType1"));
                    inMap.put("srcOrgType2", pmap.get("srcOrgType2"));
                    inMap.put(BUY_TYPE_AUTH_ROLE, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                    inMap.put("categoryCodes", pmap.get("categoryCodes"));
                    inMap.put("dataDate", today());
                    inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.CATEGORYSUM_VALUE);
                    Page page = this.sapFeedbackService.findByParams(inMap, null);
                  //  inMap.put("isHandler", "1");
                   // Page page_y = this.sapFeedbackService.findByParams(inMap, null);
                    AppUtil.succussHandler(page,null, result, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                } else {
                    AppUtil.succussHandler(new Page(),null, result, BUY_TYPE_AUTH_ROLE_NO);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 1.2 根据用户、品类、集采|地采查询【品牌】【数量】
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByEmpCategoryBuytype", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmpCategoryBuytype(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                    Map<String, Object> inMap = AppUtil.getInMap(pmap, //
                            "categoryCode",//
                            "buyType",//
                            "divisionCode");
                    inMap.put("srcOrgType1", pmap.get("srcOrgType1"));
                    inMap.put("srcOrgType2", pmap.get("srcOrgType2"));
                    inMap.put(BUY_TYPE_AUTH_ROLE, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                    inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.BRANDSUM_VALUE);
                    inMap.put("dataDate", today());
                    Page page_n = this.sapFeedbackService.findByParams(inMap, AppUtil.getPaginationParameters(pmap));
                    pmap.put("isHandler", "1");
                    Page page_y = this.sapFeedbackService.findByParams(inMap, AppUtil.getPaginationParameters(pmap));
                    AppUtil.succussHandler(page_n,null, result, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                } else {
                    AppUtil.succussHandler(new Page(),null, result, BUY_TYPE_AUTH_ROLE_NO);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 1.3 根据用户、品类、集采|地采、品牌查询【型号】【数量】
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByEmpCategoryBuytypeBrand", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmpCategoryBuytypeBrand(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                    Map<String, Object> inMap = AppUtil.getInMap(pmap, //
                            "categoryCode",//
                            "buyType",//
                            "brandCode",//
                            "divisionCode");
                    inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.GOODSSUM_VALUE);
                    inMap.put("dataDate", today());
                    inMap.put("srcOrgType1", pmap.get("srcOrgType1"));
                    inMap.put("srcOrgType2", pmap.get("srcOrgType2"));
                    inMap.put(BUY_TYPE_AUTH_ROLE, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                    Page page = this.sapFeedbackService.findByParams(inMap, this.getPaginationParameters(pmap));
                    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                    if(page != null){
                    	List dataList = page.getDataList();
                    	for (Object object : dataList) {
                    		boolean flag = true;
                    		boolean flag2 = true;
//                    		String isHandler = "";
							Map<String,Object> map = (Map<String, Object>) object;
							inMap.put("goodsCode", map.get("goodsCode"));
							inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.DIVISIONSUM_VALUE);
							List dataList2 = this.sapFeedbackService.findByParams(inMap, null).getDataList();
							for (Object object2 : dataList2) {
								Map<String,Object> map2 = (Map<String, Object>) object2;
								String isHandler = (String) map2.get("is_handler");
								if(!("3".equals(isHandler) || "4".equals(isHandler))){
									flag = false;
								}
							}
							if(flag){
								for (Object object2 : dataList2) {
									Map<String,Object> map2 = (Map<String, Object>) object2;
									String isHandler = (String) map2.get("is_handler");
									if("3".equals(isHandler)){
										flag2 = false;
									}
								}
								if(!flag2){
									map.put("is_handler", 3);
								}else {
									map.put("is_handler", 4);
								}
							}
							list.add(map);
						}
                    }
                    
                    Map<String, Object> data = new HashMap<String, Object>();
                    if (page != null) {
                        if(page.getDataList()!=null&&page.getDataList().size()>0){
                            data.put("data", list);
                        }else{
                            data.put("data", new ArrayList());
                        }
                        data.put("auth", pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                        result.put("result", data);
                    }else {
                        result.put("result", new ArrayList());
                    }
                    if (page == null) {
                       data.put("data", new ArrayList());
                    }
                    
//                    Page page_y  = null;
//                    if(page==null || page.getDataList().size()<10 ){
//                    	inMap.put("isHandler", "1");
//                    	Map<String, Object> pmap_y = new HashMap<String, Object>();
//                    	PaginationParameters param = AppUtil.getPaginationParameters(pmap_y);
//                    	/*if(page !=null){
//                    		param.setPageMaxSize(10-page.getDataList().size());
//                    	}*/
//                    	//page_y = this.sapFeedbackService.findByParams(inMap, param);
//                    }
                    AppUtil.succussHandler(page,null, result, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                } else {
                    AppUtil.succussHandler(new Page(),null, result, BUY_TYPE_AUTH_ROLE_NO);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 1.4 根据用户、品类、集采|地采、品牌、型号查询【缺货|断货】【分部】【数量】--只适用集采
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByEmpCategoryBuytypeBrandGoods", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmpCategoryBuytypeBrandGoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                    Map<String, Object> inMap = AppUtil.getInMap(pmap, //
                            "categoryCode",//
                            "buyType",//
                            "brandCode",//
                            "goodsCode",//
                            "divisionCode");
                    inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.DIVISIONSUM_VALUE);
                    inMap.put("dataDate", today());
                    inMap.put("srcOrgType1", pmap.get("srcOrgType1"));
                    inMap.put("srcOrgType2", pmap.get("srcOrgType2"));
                    inMap.put(BUY_TYPE_AUTH_ROLE, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                    Page page = this.sapFeedbackService.findByParams(inMap, null);
                    pmap.put("isHandler", "1");
                    Page page_y = this.sapFeedbackService.findByParams(inMap, null);
                    AppUtil.succussHandler(page,page_y, result, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                } else {
                    AppUtil.succussHandler(new Page(),null, result, BUY_TYPE_AUTH_ROLE_NO);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 1.4 根据用户、品类、集采|地采、品牌、型号查询【缺货|断货】【分部】【数量】
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/insertResult", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    String insertResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int hours = new Date().getHours();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if(hours >= 8 && hours <=23){
        	if (AppUtil.requestHandler(request, pmap, result)) {
        		try {
        			// 数据校验
        			// resultFlag：" 1"//补货与否标志（String）0 补货；1不补货
        			if (!(pmap.get("resultFlag") != null && pmap.get("resultFlag").toString().trim().length() > 0)) {
        				AppUtil.exceptionHandler(result);
        			}
        			// buyType: "1",//采购方式（String）【1：集采】【0：地采】
        			if (!(pmap.get("buyType") != null && pmap.get("buyType").toString().trim().length() > 0)) {
        				AppUtil.exceptionHandler(result);
        			}
        			// ids:
        			// ["redis_mybatis_spring_mvc_001","redis_mybatis_spring_mvc_002"],//唯一key（String[]）
        			if (pmap.get("ids") != null) {
        				try {
        					List ids = (List) pmap.get("ids");
        				} catch (Exception e) {
        					AppUtil.exceptionHandler(result);
        				}
        			} else {
        				AppUtil.exceptionHandler(result);
        			}
        			if (pmap.get("resultFlag").toString().trim().equals(BUHUO_NO)) {
        				// resultCode：" 1 "//不补货原因编码（String）
        				// resultName：" 不补货原因 "//不补货名称值（String）
        				if (!(pmap.get("resultCode") != null && pmap.get("resultCode").toString().trim().length() > 0)) {
        					result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11022);
        					result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11022);
        				}else{
        					// 不补货处理
        					List<SapFeedbackHandler> resultList = new ArrayList<SapFeedbackHandler>();
        					getHandlerResult(resultList, pmap);
        					sapFeedbackHandlerService.insertHandlerResultNo(resultList);
        					AppUtil.succussHandler(result);
        				}
        			} else if (pmap.get("resultFlag").toString().trim().equals(BUHUO_YES)) {
        				// orders：["order1 "," order2 "]//采购订单号（String[]）
        				if (!pmap.containsKey("goodsCode")) {
        					AppUtil.exceptionHandler(result);
        					logger.info(JsonUtil.javaObjectToJsonString(result));
        					return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        				}
        				if (pmap.get("orders") != null) {
        					try {
        						List<String> orders = (List<String>) pmap.get("orders");
        						List<String> orderIds = new ArrayList<String>();
        						/*for (String orderId : orders) {
        							Map<String, Object> params = new HashMap<String, Object>();
        							params.put("orderId", orderId);
        							params.put("goodsCode", pmap.get("goodsCode").toString().trim());
        							List list = sapOrderService.findSapOrder(params);
        							if (list == null || list.size() == 0) {
        								result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11020);
        								result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11020);
        								logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
        										JsonUtil.javaObjectToJsonString(result)));
        								logger.info(JsonUtil.javaObjectToJsonString(result));
        								return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        							}
        						}*/
        						for (String order : orders) {
        							Map<String, Object> params = new HashMap<String, Object>();
        							if(order.contains("+")){
        								String[] strs = order.split("\\+");
        								String orderId = strs[0];
        								String orderContent = strs[1];
        								orderIds.add(orderId);
        								
        								params.put("orderId", orderId);
        								params.put("orderContent",orderContent);
        								params.put("goodsCode", pmap.get("goodsCode").toString().trim());
        								//查询对应的订单是否存在
        								List list = sapOrderService.findSapOrder(params);
        								if (list == null || list.size() == 0) {
        									result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11020);
        									result.put(APPErrorInfo.ERRORMSG, orderId + "或" + orderContent + " 不存在");
//        									result.put("order_id", orderId);//返回不存在的订单号
        									logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
        											JsonUtil.javaObjectToJsonString(result)));
        									logger.info(JsonUtil.javaObjectToJsonString(result));
        									return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        								}
        							}else {
        								result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11059);
    									result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11059);
    									logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
    											JsonUtil.javaObjectToJsonString(result)));
    									logger.info(JsonUtil.javaObjectToJsonString(result));
    									return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        							}
        						}
        						// 补货处理
        						List<SapFeedbackHandler> resultList = new ArrayList<SapFeedbackHandler>();
        						getHandlerResult(resultList, pmap);
        						//订单号和所选择的条目不匹配
        						if(pmap.get("flag") != null){
        							result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11058);
                					result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11058);
                					logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                							JsonUtil.javaObjectToJsonString(result)));
                					logger.info(JsonUtil.javaObjectToJsonString(result));
                					return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        						}
        						sapFeedbackHandlerService.insertHandlerResultYes(resultList, orders, pmap.get("goodsCode")
        								.toString().trim());
        						AppUtil.succussHandler(result);
        					} catch (Exception e) {
        						AppUtil.exceptionHandler(result);
        					}
        				} else {
        					result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11021);
        					result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11021);
        					logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
        							JsonUtil.javaObjectToJsonString(result)));
        					logger.info(JsonUtil.javaObjectToJsonString(result));
        					return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        				}
        			} else {
        				AppUtil.exceptionHandler(result);
        			}
        			logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
        					JsonUtil.javaObjectToJsonString(result)));
        		} catch (BaseException e) {
        			logger.error(e.getMessage(), e);
        			AppUtil.exceptionHandler(result);
        		}
        	}
        	
        } else {
        	result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11054);
        	result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11054);
        	logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
					JsonUtil.javaObjectToJsonString(result)));
        }
        String resultJson=JsonUtil.javaObjectToJsonString(result);
    	logger.info(resultJson);
    	return GzipAESUtil.compressThenEncryptAES(resultJson);
    }

    /**
     * 1.6 获取不补货原因
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getReplenishmentNOReason", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getReplenishmentNOReason(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                List list = this.resultNoReasonService.findCombo(null);
                if (list != null && list.size() > 0) {
                    result.put("result", list);
                } else {
                    result.put("result", new ArrayList());
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    private boolean getEmpInfoFromRPC(Map<String, Object> pmap, String... params) throws BaseException {
        if (params != null && params.length > 0) {
            String empNumber = pmap.get("employeeId").toString().trim();

            /*
             * Map<String, Object> inMap = new HashMap<String, Object>();
             * inMap.put("pushEmpNumber", empNumber); List<SapFeedbackPush>
             * pushList = sapFeedbackPushService.findSapFeedbackPush(inMap); if
             * (pushList != null && pushList.size() > 0) { SapFeedbackPush
             * pushEntity = pushList.get(0); if (pushEntity != null &&
             * pushEntity.getRoleId() != null) { switch (pushEntity.getRoleId())
             * { case SapFeedbackConstant.GMZB_LEADER: pmap.put(BUY_TYPE_AUTH,
             * "1"); break; case SapFeedbackConstant.GMZB_BUSINESS:
             * pmap.put(BUY_TYPE_AUTH, "1"); break; case
             * SapFeedbackConstant.GMFB_LEADER: pmap.put(BUY_TYPE_AUTH, "0");
             * break; case SapFeedbackConstant.GMFB_BUSINESS:
             * pmap.put(BUY_TYPE_AUTH, "0"); break; }
             * pmap.put(BUY_TYPE_AUTH_ROLE, pushEntity.getRoleId().toString());
             * return true; } }
             */

//            1.员工ID 远程调用 employeeRemoteService 获取 【单位编码】和【职位编码】
//            2.【单位编码(orgNumber 推送业务单位编码)】和【职位编码】feedbackPushCategoryPositionConfigService 获取 【推送关系 列表】
//            3.【推送关系 列表】中 获取【角色】【缺断货单位(srcOrgNumber)】【品类】
//                     3.1 如果【角色】为【分部业务】 构建他权限范围能查询的 
//                                     一级分部缺货Map 【srcOrgType1】 
//                                     二级分部缺货Map 【srcOrgType2】
//                                     品类Set 【categoryCodes】
//                     3.2 如果【角色】为【分部领导】 查询 分部领导 找到权限范围内的 缺货分部
//                             构建 一级分部缺货Map 【srcOrgType1】 二级分部缺货Map 【srcOrgType2】  
//                     3.3 如果【角色】为【总部业务】构建【品类】Set 【categoryCodes】
//                     3.4 如果【角色】为【总部领导】全品类查询
//                     
//        总部领导能看集采地采全品类， 不能处理，查询的结果当天全部 (不分处理与否)
//        总部业务能看集采地采对口品类，能处理集采，不能处理地采，只能查看未处理过的
//        分部领导能看地采，不能看集采，不能处理，查询的结果前天全部(不分处理与否)
//        分部业务能看地采，不能看集采，能处理地采，不能处理集采，只能查看未处理过的
            
            String empId = pmap.get("storeFeedbackUserId").toString().trim();
            Employee emp = employeeRemoteService.findEmployeeById(empId);
            Map<String, Object> positionMap = new HashMap<String, Object>();
            Set<Integer> roleIds = new HashSet<Integer>();
            Set<String> categorys = new HashSet<String>();
            if (emp != null && emp.getPositions() != null && emp.getPositions().size() > 0) {
                List<Position> positions = emp.getPositions();
                for (Position position : positions) {
                    if (position.getPositionCode().length() >= 10) {
                        String divisionCode = position.getPositionCode().substring(0,position.getPositionCode().length() - 10);
                        String positionCode = position.getPositionCode().substring(position.getPositionCode().length() - 10, position.getPositionCode().length());
                        if (isGomeGroup(divisionCode)) {
                            pmap.put(BUY_TYPE_AUTH, "1");
                        } else {
                            pmap.put(BUY_TYPE_AUTH, "0");
                            pmap.put("divisionCode", divisionCode);
                        }
                        pmap.put("positionCode", positionCode);
                        positionMap.put("orgNumber", divisionCode);
                        positionMap.put("positionCode", positionCode);

                        List<FeedbackPushCategoryPositionConfig> pushList = feedbackPushCategoryPositionConfigService.findPushPosition(positionMap);
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
                                if (p.getSrcOrgType() != null) {
                                    srcOrgMap.put(p.getSrcOrgType(), set);
                                }
                                roleIds.add(p.getRoleId());
                                if (p.getCategoryCode() != null) {
                                    categorys.add(p.getCategoryCode());
                                }
                            }
                            if (roleIds != null && roleIds.size() == 1) {
                                Integer roleId = new ArrayList<Integer>(roleIds).get(0);
                                // 分部领导 找到权限范围内的 缺货分部
                                if (roleId != null && SapFeedbackConstant.GMFB_LEADER == roleId) {
                                    Map<String, Object> positionMap1 = new HashMap<String, Object>();
                                    positionMap1.put("orgNumber", divisionCode);
                                    List<FeedbackPushCategoryPositionConfig> pushList1 = feedbackPushCategoryPositionConfigService
                                            .findPushPosition(positionMap1);
                                    if (pushList1 != null && pushList1.size() > 0) {
                                        srcOrgMap.clear();
                                        for (FeedbackPushCategoryPositionConfig p : pushList1) {
                                            Set<String> set = null;
                                            if (srcOrgMap.containsKey(p.getSrcOrgType())) {
                                                set = srcOrgMap.get(p.getSrcOrgType());
                                            } else {
                                                set = new HashSet<String>();
                                            }
                                            set.add(p.getSrcOrgNumber());
                                            if (p.getSrcOrgType() != null) {
                                                srcOrgMap.put(p.getSrcOrgType(), set);
                                            }
                                        }
                                    }
                                }
                                pmap.put(BUY_TYPE_AUTH_ROLE, roleId);
                            } else {
                                pmap.put(BUY_TYPE_AUTH_ROLE, BUY_TYPE_AUTH_ROLE_NO);
                                return false;
                            }
                            for (Integer key : srcOrgMap.keySet()) {
                                if (key == 1) {
                                    pmap.put("srcOrgType1", srcOrgMap.get(key));
                                } else if (key == 2) {
                                    pmap.put("srcOrgType2", srcOrgMap.get(key));
                                }
                            }
                           
                        }
                    } else {
                        throw new BaseException("获取部门编码失败");
                    }
                }
                pmap.put("categoryCodes", categorys);
                return true;
            } else {
                throw new BaseException("获取部门编码失败");
            }
        }
        return false;
    }

    private static final String GOME_GROUP = "GMZB";
    private static final String BUY_TYPE_AUTH = "buy_type_auth";
    private static final String BUY_TYPE_AUTH_ROLE = "buy_type_auth_role";
    private static final String BUY_TYPE_AUTH_ROLE_NO = "00000000";

    // private static final int GMZB_LEADER = 11111100;// 总部领导
    // private static final int GMZB_BUSINESS = 11001110;// 总部业务
    // private static final int GMFB_LEADER = 10110100;// 分部领导
    // private static final int GMFB_BUSINESS = 10100001;// 分部业务

    private static boolean isGomeGroup(String orgId) {
        if (orgId != null && GOME_GROUP.equals(orgId)) {
            return true;
        }
        return false;
    }

    private void getHandlerResult(List<SapFeedbackHandler> resultList, Map<String, Object> pmap) {
        List<String> ids = (List<String>) pmap.get("ids");
        String resultFlag = pmap.get("resultFlag").toString().trim();
        String buyType = pmap.get("buyType").toString().trim();
        String resultName = null;
        Integer resultCode = null;
        Integer orderNum = null;
        if (pmap.get("resultFlag").toString().trim().equals(BUHUO_NO)) {
            if (pmap.containsKey("resultCode") && pmap.get("resultCode") != null
                    && pmap.get("resultCode").toString().trim().length() > 0) {
                resultCode = Integer.parseInt(pmap.get("resultCode").toString().trim());
            }
            if (pmap.containsKey("resultName") && pmap.get("resultName") != null
                    && pmap.get("resultName").toString().trim().length() > 0) {
                resultName = pmap.get("resultName").toString().trim();
            }
        } else if (pmap.get("resultFlag").toString().trim().equals(BUHUO_YES)) {
            if (pmap.containsKey("orders") && pmap.get("orders") != null) {
                try {
                    List<String> orders = (List<String>) pmap.get("orders");
                    orderNum = orders.size();
                } catch (Exception e) {

                }
            }
        }

        /*if (ids != null && ids.size() > 0) {
            for (String id : ids) {
                SapFeedbackHandler sapFH = id2PK(id);
                if (sapFH != null) {
                    // sapFH.setIsPush(isPush);
                    // sapFH.setPushTime(pushTime);
                    sapFH.setIsHandler(SAPFEEDBACK_HANDLER_YES);
                    sapFH.setHandlerResult(Integer.parseInt(pmap.get("resultFlag").toString()));
                    sapFH.setResultYesOrderNum(orderNum);
                    sapFH.setResultNoName(resultName);
                    sapFH.setResultNoCode(resultCode);
                    sapFH.setHandlerEmpId(pmap.get("storeFeedbackUserId").toString());
                    sapFH.setHandlerEmpName(pmap.get("employeeName").toString());
                    sapFH.setHandlerEmpNumber(pmap.get("employeeId").toString());
                    sapFH.setHandlerTime(new Date());
                    resultList.add(sapFH);
                }
            }
        }*/
        List<String> orders = (List<String>) pmap.get("orders");
        if (ids != null && ids.size() > 1) {
        	
        		for (int i=0;i<ids.size();i++) {
        			String id = ids.get(i);
        			SapFeedbackHandler sapFH = id2PK(id);
        			
        			boolean flag = false;
        			if(orders != null && ids.size() == orders.size()){
        				String order = orders.get(i);
        				Map<String, Object> params = new HashMap<String, Object>();
            			if(order.contains("+")){
            				String[] strs = order.split("\\+");
            				String orderId = strs[0];
            				String orderContent = strs[1];
            				params.put("orderId", orderId);
            				params.put("orderContent", orderContent);
            				params.put("goodsCode", pmap.get("goodsCode").toString().trim());
            				params.put("date", today());
            				try {
            					List sapOrders = sapOrderService.findSapOrder(params);
            					SapOrder sapOrder = (SapOrder) sapOrders.get(0);
            					Long orderNum2 = sapOrder.getOrderNum();
            					Long orderToNum = sapOrder.getOrderToNum();
            					flag = orderNum2 == orderToNum ? true:false;
            					
            				} catch (BaseException e) {
            					e.printStackTrace();
            				}
            			}
        			} else if (orders != null && ids.size() != orders.size()){
        				pmap.put("flag", false);
        			}
        			if (sapFH != null) {
        				// sapFH.setIsPush(isPush);
        				//pushTime 设置成 BW 缺断货表数据对应的日期
        				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        				Date calday;
        				try {
        					calday = format.parse(today());
        					sapFH.setPushTime(calday);
        				} catch (ParseException e) {
        					e.printStackTrace();
        				}
        				if (pmap.get("resultFlag").toString().trim().equals(BUHUO_NO)) {
        					sapFH.setIsHandler(SAPFEEDBACK_HANDLER_NO);
        					
        				} else {
        					if(flag){
        						//补货完全
        						sapFH.setIsHandler(4);
        					} else {
        						//补货但未补全
        						sapFH.setIsHandler(3);
        					}
        				}
        				sapFH.setHandlerResult(Integer.parseInt(pmap.get("resultFlag").toString()));
        				sapFH.setResultYesOrderNum(orderNum);
        				sapFH.setResultNoName(resultName);
        				sapFH.setResultNoCode(resultCode);
        				sapFH.setHandlerEmpId(pmap.get("storeFeedbackUserId").toString());
        				sapFH.setHandlerEmpName(pmap.get("employeeName").toString());
        				sapFH.setHandlerEmpNumber(pmap.get("employeeId").toString());
        				sapFH.setHandlerTime(new Date());
        				resultList.add(sapFH);
        			}
        		}
        	
        }
        
        if (ids != null && ids.size() == 1) {
        	boolean flag = true;
        	String id = ids.get(0);
    		SapFeedbackHandler sapFH = id2PK(id);	
			if(orders != null && orders.size() > 0){
				for(String order : orders){
					Map<String, Object> params = new HashMap<String, Object>();
					if(order.contains("+")){
						String[] strs = order.split("\\+");
						String orderId = strs[0];
						String orderContent = strs[1];
						params.put("orderId", orderId);
						params.put("orderContent", orderContent);
						params.put("goodsCode", pmap.get("goodsCode").toString().trim());
						params.put("date", today());
						try {
							List sapOrders = sapOrderService.findSapOrder(params);
							SapOrder sapOrder = (SapOrder) sapOrders.get(0);
							Long orderNum2 = sapOrder.getOrderNum();
							Long orderToNum = sapOrder.getOrderToNum();
							if(orderNum2 > orderToNum){
								flag = false;
							}
							
						} catch (BaseException e) {
							e.printStackTrace();
						}
					}
				}
			}
		
			if (sapFH != null) {
				// sapFH.setIsPush(isPush);
				//pushTime 设置成 BW 缺断货表数据对应的日期
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				Date calday;
				try {
					calday = format.parse(today());
					sapFH.setPushTime(calday);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (pmap.get("resultFlag").toString().trim().equals(BUHUO_NO)) {
					sapFH.setIsHandler(SAPFEEDBACK_HANDLER_NO);
					
				} else {
					if(flag){
						//补货完全
						sapFH.setIsHandler(4);
					} else {
						//补货但未补全
						sapFH.setIsHandler(3);
					}
				}
				sapFH.setHandlerResult(Integer.parseInt(pmap.get("resultFlag").toString()));
				sapFH.setResultYesOrderNum(orderNum);
				sapFH.setResultNoName(resultName);
				sapFH.setResultNoCode(resultCode);
				sapFH.setHandlerEmpId(pmap.get("storeFeedbackUserId").toString());
				sapFH.setHandlerEmpName(pmap.get("employeeName").toString());
				sapFH.setHandlerEmpNumber(pmap.get("employeeId").toString());
				sapFH.setHandlerTime(new Date());
				resultList.add(sapFH);
			}
        }
    }

    private static SapFeedbackHandler id2PK(String id) {
        SapFeedbackHandler sapFeedbackHandler = new SapFeedbackHandler();
        String[] pk = id.split("#");
        if (pk != null && pk.length == 3) {
            if (pk[0] != null && pk[0].trim().length() > 0) {
                sapFeedbackHandler.setRequest(pk[0]);
            }
            if (pk[0] != null && pk[0].trim().length() > 0) {
                sapFeedbackHandler.setDatapakid(Double.parseDouble(pk[1]));
            }
            if (pk[0] != null && pk[0].trim().length() > 0) {
                sapFeedbackHandler.setRecord(Integer.parseInt(pk[2]));
            }
            return sapFeedbackHandler;
        }
        return null;
    }
    
    private static String  today(){
        Calendar c = Calendar.getInstance();  
        c.setTime(new Date());  
        c.add(c.DATE, -1);
        Date temp_date = c.getTime();  
        return DateTimeUtil.formatDate(temp_date);
    }
    
    public static PaginationParameters getPaginationParameters(Map<String, Object> pmap) {
        PaginationParameters param = new PaginationParameters();
        int loadCount = 500;
        if (pmap.containsKey("loadCount") && pmap.get("loadCount") != null
                && pmap.get("loadCount").toString().length() > 0) {
            //loadCount = Integer.parseInt(pmap.get("loadCount").toString());
            if (loadCount != 0) {
                param.setPageMaxSize(loadCount);
            }
        }
        if (pmap.containsKey("currentItemNo") && pmap.get("currentItemNo") != null
                && pmap.get("currentItemNo").toString().length() > 0) {
            int currentItemNo = Integer.parseInt(pmap.get("currentItemNo").toString());
            param.setCurrentPageNumber(currentItemNo / loadCount + 1);
            param.setAlreadyLoadCount(currentItemNo);
        }

        return param;
    }
}