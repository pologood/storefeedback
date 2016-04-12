package com.gome.storefeedback.controller.apptoweb;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.helpers.JavaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackAppealCl;
import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.entity.SapFeedbackCheckPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.jms.SapFeedbackPushSender;
import com.gome.storefeedback.service.CheckFeedbackPushService;
import com.gome.storefeedback.service.CheckSapFeedbackService;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackAppealService;
import com.gome.storefeedback.service.FeedbackCheckConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.FeedbackToOaService;
import com.gome.storefeedback.service.ResultNoReasonService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToDisplayService;
import com.gome.storefeedback.service.SapFeedbackCheckPushService;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.CollectionUtil;
import com.gome.storefeedback.util.DateTimeUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.util.OauthUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 缺断货考核功能在web端展现
 * @author zhaozhenxiu
 * @date 2015年11月2日下午4:4:12
 */
@Controller
@RequestMapping("/checkSapFeedback")
public class CheckSapFeedbackWebController {
	private static final String BUHUO_YES = "1";
    private static final String BUHUO_NO = "0";
    private static final Integer SAPFEEDBACK_HANDLER_YES = 1;
    private static final String  RESULT_FLAG = "resultFlag";

    private static final Logger logger = LoggerFactory.getLogger(SapFeedbackController.class);

    @Resource
    private FeedbackPushCategoryPositionConfigService feedbackPushCategoryPositionConfigService;

    @Resource
    private SapFeedbackHandlerService sapFeedbackHandlerService;
    @Resource
    private FeedbackToOaService feedbackToOaService;
    
    @Resource
    private FeedbackAppealService feedbackAppealService;
    
    @Resource
    private EmployeeRemoteService employeeRemoteService;

    @Resource
    private CheckSapFeedbackService checkSapFeedbackService;

    @Resource
    private LogMessageSender logMessageSender;

    @Resource
    private ResultNoReasonService resultNoReasonService;

    @Resource
    private SapOrderService sapOrderService;
    
    @Resource
    private SapFeedbackPushSender SapFeedbackCheckPushSender;
    @Resource
    private SapFeedbackCheckPushService sapFeedbackCheckPushService;
    
    @Resource FeedbackCheckConfigService feedbackCheckConfigService;
    
    @Resource
    private SapFeedbackService sapFeedbackService;
    @Resource
    private CheckFeedbackPushService checkFeedbackPushService;
    
    private static final String GSM_TYPE_112 = "112";
    private static final String GSM_MSG = "缺断货考核通知";
    private static final String GOME_GROUP = "GMZB";
    private static final String BUY_TYPE_AUTH = "buy_type_auth";
    private static final String ROLE_ID = "roleId";
    private static final String BUY_TYPE_AUTH_ROLE_NO = "00000000";
    
