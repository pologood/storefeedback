package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackResultOrderDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapFeedbackResultOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackResultOrderDao实现
 * 
 * @author 
 * @date 2015年07月23日 19时07分04秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("sapFeedbackResultOrderDao")
public class SapFeedbackResultOrderDaoImpl extends BaseDaoImpl<SapFeedbackResultOrder, SapFeedbackResultOrder> implements SapFeedbackResultOrderDao {

	@Override
	public void batchSapFeedbackResultOrder(List<SapFeedbackResultOrder> sapFeedbackResultOrders) throws BaseException {
		try {
			this.insert("Mapper.SapFeedbackResultOrder.batch", sapFeedbackResultOrders);
		} catch (Exception e) {
			throw new BaseException("batch sapFeedbackResultOrder error.", e);
		}
	}
	
	@Override
	public void insertSapFeedbackResultOrder(SapFeedbackResultOrder sapFeedbackResultOrder) throws BaseException {
		try {
			this.insert("Mapper.SapFeedbackResultOrder.insert", sapFeedbackResultOrder);
		} catch (Exception e) {
			throw new BaseException("insert sapFeedbackResultOrder error.", e);
		}
	}

	@Override
	public void updateSapFeedbackResultOrder(SapFeedbackResultOrder sapFeedbackResultOrder) throws BaseException {
		try {
			this.update("Mapper.SapFeedbackResultOrder.updateByPK", sapFeedbackResultOrder);
		} catch (Exception e) {
			throw new BaseException("update sapFeedbackResultOrder error.", e);
		}
	}

	@Override
	public void deleteSapFeedbackResultOrder(Map<String, Object> inMap) throws BaseException {
		try {
			this.delete("Mapper.SapFeedbackResultOrder.deleteByPK", inMap);
		} catch (Exception e) {
			throw new BaseException("delete sapFeedbackResultOrder error.", e);
		}
	}

	@Override
	public SapFeedbackResultOrder findSapFeedbackResultOrderById(Map<String, Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.SapFeedbackResultOrder.selectByPK", inMap);
		} catch (Exception e) {
			throw new BaseException("findSapFeedbackResultOrderById error.", e);
		}
	}

	@Override
	public List findSapFeedbackResultOrder(Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByParam("Mapper.SapFeedbackResultOrder.list", inMap);
		} catch (Exception e) {
			throw new BaseException("findSapFeedbackResultOrder error.", e);
		}
	}

	@Override
	public Page findSapFeedbackResultOrderByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		try {
			return this.findByPage("Mapper.SapFeedbackResultOrder.list", inMap, param);
		} catch (Exception e) {
			throw new BaseException("findSapFeedbackResultOrderByPage error.", e);
		}
	}

}
