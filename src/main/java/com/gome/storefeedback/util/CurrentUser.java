package com.gome.storefeedback.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;

/**
 * 当前用户
 * @author Ma.Mingyang
 * @date 2015年1月26日下午4:20:39
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class CurrentUser {

	/**
	 * 设置当前用户
	 * @param request
	 * @param user
	 * @throws BaseException
	 */
	public static void setCurrentUser(HttpServletRequest request, User user) throws BaseException{
		HttpSession session = request.getSession();
		session.setAttribute("existUser", user);
	}

	/**
	 * 清空session
	 * @param request
	 */
	public static void removeCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("existUser");
	}

	/**
	 * 获取用户
	 * @param request
	 */
	public static User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (User) session.getAttribute("existUser");
	}
}