    private static final String ORG_NUMBER = "orgNumber";
    private static final String EMP_NUMBER = "empNumber";
    private static final String CATEGEGORY_CODES = "categoryCodes";
    private static final String BRAND_CODES  = "brandCodes";
    
    
	/**
	 * 到  sapCheckWeek 页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/goToCheckWeekPage")
	public ModelAndView goToCheckWeekPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
        	if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
                if(pmap.get(BUY_TYPE_AUTH) != null){
                	request.setAttribute("buyTypeAuthRole", pmap.get(BUY_TYPE_AUTH));
                }
        	}
        }
        mv.setViewName("appmain/checkFeedback/sapCheckWeek");
        return mv;
    }
	
    
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
        		String roleId = pmap.get("roleId").toString();
                if("10110100".equals(roleId)||"11111100".equals(roleId)){
                	request.setAttribute("roleMessage", "您没有考核相关信息");
                }
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
                if(pmap.get(BUY_TYPE_AUTH) != null){
                	request.setAttribute("buyTypeAuth", pmap.get(BUY_TYPE_AUTH));
                }
                request.setAttribute("year", pmap.get("year"));
                request.setAttribute("month", pmap.get("month"));
                request.setAttribute("weekCount", pmap.get("weekCount"));
        	} else {
        		result.put("check_message", "没有可处理的考核信息");
        		request.setAttribute("checkMessage", "没有可处理的考核信息");
            	logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
    					JsonUtil.javaObjectToJsonString(result)));
        	}
        }
        mv.setViewName("appmain/checkFeedback/category");
        return mv;
    }

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
        if (requestHandlernew(request, pmap, result)) {
        	String fridayDate = getWeekFriday((String)pmap.get("year"),(String)pmap.get("month"),(String)pmap.get("weekCount"));
        	if(compareWithFirdayDate(fridayDate,pmap)){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        		Date date = new Date();
        		String sysTime = sdf.format(date);
        		if(sysTime.equals(fridayDate)){
        			result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11062);
        			result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11062);
                	logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
        					JsonUtil.javaObjectToJsonString(result)));
                	logger.info(JsonUtil.javaObjectToJsonString(result));
                	return JsonUtil.javaObjectToJsonString(result);
        		}
        		request.getSession().setAttribute("fridayDate", fridayDate);
	            try {
	                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
	                	
	                		Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
	                		if(pmap.get(ROLE_ID) != null){
	                			inMap.put(ROLE_ID, pmap.get(ROLE_ID).toString().trim());
	                		}else{
	                			result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11057);
	                        	logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
	                					JsonUtil.javaObjectToJsonString(result)));
	                        	logger.info(JsonUtil.javaObjectToJsonString(result));
	                            return JsonUtil.javaObjectToJsonString(result);
	                		}
	                		inMap.put(ORG_NUMBER, pmap.get("divisionCode"));
	                		inMap.put(BUY_TYPE_AUTH, pmap.get(BUY_TYPE_AUTH));
	                		inMap.put(CATEGEGORY_CODES, pmap.get(CATEGEGORY_CODES));
	                		inMap.put("dataDate", fridayDate);
	                		Page page = new Page();
	                		List pageList = new ArrayList();
	                		Map<String,Object> positionMap = new HashMap<String,Object>(); 
	                    	positionMap.put(ORG_NUMBER, pmap.get("divisionCode"));
	                        positionMap.put(EMP_NUMBER, pmap.get(EMP_NUMBER));
	                        //关联考核配置
	                        List<FeedbackCheckConfig> empList = feedbackCheckConfigService.findCheckEmpList(positionMap);

	                        Set<String> categorys = new HashSet<String>();
	                        if (empList != null && empList.size() > 0) {
	                            for (FeedbackCheckConfig config : empList) {
	                            	String categoryCode = config.getCategoryCode();
	                            	if(categorys.contains(categoryCode)){
	                            		continue;
	                            	}else{
	                            		Set<String> category = new HashSet<String>();
	                            		Set<String> brandCode = new HashSet<String>();
	                            		for (FeedbackCheckConfig config1 : empList) {
	                            			categorys.add(categoryCode);
	                            			category.add(categoryCode);
	                            			String categoryCode1 = config1.getCategoryCode();
	                            			if(categoryCode.equals(categoryCode1)){
	                            				brandCode.add(config1.getBrandCode());
	                            			}
	                            		}
	                            		inMap.put(CATEGEGORY_CODES, category);
	                            		inMap.put(BRAND_CODES, brandCode);
	                            		inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.CATEGORYSUM_VALUE);
	        	                		page = this.checkSapFeedbackService.findByParams(inMap, null);
	        	                		List dataList = page.getDataList();
	        	                		for (Object object : dataList) {
											pageList.add(object);
										}
	                            	}            	
	                            }
	                        } else {
	                        	inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.CATEGORYSUM_VALUE);
	                        	page = this.checkSapFeedbackService.findByParams(inMap, null);
	                        	pageList = page.getDataList();
	                        }
	                		
	                		if (null == page) {
	                			result.put("total", 0);
	                			result.put("rows", new ArrayList());
	                		} else {
//	                			List list = pageList;
//	                			List list1 = new ArrayList();
//	                			for (Object object : list) {
//	                				list1.add(object);
//	                				list1.add(object);
//	                			}
//	                			if(pmap.get(BUY_TYPE_AUTH)!= null){
//	                				if("0".equals(pmap.get(BUY_TYPE_AUTH).toString().trim())){
	                					result.put("rows", pageList);
//	                				}else if("1".equals(pmap.get(BUY_TYPE_AUTH).toString().trim())){
//	                					result.put("rows", list1);
//	                				}
//	                			}
	                			result.put("total", pageList.size());
	                		}
	                	}
	                } catch (BaseException e) {
	                logger.error(e.getMessage(), e);
	                AppUtil.exceptionHandler(result);
	            }
        	} else {
        		result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11055);
            	result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11055);
            	result.put("rows", new ArrayList());
            	logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
    					JsonUtil.javaObjectToJsonString(result)));
        	}
        }
        
        logger.info(JsonUtil.javaObjectToJsonString(result));
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
	@RequestMapping(value = "/goToCategoryBrandPage", produces = "text/plain;charset=UTF-8" )
	public ModelAndView goToCategoryBrandPage(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {

		ModelAndView mv = new ModelAndView();
        map.put("categoryCode", request.getParameter("categoryCode"));
        map.put("categoryName", request.getParameter("categoryName"));
        map.put("buyType", request.getParameter("buyType"));
        mv.setViewName("appmain/checkFeedback/categoryBrand");
        return mv;
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
        if (requestHandlernew(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                    Map<String, Object> inMap = AppUtil.getInMap(pmap, //
                            "categoryCode",//
                            "buyType",//
                            "divisionCode");
                    inMap.put(ORG_NUMBER, pmap.get("divisionCode"));
//                    inMap.put(EMP_NUMBER, pmap.get(EMP_NUMBER));
                    inMap.put(BUY_TYPE_AUTH, pmap.get(BUY_TYPE_AUTH));
                    //inMap.put(CATEGEGORY_CODES, pmap.get(CATEGEGORY_CODES));
                    if(pmap.get(ROLE_ID)!= null && pmap.get(ROLE_ID) != ""){
                    	inMap.put(ROLE_ID, pmap.get(ROLE_ID).toString().trim());
                    }
                    inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.BRANDSUM_VALUE);
                    inMap.put("dataDate", request.getSession().getAttribute("fridayDate"));
                    
                    Map<String,Object> positionMap = new HashMap<String,Object>(); 
                	positionMap.put(ORG_NUMBER, pmap.get("divisionCode"));
                    positionMap.put(EMP_NUMBER, pmap.get(EMP_NUMBER));
                    
                    //关联考核配置
                    List<FeedbackCheckConfig> empList = feedbackCheckConfigService.findCheckEmpList(positionMap);

                    String categoryCode = (String) pmap.get("categoryCode");
                    Set<String> brandCode = new HashSet<String>();
                    if (empList != null && empList.size() > 0) {
                        for (FeedbackCheckConfig config : empList) {
                        	String categoryCode1 = config.getCategoryCode();
                        	if(categoryCode1.equals(categoryCode) ){
                        		brandCode.add( config.getBrandCode());
                        	}
                        }
                        if(!brandCode.isEmpty()){
                        	inMap.put(BRAND_CODES,brandCode);
                        }
                    } 
                    Page page = this.checkSapFeedbackService.findByParams(inMap, AppUtil.getPaginationParameters(pmap));
                    if (null == page) {
                        result.put("total", 0);
                        result.put("rows", new ArrayList());
                    } else {
                        result.put("total", page.getDataList().size());
                        result.put("rows", page.getDataList());
                    }
                    /*pmap.put("isHandler", "1");
                    Page page_y = this.checkSapFeedbackService.findByParams(inMap, AppUtil.getPaginationParameters(pmap));*/
                    if(pmap.get(ROLE_ID)!= null){
                    	AppUtil.succussHandler(page,null, result, pmap.get(ROLE_ID).toString().trim());
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
    @RequestMapping(value = "/goToCategoryBrandGoodsPage", produces = "text/plain;charset=UTF-8")
	public ModelAndView goToCategoryBrandGoodsPage(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map) throws Exception {
    	
    	ModelAndView mv = new ModelAndView();
        map.put("categoryCode", request.getParameter("categoryCode"));
        map.put("buyType", request.getParameter("buyType"));
        map.put("brandCode",request.getParameter("brandCode"));
        map.put("brandName",request.getParameter("brandName"));
        mv.setViewName("appmain/checkFeedback/categoryBrandGoods");
        return mv;
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
        if (requestHandlernew(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                    Map<String, Object> inMap = AppUtil.getInMap(pmap, //
                            "categoryCode",//
                            "buyType",//
                            "brandCode",//
                            "divisionCode");
                    inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.GOODSSUM_VALUE);
                    inMap.put("dataDate", request.getSession().getAttribute("fridayDate"));
                    inMap.put(ORG_NUMBER, pmap.get("divisionCode"));
                    inMap.put(BUY_TYPE_AUTH, pmap.get(BUY_TYPE_AUTH));
                    if(pmap.get(ROLE_ID)!= null && pmap.get(ROLE_ID) != ""){
                    	inMap.put(ROLE_ID, pmap.get(ROLE_ID).toString().trim());
                    }
                    PaginationParameters params = new PaginationParameters(request,response);
                    Page page = this.checkSapFeedbackService.findByParams(inMap, this.getPaginationParameters(pmap));
                    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                    int num = 0;
                    if(page != null){
                    	List dataList = page.getDataList();
                    	for (Object object : dataList) {
                    		boolean flag = true;
                    		boolean flag2 = true;
                    		boolean flag3 = true;
                    		boolean flag4 = true;
                    		boolean flag5 = true;
//                    		String isHandler = "";
							Map<String,Object> map = (Map<String, Object>) object;
							inMap.put("goodsCode", map.get("goodsCode"));
							inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.DIVISIONSUM_VALUE);
							List dataList2 = this.checkSapFeedbackService.findByParams(inMap, null).getDataList();
							for (Object object2 : dataList2) {
								Map<String,Object> map2 = (Map<String, Object>) object2;
								String isHandler = (String) map2.get("is_handler");

								if("1".equals(isHandler)){//确认
									flag2 = false;
								}
								if("3".equals(isHandler)){//申诉未审批
									flag3 = false;
								}
								if("4".equals(isHandler)){//申诉同意
									flag4 = false;
								}
								if("2".equals(isHandler)){//申诉同意
									flag5 = false;
								}
							}
							if(!flag2 & flag3 & flag4 & flag5){
								map.put("is_handler", 5);//全部是已经确认的red
							}else if(!flag4 & flag2 & flag3 & flag5){
								map.put("is_handler", 2);//全部是申诉同意的green
							}else if(!flag2 & !flag4 & flag3 & flag5){
								map.put("is_handler", 3);//全部处理且red+green=blue
							}else if(!flag3 & flag5){
//							}else if((!flag3 & flag4 & flag5 & flag2) || (!flag3 & flag4 & flag5 & !flag2)){
								map.put("is_handler", 4);//全部申诉未审批
							}else {
								map.put("is_handler", 1);
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
    @RequestMapping(value = "/goToCategoryBrandGoodsDivisionPage", produces = "text/plain;charset=UTF-8")
	public ModelAndView goToCategoryBrandGoodsDivisionPage(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map) throws Exception {
    	
    	ModelAndView mv = new ModelAndView();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
        	if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode");
                if(pmap.get(ROLE_ID) != null){
                	request.setAttribute("buyTypeAuthRole", pmap.get(ROLE_ID));
                }
        	}
        }
        String lastFridayDate = this.getLastFridayDate();
    	String fridayDate = (String) request.getSession().getAttribute("fridayDate");
    	if(lastFridayDate.equals(fridayDate)){
    		//result.put("flag", 1);——返回客户端是否能处理，1代表能处理，0代表不能处理
    		request.setAttribute("flag", 1);
    	}else{
//    		result.put("flag", 0);
    	}
        map.put("categoryCode", request.getParameter("categoryCode"));
        map.put("buyType", request.getParameter("buyType"));
        map.put("brandCode",request.getParameter("brandCode"));
        map.put("goodsCode",request.getParameter("goodsCode"));
        map.put("goodsName",request.getParameter("goodsName"));
//        request.getParameter("dataparkId");
//        request.getParameter("record");
        map.put("goodsId",request.getParameter("goodsId")+ "#" +request.getParameter("dataparkId")+ "#" +request.getParameter("record"));
        map.put("reason", JsonUtil.jsonStringToMap(this.getReplenishmentNOReason(request, response)));
        mv.setViewName("appmain/checkFeedback/categoryBrandGoodsDivision");

        return mv;
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
        Map<String, Object> data = new HashMap<String, Object>();
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
                    if(pmap.get(ROLE_ID)!= null && pmap.get(ROLE_ID) != ""){
                    	inMap.put(ROLE_ID, pmap.get(ROLE_ID).toString().trim());
                    }
                    inMap.put("dataDate", request.getSession().getAttribute("fridayDate"));
                    inMap.put(ORG_NUMBER, pmap.get("divisionCode"));
                    inMap.put(BUY_TYPE_AUTH, pmap.get(BUY_TYPE_AUTH));
                    Page page = this.checkSapFeedbackService.findByParams(inMap, null);
                    if (null == page) {
                        result.put("total", 0);
                        result.put("rows", new ArrayList());
                    } else {
                        result.put("total", page.getDataList().size());
                        result.put("rows", page.getDataList());
                    }
                    /*pmap.put("isHandler", "1");
                    Page page_y = this.checkSapFeedbackService.findByParams(inMap, null);*/
                    if(pmap.get(ROLE_ID)!= null){
                    	AppUtil.succussHandler(page,null, result, pmap.get(ROLE_ID).toString().trim());
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
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return JsonUtil.javaObjectToJsonString(result);
    }
    
    
    /**
     * 根据员工域账号查询员工姓名
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEmpByAcccount", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getEmpByAcccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                	Employee emp = employeeRemoteService.findEmpByAdAccount(pmap.get("empAcccount").toString().trim().toLowerCase());
                	if(emp != null){
                		data.put("empNum", emp.getEmployeeId());
                		data.put("empName", emp.getEmployeeName());
                		data.put("empAcccount", pmap.get("empAcccount").toString());
                	}
                } 
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        result.put("result", data);
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return JsonUtil.javaObjectToJsonString(result);
    }
    
    
    /**
     * 页面跳转接口
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUrlByAccesstoken", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getUrlByAccesstoken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                	
                	if(pmap.get("roleId") != null && (pmap.get("roleId").toString().trim().equals("11111100")|| pmap.get("roleId").toString().equals("10110100"))){
                			data.put("is_leader", "1");//是否领导
                	}else{
                		data.put("is_leader", "0");
                	}
                } 
                
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        result.put("result", data);
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return JsonUtil.javaObjectToJsonString(result);
    }

    
    /**
     * 缺断货考核审批处理
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getRppealList", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getRppealList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        List<FeedbackAppealCl> data = new ArrayList<FeedbackAppealCl>();
        Map<String, Object> newdata = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
            try {
                if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
                	
                	if(pmap.get("buy_type_auth_role") != null){
                		if(pmap.get("buy_type_auth_role").toString().equals("11111100") || pmap.get("buy_type_auth_role").toString().equals("10110100")){
                			List<FeedbackAppeal> list  = feedbackAppealService.findFeedbackAppealList(pmap);
                			for(FeedbackAppeal appeal : list){
                				FeedbackAppealCl appcl = new FeedbackAppealCl();
                				appcl.setId(appeal.getId());
                				appcl.setAppealnum(appeal.getAppealnum());
                				appcl.setAppealName(appeal.getAppealName());
                				appcl.setAppealModel(appeal.getAppealModel());
                				appcl.setAppealAcccount(appeal.getAppealAcccount());
                				appcl.setAppealReason(appeal.getAppealReason());
                				data.add(appcl);
                			}
                			newdata.put("data", data);
                			/*JSONArray ja = JSONArray.fromObject(list);
                			data.put("data", ja);*/
                		}else{
                			newdata.put("is_leader", "0");//业务人员登录 
                		}
                	}
                } 
                
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        result.put("result", newdata);
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return JsonUtil.javaObjectToJsonString(result);
    }
    
    
    /**
     * 缺断货考核结果确认
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/confirmResult", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    String confirmResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (requestHandlernew(request, pmap, result)) {
            try {
            	
            	if (getEmpInfoFromRPC(pmap, BUY_TYPE_AUTH, "divisionCode")) {
            		List<String> ids = (List<String>) pmap.get("ids");
    				if(ids == null || ids.size() <= 0){
    					result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11053);
           				result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11053);
           			   return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    				   }
            		// 数据校验
            		if ( pmap.get(RESULT_FLAG)==  null) {
            			AppUtil.exceptionHandler(result);
            		}
            		if (pmap.get(RESULT_FLAG).toString().trim().equals(BUHUO_NO)) {//申诉处理
            			if (pmap.get("appealReason") == null) {//申诉原因不能为空
            				result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E11052);
            				result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M11052);
            			}else{
            				List<FeedbackAppeal> resultList = new ArrayList<FeedbackAppeal>();
            				
            				getFeedbackAppealHandlerResult(resultList, pmap);//组装实体 --批量插入数据
            				feedbackAppealService.insertBatch(resultList);
            				checkSapFeedbackService.updateBatchSapFeedbackHisByAppeal(resultList);//批量更新考核推送历史表--是否处理(申诉)
            				
            			//  statr  给领导推送业务人员申诉信息
            				List<SapFeedbackCheckPush> sendMessages =new ArrayList<SapFeedbackCheckPush>();
            				for(FeedbackAppeal appeal : resultList){
            							//SapFeedbackCheckPush pushRecord = getSendMessage(toA);
            							
            							SapFeedbackCheckPush sendMessage = new SapFeedbackCheckPush();
            					    	 sendMessage.setId(UUIDUtil.getUUID());
            						        //sendMessage.setPushCategory(feedback.getPushCategory());
            						        sendMessage.setPushContent("您有一条缺断货考核信息");
            						        sendMessage.setPushDataDate(new Date());
            						        sendMessage.setPushEmpId(appeal.getAppealnum());
            						        sendMessage.setPushEmpName(appeal.getAppealName());
            						        sendMessage.setPushEmpNumber(appeal.getAppealnum());
            						        //sendMessage.setPushOrg(feedback.getPushOrg());
            					            // SapFeedbackCheckPush.setPushPosition(pushPosition);
            						       // sendMessage.setPushTime(pushTimeStamp);
            						        sendMessage.setRoleId(SapFeedbackConstant.GMFB_BUSINESS);
            						        
            							sapFeedbackCheckPushService.insertSapFeedbackCheckPush(sendMessage);
            							sendMessages.add(sendMessage);//组装推送信息
            							}
            						if (!CollectionUtil.isEmpty(sendMessages)) {
            							for (SapFeedbackCheckPush sendMessage : sendMessages) {
            								this.sendMessage(sendMessage.getPushEmpId(), "您有一个缺断货申诉信息需要审批");
            							}
            						}
            				// end 给领导推送业务人员申诉信息
            						
            				AppUtil.succussHandler(result);
            			}
            		} else if (pmap.get(RESULT_FLAG).toString().trim().equals(BUHUO_YES)) {//确认处理
            			try {
            				List<FeedbackToOa> resultList = new ArrayList<FeedbackToOa>();
            				 getFeedbackToOaHandlerResult(resultList, pmap);
            				feedbackToOaService.insertBatch(resultList);
            				checkSapFeedbackService.updateBatchSapFeedbackHisByToOa(resultList);//批量更新考核推送历史表--是否处理(确认)
            				AppUtil.succussHandler(result);
            			} catch (Exception e) {
            				AppUtil.exceptionHandler(result);
            			}
            		} else {
            			AppUtil.exceptionHandler(result);
            		}
            		logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
            				JsonUtil.javaObjectToJsonString(result)));
            	}
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        String resultJson=JsonUtil.javaObjectToJsonString(result);
        logger.info(resultJson);
        return resultJson;
    }
    
   
	
    
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

    private static SapFeedbackCheckPush getSendMessage(FeedbackToOa feedback, String content) {
    	SapFeedbackCheckPush sendMessage = new SapFeedbackCheckPush();
    	 sendMessage.setId(UUIDUtil.getUUID());
	        //sendMessage.setPushCategory(feedback.getPushCategory());
	        sendMessage.setPushContent(content);
	        sendMessage.setPushDataDate(new Date());
	        sendMessage.setPushEmpId(feedback.getEmpnum());
	        sendMessage.setPushEmpName(feedback.getName());
	        sendMessage.setPushEmpNumber(feedback.getEmpnum());
	        //sendMessage.setPushOrg(feedback.getPushOrg());
            // SapFeedbackCheckPush.setPushPosition(pushPosition);
	       // sendMessage.setPushTime(pushTimeStamp);
	        sendMessage.setRoleId(SapFeedbackConstant.GMFB_BUSINESS);
	        return sendMessage;
    }
    
     
    
    /**
     * 缺断货考考核 申诉审批
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/examineResult", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody
	String examineResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> pmap = new HashMap<String, Object>();
		if (requestHandlernew(request, pmap, result)) {
			try {
				// 数据校验
				if (pmap.get(RESULT_FLAG) == null) {
					AppUtil.exceptionHandler(result);
				}
				List<FeedbackAppeal> resultList = new ArrayList<FeedbackAppeal>();
				getHandlerResult(resultList, pmap);
				for (FeedbackAppeal feedbackAppeal : resultList) {
					if (pmap.get(RESULT_FLAG).toString().trim().equals(BUHUO_NO)) {
						feedbackAppeal.setIsHandler(SAPFEEDBACK_HANDLER_YES);
						feedbackAppeal.setHandlerResult(0);//申诉拒绝
					}else{
						feedbackAppeal.setIsHandler(SAPFEEDBACK_HANDLER_YES);
						feedbackAppeal.setHandlerResult(1);//申诉通过
					}
					feedbackAppeal.setHandlerName(pmap.get("employeeId").toString());
					feedbackAppeal.setHandlerNumber(pmap.get("employeeName").toString());
					feedbackAppeal.setHandlerTime(new Date());
					feedbackAppealService.updateByPrimaryKey(feedbackAppeal);//   修改申诉信息
					
					if(feedbackAppeal.getHandlerResult() == 0){
						feedbackAppeal.setHandlerResult(2);
					} else if(feedbackAppeal.getHandlerResult() == 1){
						feedbackAppeal.setHandlerResult(4);
					}
					checkSapFeedbackService.updateSapFeedbackHisByAppeal(feedbackAppeal);//更新缺断货考核历史记录表——(申诉同意OR拒绝)
					if (pmap.get(RESULT_FLAG).toString().trim().equals(BUHUO_NO)) {
						List<FeedbackToOa> resultoa = new ArrayList<FeedbackToOa>();
						
					        Calendar calendar=Calendar.getInstance();
					           FeedbackToOa sapFH = new FeedbackToOa();
			                	Employee emp = employeeRemoteService.findEmpByAdAccount(feedbackAppeal.getAppealAcccount()); 
			                	sapFH.setId(UUIDUtil.getUUID());
			                	sapFH.setDatapakid(feedbackAppeal.getDatapakid());
			                	sapFH.setRequest(feedbackAppeal.getRequest());
			                	sapFH.setRecord(feedbackAppeal.getRecord());
			                	sapFH.setName(emp.getEmployeeName());
			                	sapFH.setEmpnum(emp.getEmployeeId());
			                	sapFH.setSanctionMonth((calendar.get(Calendar.MONTH) + 1) + "");
			                	sapFH.setSanctionMoney("15");
			                	sapFH.setSanctionPoints("0");
			                    sapFH.setInsertTime(new Date());
			                    resultoa.add(sapFH);
					        
						feedbackToOaService.insertBatch(resultoa);// 将申诉 被驳回的信息添加至 oa推送表
						
						
						//  statr  申诉信息被领导驳回
        				List<SapFeedbackCheckPush> sendMessages =new ArrayList<SapFeedbackCheckPush>();
        				for(FeedbackToOa toA : resultoa){
        							SapFeedbackCheckPush pushRecord = getSendMessage(toA,"您有一条缺断货申诉信息被驳回");
        							sapFeedbackCheckPushService.insertSapFeedbackCheckPush(pushRecord);
        								sendMessages.add(pushRecord);//组装推送信息
        							}
        						if (!CollectionUtil.isEmpty(sendMessages)) {
        							for (SapFeedbackCheckPush sendMessage : sendMessages) {
        								this.sendMessage(sendMessage.getPushEmpId(), "您有一条缺断货申诉信息被驳回");
        							}
        						}
        				// end 申诉信息被两道驳回
        						
					}
					
				AppUtil.succussHandler(result);
				}
				logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(), JsonUtil.javaObjectToJsonString(result)));
			} catch (BaseException e) {
				logger.error(e.getMessage(), e);
				AppUtil.exceptionHandler(result);
			}
		}
		String resultJson = JsonUtil.javaObjectToJsonString(result);
		logger.info(resultJson);
		return resultJson;
	}
    

    
    private static void getHandlerResult(List<FeedbackAppeal> resultList, Map<String, Object> pmap) {
        List<String> ids = (List<String>) pmap.get("ids");
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
            	FeedbackAppeal sapFH = id2PK_toOa(id);
                if (sapFH != null) {
                    resultList.add(sapFH);
                }
            }
        }
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

    @Resource
    private SapFeedbackCheckEmpToDisplayService sapFeedbackCheckEmpToDisplayService;
    
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
                        pmap.put(EMP_NUMBER, emp.getEmployeeId());
                        
                        positionMap.put("positionCode", positionCode);
                        positionMap.put(ORG_NUMBER, divisionCode);
                        positionMap.put(EMP_NUMBER, emp.getEmployeeId());
                        
                        //关联考核配置
//                        List<FeedbackCheckConfig> empList = feedbackCheckConfigService.findCheckEmpList(positionMap);
//
//                        Set<String> brandCode = new HashSet<String>();
//                        if (empList != null && empList.size() > 0) {
//                            for (FeedbackCheckConfig config : empList) {
//                            	categorys.add(config.getCategoryCode());
//                            	brandCode.add(config.getBrandCode());
//                            }
//                            pmap.put(CATEGEGORY_CODES, categorys);
//                            pmap.put(BRAND_CODES, brandCode);
//                            pmap.put(ROLE_ID, empList.get(0).getRoleId());
//                            return true;
//                        }
                        
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
                                pmap.put("roleId", roleId);
                            } else {
                                pmap.put("roleId", BUY_TYPE_AUTH_ROLE_NO);
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
                pmap.put(CATEGEGORY_CODES, categorys);
                return true;
            } else {
                throw new BaseException("获取部门编码失败");
            }
        }
        return false;
    	
//    	if (params != null && params.length > 0) {
//    		Set<Integer> roleIds = new HashSet<Integer>();
//            Set<String> categorys = new HashSet<String>();
//    		
//            String empId = pmap.get("storeFeedbackUserId").toString().trim();
//            Employee emp = employeeRemoteService.findEmployeeById(empId);
//            Map<String, Object> positionMap = new HashMap<String, Object>();
//            if (emp != null && emp.getPositions() != null && emp.getPositions().size() > 0) {
//                List<Position> positions = emp.getPositions();
//                for (Position position : positions) {
//                    if (position.getPositionCode().length() >= 10) {
//                        String divisionCode = position.getPositionCode().substring(0,position.getPositionCode().length() - 10);
//                        String positionCode = position.getPositionCode().substring(position.getPositionCode().length() - 10, position.getPositionCode().length());
//                        if (isGomeGroup(divisionCode)) {
//                            pmap.put(BUY_TYPE_AUTH, "1");
//                        } else {
//                            pmap.put(BUY_TYPE_AUTH, "0");
//                        }
//                        pmap.put("positionCode", positionCode);
//                        pmap.put(ORG_NUMBER, divisionCode);
//                        pmap.put(EMP_NUMBER, emp.getEmployeeId());
//                        
//                        positionMap.put("divisionCode", divisionCode);
//                        positionMap.put(EMP_NUMBER, emp.getEmployeeId());
//                        
////                        positionMap.put("positionCode", positionCode);
//                        
//                        List<SapFeedbackCheckEmp> empList = sapFeedbackCheckEmpToDisplayService.getAllDisplayByEmp(positionMap);
//                        Set<String> categoryCode = new HashSet<String>();
//                        Set<String> brandCode = new HashSet<String>();
//                        if (empList != null && empList.size() > 0) {
//                            for (SapFeedbackCheckEmp sapFeedbackCheckEmp : empList) {
//                            	categoryCode.add(sapFeedbackCheckEmp.getCategoryCode());
//                            	brandCode.add(sapFeedbackCheckEmp.getBrandCode());
//                            }
//                            pmap.put(CATEGEGORY_CODES, categoryCode);
//                            pmap.put(BRAND_CODES, brandCode);
//                            pmap.put(ROLE_ID, empList.get(0).getRoleId());
//                        } else {
//                        	return false;
//                        }
                    	//关联缺断货配置
//                    	List<FeedbackPushCategoryPositionConfig> pushList = feedbackPushCategoryPositionConfigService.findPushPosition(positionMap);
//                    	if (pushList != null && pushList.size() > 0) {
//                    		for (FeedbackPushCategoryPositionConfig p : pushList) {
//                    			
//                    			roleIds.add(p.getRoleId());
//                    			if (p.getCategoryCode() != null) {
//                    				categorys.add(p.getCategoryCode());
//                    			}
//                    		}
//                    		if (roleIds != null && roleIds.size() == 1) {
//                    			Integer roleId = new ArrayList<Integer>(roleIds).get(0);
//                    			pmap.put(ROLE_ID, roleId);
//                    		}
//                    		categorys.addAll(categoryCode);
//                    		pmap.put(CATEGEGORY_CODES, categorys);
//                    	}
//                        
//                    	if(empList == null || empList.size() == 0 || pushList == null || pushList.size() == 0){
//                    		return false;
//                    	}
//                    } else {
//                        throw new BaseException("请给:"+emp.getEmployeeName()+"配置缺断货考核数据");
//                    }
//                }
//                pmap.put(ROLE_ID, emp.getEmployeeId());
//                return true;
//            } else {
//                throw new BaseException("请给:"+emp.getEmployeeName()+"配置缺断货考核数据");
//            }
//        }
//        return false;
    }
    
    /**
     * check 配置表没有配置的根据缺断货匹配
     * @param pmap
     * @param params
     * @return
     * @throws BaseException
     *//*
    private boolean getEmpInfoFromRPC1(Map<String, Object> pmap, String... params) throws BaseException {
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
                        pmap.put("orgNumber", divisionCode);
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
                                pmap.put(ROLE_ID, roleId);
                            } else {
                                pmap.put(ROLE_ID, BUY_TYPE_AUTH_ROLE_NO);
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
                pmap.put("sapCategoryCodes", categorys);
                return true;
            } else {
                throw new BaseException("获取部门编码失败");
            }
        }
        return false;
    }*/
  

