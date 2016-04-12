package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.LackCategoryDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.LackCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 该类只作为模板参考，不参与具体的业务
 * @author Zhang.Mingji
 * @date 2014年8月7日上午11:17:13
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("lackCategoryDao")
public class LackCategoryDaoImpl extends BaseDaoImpl<LackCategory, LackCategory> implements LackCategoryDao {

	@Override
	public void insertLackCategory(LackCategory lackCategory) throws BaseException {
		try {
			this.insert("Mapper.LackCategory.insert", lackCategory);
		} catch (Exception e) {
			throw new BaseException("insert lackCategory error.", e);
		}
	}

	@Override
	public void updateLackCategory(LackCategory lackCategory) throws BaseException {
		try {
			this.update("Mapper.LackCategory.update", lackCategory);
		} catch (Exception e) {
			throw new BaseException("update lackCategory error.", e);
		}
	}

	@Override
	public void deleteLackCategory(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.LackCategory.deleteByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("delete lackCategory error.", e);
		}
	}

	@Override
	public LackCategory findLackCategoryById(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.LackCategory.selectByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("findLackCategoryById error.", e);
		}
	}

	@Override
	public List findLackCategory(Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByParam("Mapper.LackCategory.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findLackCategory error.", e);
		}
	}

	@Override
	public Page findLackCategoryByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.LackCategory.list", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findLackCategoryByPage error.", e);
		}
	}

}
