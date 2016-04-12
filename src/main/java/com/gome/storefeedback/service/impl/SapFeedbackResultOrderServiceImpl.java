package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.SapFeedbackResultOrderDao;
import com.gome.storefeedback.entity.SapFeedbackResultOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackResultOrderService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * SapFeedbackResultOrderService实现
 * 
 * @author 
 * @date 2015年07月23日 19时07分04秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("sapFeedbackResultOrderService")
public class SapFeedbackResultOrderServiceImpl implements SapFeedbackResultOrderService {

	private SapFeedbackResultOrderDao sapFeedbackResultOrderDao;
	
	
	public SapFeedbackResultOrderDao getSapFeedbackResultOrderDao() {
		return sapFeedbackResultOrderDao;
	}

	@Autowired
	public void setSapFeedbackResultOrderDao(@Qualifier("sapFeedbackResultOrderDao")SapFeedbackResultOrderDao sapFeedbackResultOrderDao) {
		this.sapFeedbackResultOrderDao = sapFeedbackResultOrderDao;
	}

	@Override
	public void batchSapFeedbackResultOrder(List<SapFeedbackResultOrder> sapFeedbackResultOrders) throws BaseException {
		this.sapFeedbackResultOrderDao.batchSapFeedbackResultOrder(sapFeedbackResultOrders);
	}
	
	@Override
	public void insertSapFeedbackResultOrder(SapFeedbackResultOrder sapFeedbackResultOrder) throws BaseException {
		this.sapFeedbackResultOrderDao.insertSapFeedbackResultOrder(sapFeedbackResultOrder);
	}

	@Override
	public void updateSapFeedbackResultOrder(SapFeedbackResultOrder sapFeedbackResultOrder) throws BaseException {
		this.sapFeedbackResultOrderDao.updateSapFeedbackResultOrder(sapFeedbackResultOrder);
	}

	@Override
	public void deleteSapFeedbackResultOrder(Map<String, Object> inMap) throws BaseException {
		this.sapFeedbackResultOrderDao.deleteSapFeedbackResultOrder(inMap);
	}

	@Override
	public SapFeedbackResultOrder findSapFeedbackResultOrderById(Map<String, Object> inMap)
			throws BaseException {
		return this.sapFeedbackResultOrderDao.findSapFeedbackResultOrderById(inMap);
	}

	@Override
	public List findSapFeedbackResultOrder(Map<String, Object> inMap) throws BaseException {
		return this.sapFeedbackResultOrderDao.findSapFeedbackResultOrder(inMap);
	}

	@Override
	public Page findSapFeedbackResultOrderByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.sapFeedbackResultOrderDao.findSapFeedbackResultOrderByPage(inMap, param);
	}

}
