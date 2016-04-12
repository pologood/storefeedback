package com.gome.storefeedback.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.CategoryPositionDao;
import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.CategoryPositionService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * @author Zhang.Mingji
 * @date 2015年7月20日下午3:30:13
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("categoryPositionService")
public class CategoryPositionServiceImpl implements CategoryPositionService {

	@Resource
	private CategoryPositionDao categoryPositionDao;
	
	@Override
	public int deleteCategoryPosition(Map<String, Object> inMap)
			throws BaseException {
		return this.categoryPositionDao.deleteCategoryPosition(inMap);
	}

	@Override
	public int insertCategoryPosition(CategoryPosition record)
			throws BaseException {
		return this.categoryPositionDao.insertCategoryPosition(record);
	}

	@Override
	public List findCategoryPosition(Map<String, Object> inMap)
			throws BaseException {
		return this.categoryPositionDao.findCategoryPosition(inMap);
	}

	@Override
	public CategoryPosition findCategoryPositionById(Map<String, Object> inMap)
			throws BaseException {
		return this.categoryPositionDao.findCategoryPositionById(inMap);
	}

	@Override
	public int updateCategoryPosition(CategoryPosition record)
			throws BaseException {
		return this.categoryPositionDao.updateCategoryPosition(record);
	}

	@Override
	public Page findCategoryPositionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.categoryPositionDao.findCategoryPositionByPage(inMap, param);
	}
	
	@Override
	public List<CategoryPosition> findCategoryPositionAll(Map<String, Object> inMap) throws BaseException {
		return this.categoryPositionDao.findCategoryPositionAll(inMap);
	}

	@Override
	public List<CategoryPosition> findCategoryPositionNotBoos(
			Map<String, Object> inMap) throws BaseException {
		return this.categoryPositionDao.findCategoryPositionNotBoos(inMap);
	}


}