    // private static final int GMZB_LEADER = 11111100;// 总部领导
    // private static final int GMZB_BUSINESS = 11001110;// 总部业务
    // private static final int GMFB_LEADER = 10110100;// 分部领导
    // private static final int GMFB_BUSINESS = 10100001;// 分部业务

    private static boolean isGomeGroup(String orgId) {
        if (orgId != null && GOME_GROUP.equals(orgId) || "W".equals(orgId.substring(0, 1))) {
            return true;
        }
        return false;
    }
    /**
     * 组装缺断货同步oa数据实体类
     * @param resultList
     * @param pmap
     */
    private static void getFeedbackToOaHandlerResult(List<FeedbackToOa> resultList, Map<String, Object> pmap) {
        List<String> ids = (List<String>) pmap.get("ids");
        Calendar calendar=Calendar.getInstance();
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
            	FeedbackToOa sapFH = id2PK(id);
                if (sapFH != null) {
                	
                	sapFH.setId(UUIDUtil.getUUID());
                	sapFH.setDept(pmap.get("companyId").toString());
                	sapFH.setName(pmap.get("employeeName").toString());
                	sapFH.setEmpnum(pmap.get("employeeId").toString());
                	sapFH.setSanctionMonth((calendar.get(Calendar.MONTH) + 1) + "");
                	sapFH.setSanctionMoney("15");
                	sapFH.setSanctionPoints("0");
                    sapFH.setInsertTime(new Date());
                    resultList.add(sapFH);
                }
            }
        }
    }
    /**
     * 组装缺断货 驳回处理结果
     * @param resultList
     * @param pmap
     */
    private static void getFeedbackAppealHandlerResult(List<FeedbackAppeal> resultList, Map<String, Object> pmap) {
        List<String> ids = (List<String>) pmap.get("ids");
        Calendar calendar=Calendar.getInstance();
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
            	FeedbackAppeal sapFH = id2PK_1(id);
                if (sapFH != null) {
                	
                	sapFH.setId(UUIDUtil.getUUID());
                	sapFH.setAppealnum(pmap.get("appealEmp").toString());
                	sapFH.setAppealName(pmap.get("appealName").toString());
                	sapFH.setAppealReason(pmap.get("appealReason").toString());
                	sapFH.setIsHandler(0);
                	sapFH.setAppealTime(new Date());
                	sapFH.setHandlerName(pmap.get("examineName").toString());
                	sapFH.setHandlerNumber(pmap.get("examineEmp").toString());
                	sapFH.setAppealModel(pmap.get("appealModel").toString());
                	sapFH.setAppealAcccount(pmap.get("appealAcccount").toString());
                    resultList.add(sapFH);
                }
            }
        }
        
    }
    private static FeedbackToOa id2PK(String id) {
    	FeedbackToOa feedbackToOa = new FeedbackToOa();
        String[] pk = id.split("#");
        if (pk != null && pk.length == 3) {
            if (pk[0] != null && pk[0].trim().length() > 0) {
                feedbackToOa.setRequest(pk[0]);
            }
            if (pk[1] != null && pk[1].trim().length() > 0) {
                feedbackToOa.setDatapakid(Long.parseLong((pk[1])));
            }
            if (pk[2] != null && pk[2].trim().length() > 0) {
                feedbackToOa.setRecord(Integer.parseInt(pk[2]));
            }
            return feedbackToOa;
        }
        return null;
    }
    private static FeedbackAppeal id2PK_1(String id) {
    	FeedbackAppeal feedbackAppeal = new FeedbackAppeal();
        String[] pk = id.split("#");
        if (pk != null && pk.length == 3) {
            if (pk[0] != null && pk[0].trim().length() > 0) {
            	feedbackAppeal.setRequest(pk[0]);
            }
            if (pk[1] != null && pk[1].trim().length() > 0) {
            	feedbackAppeal.setDatapakid(Long.parseLong((pk[1])));
            }
            if (pk[2] != null && pk[2].trim().length() > 0) {
            	feedbackAppeal.setRecord(Integer.parseInt(pk[2]));
            }
            return feedbackAppeal;
        }
        return null;
    }
    
    
    private static FeedbackAppeal id2PK_toOa(String id) {
    	FeedbackAppeal feedbackAppeal = new FeedbackAppeal();
        String[] pk = id.split("#");
        if (pk != null && pk.length == 4) {
            if (pk[0] != null && pk[0].trim().length() > 0) {
            	feedbackAppeal.setRequest(pk[0]);
            }
            if (pk[1] != null && pk[1].trim().length() > 0) {
            	feedbackAppeal.setDatapakid(Long.parseLong((pk[1])));
            }
            if (pk[2] != null && pk[2].trim().length() > 0) {
            	feedbackAppeal.setRecord(Integer.parseInt(pk[2]));
            }
            if (pk[3] != null && pk[3].trim().length() > 0) {
            	feedbackAppeal.setAppealAcccount(pk[3]);
            }
            return feedbackAppeal;
        }
        return null;
    }
    
