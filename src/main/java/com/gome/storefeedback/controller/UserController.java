package com.gome.storefeedback.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.UserService;
import com.gome.storefeedback.util.CurrentUser;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.OauthUtil;

/**
 * 用户管理
 * @author Ma.Mingyang
 * @date 2015年1月26日下午3:54:37
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/user")
public class UserController {

	//注入相关资源
	
	@Resource
	private UserService userService;
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/checkLogin", produces="text/plain;charset=UTF-8")
	public @ResponseBody String checkLogin(HttpServletRequest request,HttpServletResponse response) throws BaseException{
		//获取页面参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//构建查询参数map
		Map<String,Object> inMap =new HashMap<String,Object>();
		inMap.put("username", username);
		inMap.put("password", passwordEncoder.encodePassword(password, null));
		//构建结果map
		Map<String,Object> result = new HashMap<String,Object>();
		//执行查询
		List<User> findUser = userService.findUser(inMap);
		
		if (findUser.size()==0) {
			result.put("result", false);
			result.put("error",  "用户名或密码错误");
		} else {
			//成功登陆
			result.put("result", true);
			result.put("error",  "");
			//存入session
			CurrentUser.setCurrentUser(request, findUser.get(0));
		}
		return JsonUtil.javaObjectToJsonString(result);
	}
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value="/checkLoginnew", produces="text/plain;charset=UTF-8")
	public @ResponseBody String checkLoginnew(HttpServletRequest request,HttpServletResponse response) throws BaseException{
		//获取页面参数
		String username_new = request.getParameter("username_new");
		String password_new = request.getParameter("password_new");
		String token  =OauthUtil.getAccessToken(username_new, password_new);
		Map<String,Object> result = new HashMap<String,Object>();
		
		if (StringUtils.isBlank(token)) {
			result.put("result", false);
			result.put("error",  "手机号或密码错误");
		} else {
			//成功登陆
			result.put("result", true);
			result.put("token",  token);
			//存入session
			request.getSession().setAttribute("token", token);
		}
		return JsonUtil.javaObjectToJsonString(result);
	}
	/**
	 * 退出登陆
	 * @param request
	 * @param response
	 * @return view
	 */
	@RequestMapping(value="/logout", produces="text/plain;charset=UTF-8")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		//清空session
		CurrentUser.removeCurrentUser(request);
		mv.setViewName("../../login");
		return mv;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return json
	 * @throws BaseException 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/modifyPassword", produces="text/plain;charset=UTF-8")
	public @ResponseBody String modifyPassword(HttpServletRequest request, HttpServletResponse response) throws BaseException{
		Map<String,Object> result = new HashMap<String,Object>();
		String oldPwd = request.getParameter("password");
		String newPwd = request.getParameter("newPassword");
		//Map<String, Object> inMap = new HashMap<String,Object>();
		User sessionUser=CurrentUser.getCurrentUser(request);
		//inMap.put("id", sessionUser.getId());
		if(sessionUser==null){
			result.put("result", false);
			result.put("error", "未登陆的用户！");
		}else{
			if(!passwordEncoder.encodePassword(oldPwd, null).equals(sessionUser.getPassword())){
				result.put("result", false);
				result.put("error", "原密码输入错误");
			}else {
				sessionUser.setPassword(passwordEncoder.encodePassword(newPwd, null));
				this.userService.updateUser(sessionUser);
				result.put("result", true);
				result.put("error", "");
			}
		}
		return JsonUtil.javaObjectToJsonString(result);
	}
}
