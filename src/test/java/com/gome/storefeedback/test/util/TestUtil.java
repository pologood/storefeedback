package com.gome.storefeedback.test.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.util.AESAPPUtils;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;

public class TestUtil {

    private static String scheme = "http";
    private static String ip = "localhost";
    private static int port = 8080;

    public static Map<String, Object> getBaseInfoMap() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jkId", "jk1001");
        map.put("snId", "123456");
        map.put("mkId", "m1001");
        map.put("btnId", "btn001");
        map.put("sjId", new Date().getTime() + "");

//        map.put("accesstoken", "169cd419-bd33-4ca6-b1cb-1524b4e82e3c");
        map.put("accesstoken", "12c0c487-e5a9-4524-91a8-23d5e63db3c5");
        map.put("phone", "18634319002");
        return map;
    }

    public static void httpPost(URI uri, Map<String, Object> pmap) throws Exception {
        String jsonStr = JsonUtil.javaObjectToJsonString(pmap);
        System.out.println(jsonStr);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        String encryptAES = AESAPPUtils.encryptAES(jsonStr);
        formparams.add(new BasicNameValuePair(BusinessGlossary.APP_STOREFEEDBACK, encryptAES));
        HttpPost httppost = new HttpPost(uri);
        httppost.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httppost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println(GzipAESUtil.decryptAESThenUnCompress(EntityUtils.toString(responseEntity)));
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Test
    public void get(){
        
        String s="";
        
        String decryptAESThenUnCompress = GzipAESUtil.decryptAESThenUnCompress(s);
        
        System.out.println(decryptAESThenUnCompress);
        
    }
}
