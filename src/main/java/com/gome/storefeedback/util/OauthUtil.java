package com.gome.storefeedback.util;

import java.net.URI;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.storefeedback.common.CustomizedPropertyPlaceholderConfigurer;

/**
 * Oauth工具类，用于获取accesstoken 和验证token值
 * @author Zhang.Mingji
 * @date 2014年10月9日上午9:59:28
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class OauthUtil {

	private static final Logger logger = LoggerFactory.getLogger(OauthUtil.class);
	
	private static final String OAUTH_IP = (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth.ip");
	private static final Integer OAUTH_PORT = Integer.parseInt((String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth.port"));
	private static final String OAUTH_GET_TOKEN_PATH = (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth.get.token.path");
	private static final String OAUTH_CLIENT_ID = (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth.appkey");
	private static final String OAUTH_CLIENT_SECRET = (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("oatuh.secret");
	private static final String OAUTH_SCHEME = "http";
	private static final String OAUTH_GRANT_TYPE = "password";
	private static final String OAUTH_SCOPE = "read,write";
	private static final String OAUTH_VALIDATE_TOKEN_PATH = (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth.validate.token.path");
	private static final String OAUTH_REMOVE_TOKEN_PATH = (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("oauth.remove.token.path");
	
	/*private static final String OAUTH_IP = "10.128.16.218";
	private static final Integer OAUTH_PORT = 8088;
//	private static final String OAUTH_IP = "10.2.31.25";
//	private static final Integer OAUTH_PORT = 8080;
	private static final String OAUTH_GET_TOKEN_PATH = "/oauth/oauth/token";
	private static final String OAUTH_CLIENT_ID = "gsmclient";
	private static final String OAUTH_CLIENT_SECRET = "gsmpwd";
	private static final String OAUTH_SCHEME = "http";
	private static final String OAUTH_GRANT_TYPE = "password";
	private static final String OAUTH_SCOPE = "read,write";
	private static final String OAUTH_VALIDATE_TOKEN_PATH = "/oauth/validatetoken/loadAuthentication.json";
	private static final String OAUTH_REMOVE_TOKEN_PATH = "/oauth/validatetoken/removeAuthenticationToken.json";*/
	
	
	public static void main(String[] args) {
//		System.out.println(getAccessToken("ZY0404013", "789456"));
		System.out.println(validateAccessToken("8a38569e-5058-40fd-9672-69ff4c85c477"));
//		removeAccessToken("8a38569e-5058-40fd-9672-69ff4c85c477");
	}
	
	/**
	 * 获取Accesstoken
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public static String getAccessToken(String username, String password){
		logger.info("---------------获取AccessToken开始-------------------");
		long startTime = System.currentTimeMillis();
		
		String accesstoken = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			URI uri = new URIBuilder().setScheme(OAUTH_SCHEME)
					.setHost(OAUTH_IP)
					.setPort(OAUTH_PORT)
					.setPath(OAUTH_GET_TOKEN_PATH)
					.setParameter("client_id", OAUTH_CLIENT_ID)
					.setParameter("client_secret", OAUTH_CLIENT_SECRET)
					.setParameter("grant_type", OAUTH_GRANT_TYPE)
					.setParameter("scope", OAUTH_SCOPE)
					.setParameter("username", username)
					.setParameter("password", password)
					.build();
			HttpGet httpGet = new HttpGet(uri);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			System.out.println(result);
			Map map = JsonUtil.jsonStringToMap(result);
			accesstoken = (String) map.get("access_token");
			
			response.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		logger.info("---------获取AccessToken结束---耗时---" + (endTime - startTime) + " ms ----------------");
		
		return accesstoken;
	}
	
	/**
	 * 验证Accesstoken
	 * @param accesstoken token值
	 * @return
	 */
	public static Map validateAccessToken(String accesstoken){
		logger.info("---------------通过AccessToken 获取员工信息 开始-------------------");
		long startTime = System.currentTimeMillis();
		
		Map map = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			URI uri = new URIBuilder().setScheme(OAUTH_SCHEME)
					.setHost(OAUTH_IP)
					.setPort(OAUTH_PORT)
					.setPath(OAUTH_VALIDATE_TOKEN_PATH)
					.setParameter("access_token", accesstoken)
					.build();
			HttpGet httpGet = new HttpGet(uri);//如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象
			CloseableHttpResponse response = httpClient.execute(httpGet);//发送请求
			HttpEntity entity = response.getEntity();//HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容
			String result = EntityUtils.toString(entity);
			map = JsonUtil.jsonStringToMap(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		logger.info("---------通过AccessToken 获取员工信息 结束---耗时---" + (endTime - startTime) + " ms ----------------");
		
		return map;
	}
	
	/**
	 * 删除Accesstoken
	 * @param accesstoken token值
	 */
	public static void removeAccessToken(String accesstoken){
		logger.info("---------------移除AccessToken 开始-------------------");
		long startTime = System.currentTimeMillis();
		
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			URI uri = new URIBuilder().setScheme(OAUTH_SCHEME)
					.setHost(OAUTH_IP)
					.setPort(OAUTH_PORT)
					.setPath(OAUTH_REMOVE_TOKEN_PATH)
					.setParameter("access_token", accesstoken)
					.build();
			HttpGet httpGet = new HttpGet(uri);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		logger.info("---------移除AccessToken  结束---耗时---" + (endTime - startTime) + " ms ----------------");
	}
}
