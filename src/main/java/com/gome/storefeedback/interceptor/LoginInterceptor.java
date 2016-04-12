package com.gome.storefeedback.interceptor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

/**
 * 后台管理界面用户登录拦截
 * @author Zhang.Mingji
 * @date 2014年8月7日上午9:33:41
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class LoginInterceptor extends HandlerInterceptorAdapter  {

	private List<String> excludeUrls;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Map<String,Object> userInfo = (Map<String,Object>) session.getAttribute("userInfo");
		if (userInfo != null) {
			return true;
		}

		UrlPathHelper helper = new UrlPathHelper();
		String relativePath = helper.getOriginatingRequestUri(request);
		String contextPath = helper.getOriginatingContextPath(request);
		if (!StringUtils.isEmpty(contextPath) && !"/".equals(contextPath)) {//$NON-NLS-1$
			relativePath = relativePath.substring(contextPath.length());
		}

		List<String> excludeUrls = getExcludeUrls();
		if (excludeUrls.contains(relativePath) || relativePath.startsWith("/m")) {
			return true;
		}
		response.sendRedirect(contextPath + "/common/error.jsp");
		return false;
	}

	public List<String> getExcludeUrls() {
		if (excludeUrls == null) {
			excludeUrls = Collections.emptyList();
		}
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
}
