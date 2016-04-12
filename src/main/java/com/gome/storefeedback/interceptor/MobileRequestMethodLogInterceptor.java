package com.gome.storefeedback.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

/**
 * 记录移动端调用日志
 * @author Zhang Mingji
 * @2014-2-24下午06:17:53
 * Copyright(c) gome.inc Gome Co.,ltd
 */
public class MobileRequestMethodLogInterceptor implements HandlerInterceptor{

	private static Logger logger = LoggerFactory.getLogger(MobileRequestMethodLogInterceptor.class);
	
	private Long startTime = 0L;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		UrlPathHelper helper = new UrlPathHelper();
		String relativePath = helper.getOriginatingRequestUri(request);
		String contextPath = helper.getOriginatingContextPath(request);
		relativePath = relativePath.substring(contextPath.length());
		if(relativePath.indexOf("/m/") != -1){
			startTime = System.currentTimeMillis();
			System.out.println("-----start invoke!------");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		UrlPathHelper helper = new UrlPathHelper();
		String relativePath = helper.getOriginatingRequestUri(request);
		String contextPath = helper.getOriginatingContextPath(request);
		relativePath = relativePath.substring(contextPath.length());
		if(relativePath.indexOf("/m/") != -1){
			Long endTime = System.currentTimeMillis();
			logger.info("--------拦截到路径" + relativePath +  " invoke time: " + (endTime - startTime) + "ms -----------------------");
			System.out.println("----end invoke!----");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
