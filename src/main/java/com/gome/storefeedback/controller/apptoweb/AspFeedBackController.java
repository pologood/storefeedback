package com.gome.storefeedback.controller.apptoweb;

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
import org.springframework.web.servlet.ModelAndView;

import com.gome.gsm.entity.org.Employee;
import com.gome.gsm.entity.org.Position;
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.constant.SapFeedbackConstant;
import com.gome.storefeedback.controller.app.SapFeedbackController;
import com.gome.storefeedback.dao.impl.SapFeedbackDaoImpl;
import com.gome.storefeedback.entity.CateGroySum;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.entity.SapOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.ResultNoReasonService;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.DateTimeUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.util.OauthUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
import com.ibm.icu.text.SimpleDateFormat;
/**
 * 断缺货相关功能在服务器端展示
 * @author zhaozhenxiu
 *
 */
@Controller
@RequestMapping("/aspFeedBack")
public class AspFeedBackController {

    private static final String BUHUO_YES = "1";
    private static final String BUHUO_NO = "0";
    private static final Integer SAPFEEDBACK_HANDLER_YES = 3;
    private static final Integer SAPFEEDBACK_HANDLER_NO = 4;
	
	@Resource
	private FeedbackPushCategoryPositionConfigService feedbackPushCategoryPositionConfigService;
	@Resource
    private SapFeedbackService sapFeedbackService;
	@Resource
    private SapFeedbackHandlerService sapFeedbackHandlerService;
	@Resource
    private SapOrderService sapOrderService;
	@Resource
    private EmployeeRemoteService employeeRemoteService;
	@Resource
    private ResultNoReasonService resultNoReasonService;
	@Resource
    private LogMessageSender logMessageSender;
	
	private static final Logger logger = LoggerFactory.getLogger(SapFeedbackController.class);
	