//    private static String  today(){
//        Calendar c = Calendar.getInstance();  
//        c.setTime(new Date());  
//        c.add(c.DATE, -1);
//        Date temp_date = c.getTime();  
//        return DateTimeUtil.formatDate(temp_date);
//    }
    
    /**
     * 返回指定参数对应的日期
     * @param time
     * @return
     */
    private static String  today(String time){
		String[] strs = time.split("-");
		String year = strs[0];
		String month = strs[1];
		String weekCount = strs[2];
						
		return getWeekFriday(year, month, weekCount);
    }
    /**
     * 获取指定年、月、第几周 对应的日期
     * @param year
     * @param month
     * @param m_week
     * @return
     */
    public static String getWeekFriday(String year1,String month1,String m_week1)    {
    	int year = Integer.parseInt(year1);
    	int month = Integer.parseInt(month1) - 1;
    	int m_week = Integer.parseInt(m_week1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.WEEK_OF_MONTH, m_week);// 取得该周第一天的weekday(周日=1)                
		int firstDayofweek = c.getFirstDayOfWeek();// 该周第一天日期       		
		
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, year);
		c1.set(Calendar.MONTH, month);
		c1.set(Calendar.WEEK_OF_MONTH, m_week);
		c1.set(Calendar.DAY_OF_WEEK, firstDayofweek+5);
		Date d1 = new Date(c1.getTimeInMillis());
		return df.format(d1);
	}
    /**
     * 判断 所选择的 周数对应的周五日期  是否在当前日期之前
     * @param dateStr
     * @return
     */
    public boolean compareWithFirdayDate(String dateStr,Map<String,Object> pmap){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date date;
		boolean flag = false;
		try {
			date = sdf.parse(dateStr);
			if(date.before(new Date())){
				String fridayMonth = dateStr.substring(5, 7);
				String month = (String)pmap.get("month");
				
				if(Integer.parseInt(fridayMonth) == Integer.parseInt(month)){
					flag = true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return flag;
    }
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
    
    /**
     * 比较考核推送日期和当前日期之间相差几天
     * @param smdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate) throws ParseException{ 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(sdf.parse(smdate)); 
		long time1 = cal.getTimeInMillis(); 
		cal.setTime(sdf.parse(sdf.format(new Date()))); 
		long time2 = cal.getTimeInMillis(); 
		long between_days=(time2-time1)/(1000*3600*24); 

		return Integer.parseInt(String.valueOf(between_days)); 
	}
    
    public static PaginationParameters getPaginationParameters(Map<String, Object> pmap) {
        PaginationParameters param = new PaginationParameters();
        int loadCount = 500;
        
        param.setPageMaxSize(loadCount);
        
        return param;
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
            pmap.put("ids", ids);
//            orders.add(request.getParameter("order"));
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
            pmap.put("resultCode", request.getParameter("resultNoCode"));
            String resultName = request.getParameter("resultNoName");
            if(resultName != null){
            	pmap.put("resultName", resultName.replaceAll("[0-9]", ""));
            }
            pmap.put("orders", orders);
            pmap.put("empAcccount", request.getParameter("empAcccount"));
            pmap.put("appealName", request.getParameter("appealName"));
            pmap.put("appealEmp", request.getParameter("appealEmp"));
            pmap.put("appealAcccount", request.getParameter("appealAccount"));
            pmap.put("appealReason", request.getParameter("appealReason"));
            pmap.put("appealModel", request.getParameter("appealModel"));
            pmap.put("examineName", request.getParameter("examineName"));
            pmap.put("examineEmp", request.getParameter("examineEmp"));
            pmap.put("examineAcccount", request.getParameter("examineAccount"));
            pmap.put("year", request.getParameter("year"));
            pmap.put("month", request.getParameter("month"));
            pmap.put("weekCount", request.getParameter("weekCount"));
            return true;
        }
    }
}
