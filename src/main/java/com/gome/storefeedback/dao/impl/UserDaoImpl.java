package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.UserDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 用户dao实现类(继承自BaseDaoImpl)
 * @author Ma.Mingyang
 * @date 2015年1月22日下午2:38:53
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, User> implements UserDao {

	@Override
	public void insertUser(User user) throws BaseException {
		try {
			this.insert("Mapper.User.insertUser", user);
		} catch (Exception e) {
			throw new BaseException("insert user error!",e);
		}
	}

	@Override
	public void deleteUser(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.User.deleteUserByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("delete user error!",e);
		}
	}

	@Override
	public void updateUser(User user) throws BaseException {
		try {
			this.update("Mapper.User.updateUser", user);
		} catch (Exception e) {
			throw new BaseException("update user error!",e);
		}
	}

	@Override
	public User findUserById(Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByID("Mapper.User.findUserById", inMap);
		} catch (Exception e) {
			throw new BaseException("find user error!",e);
		}
	}

	@Override
	public List<User> findUser(Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByParam("Mapper.User.findUserByParam", inMap);
		} catch (Exception e) {
			throw new BaseException("find userList error!",e);
		}
	}

	@Override
	public Page<User> findUserByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.User.findUserByParam", inMap, param);
		} catch (Exception e) {
			throw new BaseException("find user by page error!",e);
		}
	}

}
