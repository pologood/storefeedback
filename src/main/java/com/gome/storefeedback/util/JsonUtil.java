package com.gome.storefeedback.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.gome.storefeedback.exception.BaseException;


/**
 * Json 处理函数；
 * @author Zhang.Mingji
 * @date 2014-2-11下午3:08:54
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class JsonUtil {
    /**
     * 将Java Ojbect 转换成Json 串，返回给前端；
     * 
     * @param response
     * @param object
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    public static void writeResponseAsJsonString(HttpServletResponse response, Object object) throws Exception,
            JsonMappingException, IOException {
        
        String jsonString = javaObjectToJsonString(object);
        response.reset();
        if (object instanceof HashMap) {
            HashMap mp = (HashMap) object;
            String radomFileID = (String) mp.get("radomFileID");
            if (null != radomFileID && !"".equals(radomFileID)) {
                String fileID=(String) mp.get("fileID");
                if(null!=fileID){
                    JsonUtil.setCookie(response, "_fileID_ajax_upload_" + radomFileID, (String) mp.get("fileID"),
                            3600 * 24 * 1, false);
                }
                String fileName = (String) mp.get("fileName");
                if(null!=fileName &&!"".equals(fileName)){
                    fileName= URLEncoder.encode(fileName, "utf-8");
                    JsonUtil.setCookie(response, "_fileName_ajax_upload_" + radomFileID, fileName, 3600 * 24 * 1, false);
                }
            }
        }
        response.setHeader("content-type", "text/html;charset=utf-8");
        response.getOutputStream().write((jsonString).getBytes("utf-8"));
    }

    /**
     * 设置cookie
     * 
     * @param response
     * @param key
     * @param value
     * @param expired
     * @param secure
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int expired, boolean secure) {
        Cookie c = new Cookie(key, value);
        c.setPath("/");
        c.setMaxAge(expired);
        c.setSecure(secure);
        response.addCookie(c);
    }

    /**
     * 得到Json串
     * 
     * @param object
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static String javaObjectToJsonString(Object object) throws BaseException {
        String jsonString = "";
        if (null != object) {
            try {
                JsonFactory factory = new JsonFactory();
                ObjectMapper mapper = new ObjectMapper(factory);
                jsonString = mapper.writeValueAsString(object);
            } catch (Exception e) {
                e.printStackTrace();
                jsonString = "";
            }
        }
        return jsonString;
    }

    /**
     * JsonString 转换成对应Object
     * 
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static Object getJsonStringToObject(String jsonString, Class cls) throws Exception {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        return mapper.readValue(jsonString, cls);
    }

    /**
     * 获取到Map值；
     * 
     * @param jsonString
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    public static Map<String,Object> jsonStringToMap(String jsonString) throws BaseException {
        Map<String,Object> hashMap = new HashMap<String,Object>();
        if (null == jsonString) {
            return null;
        }
        try {
            hashMap = (Map<String,Object>) getJsonStringToObject(jsonString, HashMap.class);
        } catch (Exception e) {
            throw new BaseException(e);
        }
        return hashMap;
    }
    /**
     * 获取到Map值；
     * 
     * @param jsonString
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static List jsonStringToList(String jsonString) throws BaseException {
        List pList=new ArrayList();
        if(null==jsonString){
            return null;
        }
        try{
            pList = (List) getJsonStringToObject(jsonString, ArrayList.class);
        }catch(Exception e){
            throw new BaseException(e);
        }
        return pList;
    }

}