	/**
	 * 到  category 页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/goToCategoryPage")
	public ModelAndView goToCategoryPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
        	if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
                if(pmap.get(BUY_TYPE_AUTH_ROLE) != null){
                	request.setAttribute("buyTypeAuthRole", pmap.get(BUY_TYPE_AUTH_ROLE));
                }
        	}
        }
        mv.setViewName("appmain/category");
        return mv;
    }
	
	/**
	 * 1.1 登录之后根据根据用户查询商品缺断货【品类】【集采|地采】【数量】信息
	 * @param request
	 * @param response
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getByEmp", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
    		try {
    			if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
    				Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
    				if(pmap.get(BUY_TYPE_AUTH_ROLE) != null){
    					inMap.put(BUY_TYPE_AUTH_ROLE, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
    				}
    				inMap.put("srcOrgType1", pmap.get("srcOrgType1"));
    				inMap.put("srcOrgType2", pmap.get("srcOrgType2"));
    				if(pmap.get(BUY_TYPE_AUTH_ROLE) != null){
    					inMap.put(BUY_TYPE_AUTH_ROLE, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
    				}else {
    					result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11060);
    					result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11060);
    					logger.info(JsonUtil.javaObjectToJsonString(result));
    			        return JsonUtil.javaObjectToJsonString(result);
    				}
    				inMap.put("categoryCodes", pmap.get("categoryCodes"));
    				inMap.put("dataDate", today());
    				inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.CATEGORYSUM_VALUE);
    				PaginationParameters params = new PaginationParameters(request,response);
    				Page page = this.sapFeedbackService.findByParams(inMap, null);
    				
    				if (null == page) {
    					result.put("total", 0);
    					result.put("rows", new ArrayList());
    				} else {
    					List list = page.getDataList();
    					List list1 = new ArrayList();
    					for (Object object : list) {
    						list1.add(object);
    						list1.add(object);
    					}
    					if("10110100".equals(pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim()) || "10100001".equals(pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim())){
    						result.put("rows", page.getDataList());
    					}else if("11111100".equals(pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim()) || "11001110".equals(pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim())){
    						result.put("rows", list1);
    					}
    					result.put("total", page.getTotalSize());
    				}
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
        return JsonUtil.javaObjectToJsonString(result);
    }
	
	/**
	 * 到  categoryBrand 页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/goToCategoryBrandPage")
	public ModelAndView goToCategoryBrandPage(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
        ModelAndView mv = new ModelAndView();
        map.put("categoryCode", request.getParameter("categoryCode"));
        map.put("buyType", request.getParameter("buyType"));
        mv.setViewName("appmain/categoryBrand");
        return mv;
    }
	
    /**
     * 1.2 根据用户、品类、集采|地采查询【品牌】【数量】
     * 
     * @param request
     * @param response
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByEmpCategoryBuytype", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmpCategoryBuytype(HttpServletRequest request, HttpServletResponse response, Map<String, Object> result) throws Exception {
        Map<String, Object> pmap = new HashMap<String, Object>();
        request.setAttribute("buyType", request.getParameter("buyType"));
        if (requestHandlernew(request, pmap, result)) {
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
                    PaginationParameters params = new PaginationParameters(request,response);
                    Page page_n = this.sapFeedbackService.findByParams(inMap, params);
/*                    Page page_n = this.sapFeedbackService.findByParams(inMap, AppUtil.getPaginationParameters(pmap));*/                    
                    if (null == page_n) {
                        result.put("total", 0);
                        result.put("rows", new ArrayList());
                    } else {
                        result.put("total", page_n.getTotalSize());
                        result.put("rows", page_n.getDataList());
                    }
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
        return JsonUtil.javaObjectToJsonString(result);
    }
    
    /**
     * 到  categoryBrandGoods 页面
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToCategoryBrandGoodsPage")
	public ModelAndView goToCategoryBrandGoodsPage(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map) throws Exception {
        ModelAndView mv = new ModelAndView();
        map.put("categoryCode", request.getParameter("categoryCode"));
        map.put("buyType", request.getParameter("buyType"));
        map.put("brandCode",request.getParameter("brandCode"));
        mv.setViewName("appmain/categoryBrandGoods");
        return mv;
    }
    
    /**
     * 1.3 根据用户、品类、集采|地采、品牌查询【型号】【数量】
     * 
     * @param request
     * @param response
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByEmpCategoryBuytypeBrand", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmpCategoryBuytypeBrand(HttpServletRequest request, HttpServletResponse response, Map<String, Object> result) throws Exception {
        Map<String, Object> pmap = new HashMap<String, Object>();
        request.setAttribute("buyType", request.getParameter("buyType"));
        if (requestHandlernew(request, pmap, result)) {
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
                    PaginationParameters params = new PaginationParameters(request,response);
                    
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
                    if (null == page) {
                        result.put("total", 0);
                        result.put("rows", new ArrayList());
                    } else {
                        result.put("total", list.size());
                        result.put("rows", list);
                    }
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
        return JsonUtil.javaObjectToJsonString(result);
    }
	
    
    /**
     * 到 categoryBrandGoodsDivision 页面
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToCategoryBrandGoodsDivisionPage")
	public ModelAndView goToCategoryBrandGoodsDivisionPage(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map) throws Exception {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
        	if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
                if(pmap.get(BUY_TYPE_AUTH_ROLE) != null){
                	request.setAttribute("buyTypeAuthRole", pmap.get(BUY_TYPE_AUTH_ROLE));
                }
        	}
        }
        
        map.put("categoryCode", request.getParameter("categoryCode"));
        map.put("buyType", request.getParameter("buyType"));
        map.put("brandCode",request.getParameter("brandCode"));
        map.put("goodsCode",request.getParameter("goodsCode"));
        request.getParameter("dataparkId");
        request.getParameter("record");
        map.put("goodsId",request.getParameter("goodsId")+ "#" +request.getParameter("dataparkId")+ "#" +request.getParameter("record"));
        map.put("reason", JsonUtil.jsonStringToMap(this.getReplenishmentNOReason(request, response)));
        mv.setViewName("appmain/categoryBrandGoodsDivision");
        return mv;
    }
    
    /**
     * 1.4 根据用户、品类、集采|地采、品牌、型号查询【缺货|断货】【分部】【数量】
     * 
     * @param request
     * @param response
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getByEmpCategoryBuytypeBrandGoods", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getByEmpCategoryBuytypeBrandGoods(HttpServletRequest request, HttpServletResponse response, Map<String, Object> result) throws Exception {
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
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
                    PaginationParameters params = new PaginationParameters(request,response);
                    Page page = this.sapFeedbackService.findByParams(inMap, params);
                    if (null == page) {
                        result.put("total", 0);
                        result.put("rows", new ArrayList());
                    } else {
                        result.put("total", page.getDataList().size());
                        result.put("rows", page.getDataList());
                    }
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
        return JsonUtil.javaObjectToJsonString(result);
    }
    
    /**
     * 1.5	保存缺断货处理的结果
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertResult", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    String insertResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int hours = new Date().getHours();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if(hours >= 10 && hours <=23){
        	if (requestHandlernew(request, pmap, result)) {
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
        					//更新缺断货的关联表(zmm_ds62_tohr)，更改相应的IS_HANDLER 和 HANDLER_RESULT
        					sapFeedbackHandlerService.updateSapFeedbackToHr(resultList);
        					AppUtil.succussHandler(result);
        				}
        			} else if (pmap.get("resultFlag").toString().trim().equals(BUHUO_YES)) {
        				// orders：["order1 "," order2 "]//采购订单号（String[]）
        				if (!pmap.containsKey("goodsCode")) {
        					AppUtil.exceptionHandler(result);
        					logger.info(JsonUtil.javaObjectToJsonString(result));
        					return JsonUtil.javaObjectToJsonString(result);
        				}
        				if (pmap.get("orders") != null) {
        					try {
        						List<String> orders = (List<String>) pmap.get("orders");
        						List<String> orderIds = new ArrayList<String>();
        						
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
        									result.put(APPErrorInfo.ERRORMSG, orderId + " 订单号不存在");
//        									result.put("order_id", orderId);//返回不存在的订单号
        									logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
        											JsonUtil.javaObjectToJsonString(result)));
        									logger.info(JsonUtil.javaObjectToJsonString(result));
        									return JsonUtil.javaObjectToJsonString(result);
        								}
        							}else {
        								result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11059);
    									result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11059);
    									logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
    											JsonUtil.javaObjectToJsonString(result)));
    									logger.info(JsonUtil.javaObjectToJsonString(result));
    									return JsonUtil.javaObjectToJsonString(result);
        							}
        						}
        						//补货处理
        						List<SapFeedbackHandler> resultList = new ArrayList<SapFeedbackHandler>();
        						getHandlerResult(resultList, pmap);
        						//订单号和所选择的条目不匹配
        						if(pmap.get("flag") != null){
        							result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11058);
                					result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11058);
                					logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                							JsonUtil.javaObjectToJsonString(result)));
                					logger.info(JsonUtil.javaObjectToJsonString(result));
                					return JsonUtil.javaObjectToJsonString(result);
        						}
        						sapFeedbackHandlerService.insertHandlerResultYes(resultList, orders, pmap.get("goodsCode")
        								.toString().trim());
        						//更新缺断货的关联表(zmm_ds62_tohr)，更改相应的IS_HANDLER 和 HANDLER_RESULT
            					sapFeedbackHandlerService.updateSapFeedbackToHr(resultList);
        						AppUtil.succussHandler(result);
        					} catch (Exception e) {
        						System.out.println(e.getMessage());
        						AppUtil.exceptionHandler(result);
        					}
        				} else {
        					result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11021);
        					result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11021);
        					logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
        							JsonUtil.javaObjectToJsonString(result)));
        					logger.info(JsonUtil.javaObjectToJsonString(result));
        					return JsonUtil.javaObjectToJsonString(result);
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
        return resultJson;
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
        if (requestHandlernew(request, pmap, result)) {
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
        return JsonUtil.javaObjectToJsonString(result);
    }
    
	private boolean getEmpInfoFromRPC(Map<String, Object> pmap, String... params) throws BaseException {
        if (params != null && params.length > 0) {
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
        				String[] strs = order.split("+");
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
    				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    				Date calday = new Date();
    				try {
    					String today = today();
    					calday = format.parse(today);
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
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
    
    public static boolean requestHandlernew(HttpServletRequest request, Map<String, Object> pmap,
            Map<String, Object> result) throws BaseException {
        Map<String, Object> hashMap = new HashMap<String, Object>();
     /*   hashMap = JsonUtil.jsonStringToMap(AESAPPUtils.decryptAES(request
                .getParameter(BusinessGlossary.APP_STOREFEEDBACK)));*/
        String accesstoken = (String) request.getSession().getAttribute("token");//(String) hashMap.get(BusinessGlossary.ACCESS_TOKEN);
        Map map = OauthUtil.validateAccessToken(accesstoken);
        Map eMap = (Map) map.get(BusinessGlossary.EMPLOYEE);
        if (eMap == null) {
            result.put(BusinessGlossary.ERROR_CODE, BusinessGlossary.EC6001);
            result.put(BusinessGlossary.ERROR_MESSAGE, BusinessGlossary.EM6001);
            return false;
        } else {
            if (pmap == null) {
                pmap = new HashMap<String, Object>();
            }
            List<String> ids = new ArrayList<String>();
            List<String> orders = new ArrayList<String>();
            String str = request.getParameter("ids");
            String idsStrs="";
            if(str != null && str.length() > 0){
            	idsStrs = str.subSequence(0, str.length()-1).toString();
            }
            
            if(idsStrs.length() > 0){
            	String[] splits = idsStrs.split("\\+");//[+]
        		for (String id : splits) {
        			if(id != null && id != ""){
					ids.add(id);}
				}
            }
            if(request.getParameter("order") != null){
            	orders.add(request.getParameter("order"));
            }
            pmap.put("employeeId", eMap.get("employeeId"));
            pmap.put("companyId", eMap.get("companyId"));
            pmap.put("employeeName", eMap.get("employeeName"));
            pmap.put("companyCode", eMap.get("companyCode"));
            pmap.put("storeFeedbackUserId", eMap.get("id"));
            pmap.put("buyType", request.getParameter("buyType"));
            pmap.put("categoryCode", request.getParameter("categoryCode"));
            pmap.put("brandCode", request.getParameter("brandCode"));
            pmap.put("goodsCode", request.getParameter("goodsCode"));
            pmap.put("isHandler", request.getParameter("isHandler"));
            
            pmap.put("divisionCode", request.getParameter("divisionCode"));
            pmap.put("resultFlag", request.getParameter("resultFlag"));
            pmap.put("ids", ids);
            pmap.put("resultCode", request.getParameter("resultNoCode"));
            String resultName = request.getParameter("resultNoName");
            if(resultName != null){
            	pmap.put("resultName", resultName.replaceAll("[0-9]", ""));
            }
            pmap.put("orders", orders);
            return true;
        }
    }
    public static PaginationParameters getPaginationParameters(Map<String, Object> pmap) {
        PaginationParameters param = new PaginationParameters();
        int loadCount = 500;
        
        param.setPageMaxSize(loadCount);
        
        return param;
    }
}

