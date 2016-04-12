package com.gome.storefeedback.controller.app.test;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import com.gome.storefeedback.test.util.TestUtil;

public class FeedBackRecordAppControllerTest {
    private static String scheme = "http";
    private static String ip = "localhost";
    private static int port = 8080;

    @Test
    public void findListByPKTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackRecordApp/findListByPK")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("feedbackId", "30a3ba5834804e41ab875b90c2d07db5");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void findFeedBackRecordsTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackRecordApp/findFeedBackRecords")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("feedbackPersonId", "11");
        pmap.put("feedbackId", "30a3ba5834804e41ab875b90c2d07db5");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void findFeedBackRecordByIdTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackRecordApp/findFeedBackRecordById")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("id", "f098a89022b341de9ac1692f861028a7");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void deleteTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackRecordApp/delete")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("id", "d8b79b6fbf204d78a7de2ab0f56645b4");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void updateTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackRecordApp/update")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("id", "8199ed61663b456ab0fdc5d77b086387");
        pmap.put("feedbackContent", "1");
        pmap.put("feedbackId", "30a3ba5834804e41ab875b90c2d07db5");
        pmap.put("feedbackPersonEmployeeId", "1");
        pmap.put("feedbackPersonEmployeeName", "1");
        pmap.put("feedbackPersonId", "11");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void insertTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/feedBackRecordApp/insert")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("id", "1");
        pmap.put("feedbackContent", "孙兰兰 测试 消息");
        // pmap.put("feedbackId", "30a3ba5834804e41ab875b90c2d07db5");
        pmap.put("feedbackId", "4df3b8ca892c463b9a5bfb4554ac468d");
        pmap.put("feedbackPersonEmployeeId", "00000333");
        pmap.put("feedbackPersonEmployeeName", "孙兰兰");
        pmap.put("feedbackPersonId", "1000000000333");
        TestUtil.httpPost(uri, pmap);
    }
}
