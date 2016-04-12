package com.gome.storefeedback.controller;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.constant.SapFeedbackConstant;
import com.gome.storefeedback.dao.impl.SapFeedbackDaoImpl;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.ResultNoReasonService;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.SapFeedbackPushService;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.util.AESAPPUtils;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.DateTimeUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.util.OauthUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * SAP缺断货信息Controller
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月23日下午3:57:12
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/appToWebFeedback")
public class AppToWebFeedbackController {

    private static final String BUHUO_YES = "1";
    private static final String BUHUO_NO = "0";
    private static final Integer SAPFEEDBACK_HANDLER_YES = 1;
    private static final Integer SAPFEEDBACK_HANDLER_NO = 0;

    private static final Logger logger = LoggerFactory.getLogger(AppToWebFeedbackController.class);

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
    Map<String, Object>  getByEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                    }
                    inMap.put("categoryCodes", pmap.get("categoryCodes"));
                    inMap.put("dataDate", today());
                    inMap.put(SapFeedbackDaoImpl.SELECT_KEY, SapFeedbackDaoImpl.CATEGORYSUM_VALUE);
                    Page page = this.sapFeedbackService.findByParams(inMap, null);
                    
                    if (null == page) {
                        result.put("total", 0);
                        result.put("rows", new ArrayList());
                    } else {
                        result.put("total", page.getTotalSize());
                        result.put("rows", page.getDataList());
                    }
                    //Page page_y = this.sapFeedbackService.findByParams(inMap, null);
                   // AppUtil.succussHandler(page,page_y, result, pmap.get(BUY_TYPE_AUTH_ROLE).toString().trim());
                } else {
                   // AppUtil.succussHandler(new Page(),null, result, BUY_TYPE_AUTH_ROLE_NO);
                }
                /*logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));*/
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
               // AppUtil.exceptionHandler(result);
            }
        }
        return result;
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
                    Page page = this.sapFeedbackService.findByParams(inMap, AppUtil.getPaginationParameters(pmap));
                    Page page_y  = null;
                    if(page==null || page.getDataList().size()<10 ){
                    	inMap.put("isHandler", "1");
                    	Map<String, Object> pmap_y = new HashMap<String, Object>();
                    	PaginationParameters param = AppUtil.getPaginationParameters(pmap_y);
                    	/*if(page !=null){
                    		param.setPageMaxSize(10-page.getDataList().size());
                    	}*/
                    	page_y = this.sapFeedbackService.findByParams(inMap, param);
                    }
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
    @RequestMapping(value = "/insertResult", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody
    String insertResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
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
                            for (String orderId : orders) {
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
                            }
                            // 补货处理
                            List<SapFeedbackHandler> resultList = new ArrayList<SapFeedbackHandler>();
                            getHandlerResult(resultList, pmap);
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
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
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

    private static void getHandlerResult(List<SapFeedbackHandler> resultList, Map<String, Object> pmap) {
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

        if (ids != null && ids.size() > 0) {
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
            pmap.put("employeeId", eMap.get("employeeId"));
            pmap.put("companyId", eMap.get("companyId"));
            pmap.put("employeeName", eMap.get("employeeName"));
            pmap.put("companyCode", eMap.get("companyCode"));
            pmap.put("storeFeedbackUserId", eMap.get("id"));
            return true;
        }
    }
}