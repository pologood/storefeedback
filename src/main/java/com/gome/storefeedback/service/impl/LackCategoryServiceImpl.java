package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.LackCategoryDao;
import com.gome.storefeedback.entity.LackCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.LackCategoryService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午5:48:27
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("lackCategoryService")
public class LackCategoryServiceImpl implements LackCategoryService {

	private LackCategoryDao lackCategoryDao;
	
	
	public LackCategoryDao getLackCategoryDao() {
		return lackCategoryDao;
	}

	@Autowired
	public void setLackCategoryDao(@Qualifier("lackCategoryDao")LackCategoryDao lackCategoryDao) {
		this.lackCategoryDao = lackCategoryDao;
	}

	@Override
	public void insertLackCategory(LackCategory lackCategory) throws BaseException {
		this.lackCategoryDao.insertLackCategory(lackCategory);
	}

	@Override
	public void updateLackCategory(LackCategory lackCategory) throws BaseException {
		this.lackCategoryDao.updateLackCategory(lackCategory);
	}

	@Override
	public void deleteLackCategory(Map<String, Object> inMap) throws BaseException {
		this.lackCategoryDao.deleteLackCategory(inMap);
	}

	@Override
	public LackCategory findLackCategoryById(Map<String, Object> inMap)
			throws BaseException {
		return this.lackCategoryDao.findLackCategoryById(inMap);
	}

	@Override
	public List findLackCategory(Map<String, Object> inMap) throws BaseException {
		return this.lackCategoryDao.findLackCategory(inMap);
	}

	@Override
	public Page findLackCategoryByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.lackCategoryDao.findLackCategoryByPage(inMap, param);
	}

}
