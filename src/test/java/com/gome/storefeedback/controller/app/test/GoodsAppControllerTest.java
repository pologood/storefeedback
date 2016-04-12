package com.gome.storefeedback.controller.app.test;

import java.net.URI;
import java.util.ArrayList;
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
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.test.util.TestUtil;
import com.gome.storefeedback.util.AESAPPUtils;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;

public class GoodsAppControllerTest extends AbstractTransactionalSpringContextTestCase {
    private static String scheme = "http";
    private static String ip = "localhost";
    private static int port = 8080;
    @Test
    public void findGoodsCategorysTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/goodsApp/findGoodsCategorys")//
                .build();

        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("classLevel", "1");
        // pmap.put("className", "1");
        // pmap.put("parentClassCode", "1");

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
    public void findGoodsBrandsTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/goodsApp/findGoodsBrands")//
                .build();

        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("cnText", "1");

        String jsonStr = JsonUtil.javaObjectToJsonString(pmap);
        System.out.println(jsonStr);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair(BusinessGlossary.APP_STOREFEEDBACK, AESAPPUtils.encryptAES(jsonStr)));
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
    public void findGoodsesTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/goodsApp/findGoodses")//
                .build();

        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("goodsBrand", "00001");
        // pmap.put("categoryLevel1", "1");
        // pmap.put("categoryLevel2", "1");
        // pmap.put("categoryLevel3", "1");
        // pmap.put("goodsName", "1");

        String jsonStr = JsonUtil.javaObjectToJsonString(pmap);
        System.out.println(jsonStr);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair(BusinessGlossary.APP_STOREFEEDBACK, AESAPPUtils.encryptAES(jsonStr)));
        HttpPost httppost = new HttpPost(uri);
        httppost.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(GzipAESUtil.decryptAESThenUnCompress(EntityUtils.toString(responseEntity)));
        response.close();
        httpclient.close();
    }

}
