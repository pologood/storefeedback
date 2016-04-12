package com.gome.storefeedback.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.controller.app.SapFeedbackController;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
import com.mysql.fabric.xmlrpc.base.Array;

public class AppUtil {

    private static final Logger logger = LoggerFactory.getLogger(AppUtil.class);

    public static final List DATA_LIST_NULL = new ArrayList();
    public static final String DATA_ENTITY_NULL = "";

    public static void succussHandler(Page page, Map<String, Object> result) throws BaseException {
        if (page != null) {
            result.put("result", page.getDataList());
        } else {
            result.put("result", DATA_LIST_NULL);
        }
        // String resultStr = JsonUtil.javaObjectToJsonString(result);
    }

    public static void succussHandler(Page page,Page page_y, Map<String, Object> result, String auth) throws BaseException {
    	Map<String, Object> data = new HashMap<String, Object>();
        if (page != null) {
            if(page.getDataList()!=null&&page.getDataList().size()>0){
                data.put("data", page.getDataList());
            }else{
                data.put("data", new ArrayList());
            }
            data.put("auth", auth);
            result.put("result", data);
        }else {
            result.put("result", DATA_LIST_NULL);
        }
        if (page == null) {
           data.put("data", new ArrayList());
        }
        if(page_y != null){
      	   if(page_y.getDataList()!=null&&page_y.getDataList().size()>0){
                 data.put("data_y", page_y.getDataList());
             }
      	 data.put("auth", auth);
      	 result.put("result", data);
         }
        if (page_y == null) {
            data.put("data_y", new ArrayList());
         }
    }

    public static void succussHandler(Map<String, Object> result) throws BaseException {
        result.put("result", "success");
    }

    public static void exceptionHandler(Map<String, Object> result) {
        result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E10001);
        result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M10001);
    }

    public static boolean requestHandler(HttpServletRequest request, Map<String, Object> pmap,
            Map<String, Object> result) throws BaseException {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap = JsonUtil.jsonStringToMap(AESAPPUtils.decryptAES(request
                .getParameter(BusinessGlossary.APP_STOREFEEDBACK)));
        String accesstoken = (String) hashMap.get(BusinessGlossary.ACCESS_TOKEN);
        Iterator<Map.Entry<String, Object>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            pmap.put(entry.getKey(), entry.getValue());
        }
        logger.info(request.getRequestURL().toString());
        logger.info(JsonUtil.javaObjectToJsonString(pmap));
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

    public static PaginationParameters getPaginationParameters(Map<String, Object> pmap) {
        PaginationParameters param = new PaginationParameters();
        int loadCount = 20;
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

    /**
     * 仅适用于 类型为 String 的值
     * 
     * @param pmap
     * @param names
     * @return
     */
    public static Map<String, Object> getInMap(Map<String, Object> pmap, String... names) {
        Map<String, Object> inMap = new HashMap<String, Object>();
        if (names != null) {
            for (String name : names) {
                if (pmap.containsKey(name)) {
                    if (pmap.get(name) != null) {
                        inMap.put(name, pmap.get(name).toString());
                    }
                }
            }
        }
        return inMap;
    }

    public static void authNoForSapFeedback(Map<String, Object> result) {
        
    }
}
