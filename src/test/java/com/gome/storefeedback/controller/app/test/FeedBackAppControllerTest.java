package com.gome.storefeedback.controller.app.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.test.util.TestUtil;
import com.gome.storefeedback.util.AESAPPUtils;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;

public class FeedBackAppControllerTest {
    private static String scheme = "http";
    private static String ip = "localhost";
    private static int port = 8080;

    @Test
    public void findListByRecieverTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackApp/findListByReciever")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        // pmap.put("feedbackPersonId", "1");
        pmap.put("hasReturn", "0");
        pmap.put("storeCode", "A0");
        // pmap.put("goodsName", "彩电");
        pmap.put("currentItemNo", "0");
        pmap.put("startTime", "2014-01-01");
        pmap.put("endTime", "2016-01-01");
        pmap.put("goodsCode", "000000000100242793");
        pmap.put("loadCount", "10");
        // pmap.put("storeName", "大中");

        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void findFeedBacksTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackApp/findFeedBacks")//
                .build();

        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        // pmap.put("sponsorId", "1000010001488");
        pmap.put("hasReturn", "0");

        String jsonStr = JsonUtil.javaObjectToJsonString(pmap);
        System.out.println(jsonStr);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        String encryptAES = AESAPPUtils.encryptAES(jsonStr);
        System.out.println(encryptAES);
        formparams.add(new BasicNameValuePair(BusinessGlossary.APP_STOREFEEDBACK, encryptAES));
        HttpPost httppost = new HttpPost(uri);
        httppost.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(GzipAESUtil.decryptAESThenUnCompress(EntityUtils.toString(responseEntity)));
        response.close();
        httpclient.close();
    }

    @Test
    public void findFeedBacksByIdTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackApp/findFeedBacksById")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("id", "ea04f5a663ad4e82ade8a25c77c483b3");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void deleteTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackApp/delete")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("id", "ea04f5a663ad4e82ade8a25c77c483b3");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void updateTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackApp/update")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("id", "ea04f5a663ad4e82ade8a25c77c483b3");
        pmap.put("firstCategory", "W");
        pmap.put("secondCategory", "W0201");
        pmap.put("brandCode", "00001");
        pmap.put("goodsCode", "1");
        pmap.put("lackCategory", "1");
        pmap.put("quantity", "2");
        pmap.put("saleOutDate", "1");
        pmap.put("anticipatedDatesSoldOut", "1");
        pmap.put("sponsorId", "1");
        pmap.put("sponsorCompanyId", "1");
        pmap.put("sponsorEmployeeId", "1");
        pmap.put("sponsorEmployeeName", "1");
        pmap.put("storeCode", "1");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void insertTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackApp/insert")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        // pmap.put("id", "1");
        pmap.put("firstCategory", "0001");
        pmap.put("secondCategory", "R0101001");
        pmap.put("brandCode", "00001");
        pmap.put("goodsCode", "000000000100251919");
        pmap.put("lackCategory", "1");
        pmap.put("quantity", "123");
        pmap.put("saleOutDate", 123);
        // pmap.put("anticipatedDatesSoldOut", new Date().getTime() + "");
        pmap.put("anticipatedDatesSoldOut", "2015-04-25");
        // pmap.put("sponsorId", "1000000001157");
        // pmap.put("sponsorCompanyId", "1");
        // pmap.put("sponsorEmployeeId", "1");
        // pmap.put("sponsorEmployeeName", "1");
        // pmap.put("storeCode", "");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void findLackCategoryTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackApp/findLackCategory")//
                .build();

        Map<String, Object> pmap = TestUtil.getBaseInfoMap();

        String jsonStr = JsonUtil.javaObjectToJsonString(pmap);
        System.out.println(jsonStr);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        String encryptAES = AESAPPUtils.encryptAES(jsonStr);
        System.out.println(encryptAES);
        formparams.add(new BasicNameValuePair(BusinessGlossary.APP_STOREFEEDBACK, encryptAES));
        HttpPost httppost = new HttpPost(uri);
        httppost.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(GzipAESUtil.decryptAESThenUnCompress(EntityUtils.toString(responseEntity)));
        response.close();
        httpclient.close();
    }

    @Test
    public void notificationSend() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", "10000");
        map.put("employeeId", "00000333");
        // map.put("employeeId", "00026572");
        map.put("title", "TT");
        map.put("content", "TTTT");
        map.put("type", "106");
        map.put("sender", "storefeedback");

        String pValue = JsonUtil.javaObjectToJsonString(map);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("notification", pValue));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            String url = "http://10.128.16.218:8088/gsm/n/notification/sendNotificationToMobile";
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            System.out.println(url);
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println(EntityUtils.toString(responseEntity));
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
}
