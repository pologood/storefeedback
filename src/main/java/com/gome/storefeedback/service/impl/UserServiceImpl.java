package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.UserDao;
import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.UserService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * user业务层实现类
 * @author Ma.Mingyang
 * @date 2015年1月22日下午3:49:05
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * 注入userdao
	 * @param userDao
	 */
	@Autowired
	public void setUserDao(@Qualifier("userDao")UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void insertUser(User user) throws BaseException {
		userDao.insertUser(user);
	}

	@Override
	public void deleteUser(Map<String, Object> inMap) throws BaseException {
		userDao.deleteUser(inMap);
	}

	@Override
	public void updateUser(User user) throws BaseException {
		userDao.updateUser(user);
	}

	@Override
	public User findUserById(Map<String, Object> inMap) throws BaseException {
		return userDao.findUserById(inMap);
	}

	@Override
	public List<User> findUser(Map<String, Object> inMap) throws BaseException {
		return userDao.findUser(inMap);
	}

	@Override
	public Page<User> findUserByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return userDao.findUserByPage(inMap, param);
	}

}
