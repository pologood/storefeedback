package com.gome.storefeedback.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.GoodsCategoryConfigDao;
import com.gome.storefeedback.entity.GoodsCategoryConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsCategoryConfigService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * @author Zhang.Mingji
 * @date 2015年7月17日下午3:40:10
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("goodsCategoryConfigService")
public class GoodsCategoryConfigServiceImpl implements GoodsCategoryConfigService {
	
	@Resource
	private GoodsCategoryConfigDao goodsCategoryConfigDao;

	@Override
	public int deleteGoodsCategoryConfig(Map<String, Object> inMap)
			throws BaseException {
		return this.goodsCategoryConfigDao.deleteGoodsCategoryConfig(inMap);
	}

	@Override
	public int insertGoodsCategoryConfig(GoodsCategoryConfig record)
			throws BaseException {
		return this.goodsCategoryConfigDao.insertGoodsCategoryConfig(record);
	}

	@Override
	public List findGoodsCategoryConfig(Map<String, Object> inMap)
			throws BaseException {
		return this.goodsCategoryConfigDao.findGoodsCategoryConfig(inMap);
	}

	@Override
	public GoodsCategoryConfig findGoodsCategoryConfigById(
			Map<String, Object> inMap) throws BaseException {
		return this.goodsCategoryConfigDao.findGoodsCategoryConfigById(inMap);
	}

	@Override
	public int updateGoodsCategoryConfig(GoodsCategoryConfig record)
			throws BaseException {
		return this.goodsCategoryConfigDao.updateGoodsCategoryConfig(record);
	}

	@Override
	public Page findGoodsCategoryConfigByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.goodsCategoryConfigDao.findGoodsCategoryConfigByPage(inMap, param);
	}

}
