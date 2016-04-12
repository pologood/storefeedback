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
import com.gome.storefeedback.constant.NewCateGoryCnstant;
import com.gome.storefeedback.test.util.TestUtil;
import com.gome.storefeedback.util.AESAPPUtils;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;

public class NewCategoryTest {
	private static String scheme = "http";
	private static String ip = "localhost";
	private static int port = 8080;

	@Test
	public void subNewCategoryReport() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newCategoryReport/subNewCategoryReport")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		// pmap.put("feedbackPersonId", "1");
		//pmap.put("newCategoryCode", "0");
		pmap.put(NewCateGoryCnstant.CATEGPRY_DESC, "A0");
		 pmap.put(NewCateGoryCnstant.MAIN_DESC, "121");
		 pmap.put(NewCateGoryCnstant.MODEL_DESC, "modelDesc");
		pmap.put("desc", "0");
		pmap.put("newCategoryImg1", "2014-01-01");
		pmap.put("newCategoryImg2", "2016-01-01");
		pmap.put("newCategoryImg3", "000000000100242793");
		pmap.put(NewCateGoryCnstant.DEALER, "10");
	
        TestUtil.httpPost(uri, pmap); 
        
	}
	/*@Test
	public void initNewCategoryHandle() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newCategoryHandle/initNewCategoryHandle")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		// pmap.put("feedbackPersonId", "1");
		//pmap.put("newCategoryCode", "0");
//		pmap.put(NewCateGoryCnstant.CATEGPRY_DESC, "A0");
//		 pmap.put(NewCateGoryCnstant.MAIN_DESC, "121");
//		pmap.put("desc", "0");
//		pmap.put("newCategoryImg1", "2014-01-01");
//		pmap.put("newCategoryImg2", "2016-01-01");
//		pmap.put("newCategoryImg3", "000000000100242793");
//		pmap.put(NewCateGoryCnstant.DEALER, "10");
	
        TestUtil.httpPost(uri, pmap); 
        
	}*/
	/*@Test
	public void subNewCategoryHandle() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newCategoryHandle/subNewCategoryHandle")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		 pmap.put("isImport", pmap.get(NewCateGoryCnstant.IS_IMPORT));
		pmap.put("notImportDesc", "0");
		pmap.put(NewCateGoryCnstant.CATEGPRY_DESC, "A0");
		 pmap.put(NewCateGoryCnstant.MAIN_DESC, "121");
		pmap.put("desc", "0");
		pmap.put("newCategoryImg1", "2014-01-01");
		pmap.put("newCategoryImg2", "2016-01-01");
		pmap.put("newCategoryImg3", "000000000100242793");
		pmap.put(NewCateGoryCnstant.DEALER, "10");
		pmap.put(NewCateGoryCnstant.CATEGORY_CODE, "0010");
		pmap.put(NewCateGoryCnstant.ID, "e7d99a21a7104058b92e53b5f7bf3da4");
	
        TestUtil.httpPost(uri, pmap);
        
	}*/
	
	/*@Test
	public void initModelsReport() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newModelsReport/initModelsReport")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		 pmap.put("isImport", pmap.get(NewCateGoryCnstant.IS_IMPORT));
		pmap.put("notImportDesc", "0");
		pmap.put(NewCateGoryCnstant.CATEGPRY_DESC, "A0");
		 pmap.put(NewCateGoryCnstant.MAIN_DESC, "121");
		pmap.put("desc", "0");
		pmap.put("newCategoryImg1", "2014-01-01");
		pmap.put("newCategoryImg2", "2016-01-01");
		pmap.put("newCategoryImg3", "000000000100242793");
		pmap.put(NewCateGoryCnstant.DEALER, "10");
	
        TestUtil.httpPost(uri, pmap);
        
	}*/
	
	/*@Test
	public void subModelReport() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newModelsReport/subModelReport")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		pmap.put("categoryCode", "categoryCode");
		pmap.put("categoryDesc", "categoryDesc");
		pmap.put("modelCode","modelCode");
		pmap.put(NewCateGoryCnstant.MAIN_DESC, "mainDesc");
		pmap.put("desc", "0");
		pmap.put(NewCateGoryCnstant.IMG_1, "IMG_1");
		pmap.put(NewCateGoryCnstant.IMG_2, "IMG_2");
		pmap.put(NewCateGoryCnstant.IMG_3, "IMG_3");
		pmap.put("newCategoryImg1", "2014-01-01");
		pmap.put("newCategoryImg2", "2016-01-01");
		pmap.put("newCategoryImg3", "000000000100242793");
		pmap.put(NewCateGoryCnstant.DEALER, "10");
	
        TestUtil.httpPost(uri, pmap);
        
	}*/
	
	/*@Test
	public void initModelsHandle() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newModelsHandle/initModelsHandle")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		pmap.put("categoryCode", "categoryCode");
		pmap.put("categoryDesc", "categoryDesc");
		pmap.put("modelCode","modelCode");
		pmap.put(NewCateGoryCnstant.MAIN_DESC, "mainDesc");
		pmap.put("desc", "0");
		pmap.put(NewCateGoryCnstant.IMG_1, "IMG_1");
		pmap.put(NewCateGoryCnstant.IMG_2, "IMG_2");
		pmap.put(NewCateGoryCnstant.IMG_3, "IMG_3");
		pmap.put("newCategoryImg1", "2014-01-01");
		pmap.put("newCategoryImg2", "2016-01-01");
		pmap.put("newCategoryImg3", "000000000100242793");
		pmap.put(NewCateGoryCnstant.DEALER, "10");
	
        TestUtil.httpPost(uri, pmap);
        
	}*/
	/*
	@Test
	public void initModelsHandle() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newModelsHandle/initImgModelHandle")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		pmap.put("id", "4f38c30a921d4893b13e95c54d101783");
		
	
        TestUtil.httpPost(uri, pmap);
        
	}*/
	/*@Test
	public void subNewCategoryHandle() throws Exception {
		URI uri = new URIBuilder().setScheme(scheme)//
				.setHost(ip)//
				.setPort(port)//
				.setPath("/storefeedback/m/newModelsHandle/subModelsHandle")//
				.build();
		Map<String, Object> pmap = TestUtil.getBaseInfoMap();
		pmap.put("id", "85999fd63f3d466eb6fd1c266956e97a");
		 pmap.put("isImport", pmap.get(NewCateGoryCnstant.IS_IMPORT));
		pmap.put("notImportDesc", "0");
		pmap.put(NewCateGoryCnstant.MODEL_DESC, "A0");
		 pmap.put(NewCateGoryCnstant.MAIN_DESC, "121");
		pmap.put("desc", "0");
		pmap.put("newCategoryImg1", "2014-01-01");
		pmap.put("newCategoryImg2", "2016-01-01");
		pmap.put("newCategoryImg3", "000000000100242793");
		pmap.put(NewCateGoryCnstant.DEALER, "10");
	
        TestUtil.httpPost(uri, pmap);
        
	}*/
}
