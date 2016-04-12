package com.gome.storefeedback.controller.app.test;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import com.gome.storefeedback.test.util.TestUtil;

public class SapFeedbackControllerTest {

     private static int port = 8088;
     private static String ip = "10.128.16.218";
    private static String scheme = "http";

//    private static String ip = "localhost";
//    private static int port = 8080;

    @Test
    public void getByEmpTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/sapFeedbackApp/getByEmp")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("divisionCode", "FZ00");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void getByEmpCategoryBuytypeTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/sapFeedbackApp/getByEmpCategoryBuytype")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("categoryCode", "0001");
        pmap.put("buyType", "1");
//        pmap.put("divisionCode", "FZ00");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void getByEmpCategroyBuytypeBrandTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/sapFeedbackApp/getByEmpCategoryBuytypeBrand")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("categoryCode", "0001");
        pmap.put("brandCode", "00001");
        pmap.put("buyType", "0");
//         pmap.put("buyType", "0");
//        pmap.put("divisionCode", "FZ00");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void getByEmpCategroyBuytypeBrandGoodsTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/sapFeedbackApp/getByEmpCategoryBuytypeBrandGoods")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        pmap.put("categoryCode", "0003");
        pmap.put("buyType", "0");
        pmap.put("brandCode", "00023");
        pmap.put("goodsCode", "000000000100247027");

//        pmap.put("divisionCode", "FZ00");
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void insertResult_YesTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/sapFeedbackApp/insertResult")//
                .build();

        Map<String, Object> pmap = TestUtil.getBaseInfoMap();

        String[] ids_yes = new String[]{
                "DTPR_52TZMH73A13H9YDFO9E2P438L#2#16940"};
                    
                pmap.put("ids", ids_yes);
                pmap.put("buyType", "1");
                pmap.put("resultFlag", "1");
                pmap.put("goodsCode", "000000000100247027");
                pmap.put("dataDate", "2015-06-18");
                pmap.put("resultCode", "-1");
                pmap.put("resultName", "");
                String[] orders = new String[1];
                orders[0] = "4100245676";
                pmap.put("orders", orders);
                
       /* String[] ids_yes = new String[]{
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#2#42170",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#3#26087",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#3#45120",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#3#45706",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#4#25057",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#4#32604",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#4#40616",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#5#3361",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#5#32657",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#5#33146",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#6#14000",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#6#23693",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#6#35926",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#8#32469",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#9#30242",
                "DTPR_52GYLI23JGTHE363WXJC7HKP5#10#29294"};
                    
                pmap.put("ids", ids_yes);
                pmap.put("buyType", "1");
                pmap.put("resultFlag", "1");
                pmap.put("goodsCode", "000000000100004430");
                pmap.put("dataDate", "2015-06-16");
                pmap.put("resultCode", "-1");
                pmap.put("resultName", "");
                String[] orders = new String[1];
                orders[0] = "4800000765";
                pmap.put("orders", orders);*/
                
        /*String[] ids_yes = new String[]{
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#2#42170",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#3#26087",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#3#45120",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#3#45706",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#4#25057",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#4#32604",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#4#40616",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#5#3361",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#5#32657",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#5#33146",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#6#14000",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#6#23693",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#6#35926",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#8#32469",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#9#30242",
        "DTPR_52GYLI23JGTHE363WXJC7HKP5#10#29294"};
            
        pmap.put("ids", ids_yes);
        pmap.put("buyType", "1");
        pmap.put("resultFlag", "1");
        pmap.put("goodsCode", "000000000100004430");
        pmap.put("dataDate", "2015-06-16");
        pmap.put("resultCode", "-1");
        pmap.put("resultName", "");
        String[] orders = new String[1];
        orders[0] = "4800000765";
        pmap.put("orders", orders);*/

        // String[] ids_yes = new String[1];
        // ids_yes[0] = "DTPR_52GYLI23JGTHE363WXJC7HKP5#10#23267";
        // pmap.put("ids", ids_yes);//
        // ids:["redis_mybatis_spring_mvc_001"],//唯一key（String[]）
        // pmap.put("buyType", "1");// buyType: "1",//采购方式（String）【1：集采】【0：地采】
        // pmap.put("resultFlag", "1");// resultFlag：" 1"//补货与否标志（String）
        // String[] orders = new String[1];
        // orders[0] = "4400000021";
        // // orders[0] = "1";
        // pmap.put("orders", orders);//
        // orders：["order1 "," order2 "]//采购订单号（String[]）
        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void insertResult_NoTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/sapFeedbackApp/insertResult")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        String[] ids_yes = new String[1];
        ids_yes[0] = "DTPR_52GYLI23JGTHE363WXJC7HKP5#10#23267";
        pmap.put("ids", ids_yes);// ids:["redis_mybatis_spring_mvc_001"],//唯一key（String[]）
        pmap.put("buyType", "1");// buyType: "1",//采购方式（String）【1：集采】【0：地采】
        pmap.put("resultFlag", "1");// resultFlag：" 1"//补货与否标志（String）
        String[] orders = new String[1];
        orders[0] = "4400000021";
        // orders[0] = "1";
        pmap.put("orders", orders);// orders：["order1 "," order2 "]//采购订单号（String[]）

        TestUtil.httpPost(uri, pmap);
    }

    @Test
    public void getReplenishmentNOReasonTest() throws Exception {
        URI uri = new URIBuilder().setScheme(scheme)//
                .setHost(ip)//
                .setPort(port)//
                .setPath("/storefeedback/m/sapFeedbackApp/getReplenishmentNOReason")//
                .build();
        Map<String, Object> pmap = TestUtil.getBaseInfoMap();
        TestUtil.httpPost(uri, pmap);
    }

}
