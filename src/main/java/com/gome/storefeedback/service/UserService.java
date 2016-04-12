package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 用户service接口
 * @author Ma.Mingyang
 * @date 2015年1月22日下午3:43:02
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface UserService {

	/**
	 * 添加用户
	 * @param user
	 * @throws BaseException
	 */
	public void insertUser(User user) throws BaseException;
	/**
	 * 删除用户
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteUser(Map<String, Object> inMap) throws BaseException;
	/**
	 * 修改用户
	 * @param user
	 * @throws BaseException
	 */
	public void updateUser(User user) throws BaseException;
	/**
	 * 根据id查找用户
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public User findUserById(Map<String, Object> inMap) throws BaseException;
	/**
	 * 根据条件查找用户
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List<User> findUser(Map<String, Object> inMap) throws BaseException;
	/**
	 * 分页查询用户
	 * @param inMap
	 * @param param
	 * @return
	 * @throws BaseException
	 */
	public Page<User> findUserByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException;
}
