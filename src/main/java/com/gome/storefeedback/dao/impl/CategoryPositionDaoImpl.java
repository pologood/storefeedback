package com.gome.storefeedback.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.CategoryPositionDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * @author Zhang.Mingji
 * @date 2015年7月17日下午4:17:52
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("categoryPositionDao")
public class CategoryPositionDaoImpl extends BaseDaoImpl<CategoryPosition, CategoryPosition> implements
		CategoryPositionDao {

	@Override
	public int deleteCategoryPosition(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.delete("Mapper.CategoryPositionMapper.deleteByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("deleteCategoryPosition error.", e);
		}
	}

	@Override
	public int insertCategoryPosition(CategoryPosition record)
			throws BaseException {
		try {
			return this.insert("Mapper.CategoryPositionMapper.insert", record);
		} catch (Exception e) {
			throw new BaseException("insertCategoryPosition error.", e);
		}
	}

	@Override
	public List findCategoryPosition(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByParam("Mapper.CategoryPositionMapper.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findCategoryPosition error.", e);
		}
	}

	@Override
	public CategoryPosition findCategoryPositionById(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.CategoryPositionMapper.selectByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("findCategoryPositionById error.", e);
		}
	}

	@Override
	public int updateCategoryPosition(CategoryPosition record)
			throws BaseException {
		try {
			return this.update("Mapper.CategoryPositionMapper.update", record);
		} catch (Exception e) {
			throw new BaseException("updateCategoryPosition error.", e);
		}
	}

	@Override
	public Page findCategoryPositionByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.CategoryPositionMapper.findByPage", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findCategoryPositionByPage error.", e);
		}
	}

	@Override
	public List<CategoryPosition> findCategoryPositionAll(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByParam("Mapper.CategoryPositionMapper.listAll", inMap);
		} catch (Exception e) {
			throw new BaseException("findCategoryPosition error.", e);
		}
	}

	@Override
	public List<CategoryPosition> findCategoryPositionNotBoos(
			Map<String, Object> inMap) throws BaseException {
		return this.findByParam("Mapper.CategoryPositionMapper.findCategoryPositionNotBoos", inMap);
	}

}
