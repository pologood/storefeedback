package com.gome.storefeedback.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.GoodsCategoryConfigDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.GoodsCategoryConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * @author Zhang.Mingji
 * @date 2015年7月17日下午3:27:44
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("goodsCategoryConfigDao")
public class GoodsCategoryConfigDaoImpl extends BaseDaoImpl<GoodsCategoryConfig, GoodsCategoryConfig> implements
		GoodsCategoryConfigDao {

	@Override
	public int deleteGoodsCategoryConfig(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.delete("Mapper.GoodsCategoryConfigMapper.deleteByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("deleteGoodsCategoryConfig error.", e);
		}
	}

	@Override
	public int insertGoodsCategoryConfig(GoodsCategoryConfig record)
			throws BaseException {
		try {
			return this.insert("Mapper.GoodsCategoryConfigMapper.insert", record);
		} catch (Exception e) {
			throw new BaseException("insertGoodsCategoryConfig error.", e);
		}
	}

	@Override
	public List findGoodsCategoryConfig(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByParam("Mapper.GoodsCategoryConfigMapper.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findGoodsCategoryConfig error.", e);
		}
	}

	@Override
	public GoodsCategoryConfig findGoodsCategoryConfigById(
			Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByID("Mapper.GoodsCategoryConfigMapper.selectByPrimaryKey", inMap);
		} catch (Exception e) {
			throw new BaseException("findGoodsCategoryConfigById error.", e);
		}
	}

	@Override
	public int updateGoodsCategoryConfig(GoodsCategoryConfig record)
			throws BaseException {
		try {
			return this.update("Mapper.GoodsCategoryConfigMapper.update", record);
		} catch (Exception e) {
			throw new BaseException("updateGoodsCategoryConfig error.", e);
		}
	}

	@Override
	public Page findGoodsCategoryConfigByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.GoodsCategoryConfigMapper.findByPage", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findGoodsCategoryConfigByPage error.", e);
		}
	}

}
