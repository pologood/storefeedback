package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackResultOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * 处理和采购订单号的关系Dao接口
 * 
 * @author 
 * @date 2015年07月23日 19时07分04秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SapFeedbackResultOrderDao {

	/**
	 * 批量添加处理和采购订单号的关系
	 * @param sapFeedbackResultOrder
	 * @throws BaseException
	 */
	public void batchSapFeedbackResultOrder(List<SapFeedbackResultOrder> sapFeedbackResultOrders) throws BaseException;
	/**
	 * 添加处理和采购订单号的关系
	 * @param sapFeedbackResultOrder
	 * @throws BaseException
	 */
	public void insertSapFeedbackResultOrder(SapFeedbackResultOrder sapFeedbackResultOrder) throws BaseException;
	/**
	 * 更新处理和采购订单号的关系
	 * @param sapFeedbackResultOrder
	 * @throws BaseException
	 */
	public void updateSapFeedbackResultOrder(SapFeedbackResultOrder sapFeedbackResultOrder) throws BaseException;
	/**
	 * 删除处理和采购订单号的关系
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteSapFeedbackResultOrder(Map<String, Object> inMap) throws BaseException;
	/**
	 * 通过Id查找处理和采购订单号的关系
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public SapFeedbackResultOrder findSapFeedbackResultOrderById(Map<String, Object> inMap) throws BaseException;
	/**
	 * 查找处理和采购订单号的关系列表
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List findSapFeedbackResultOrder(Map<String, Object> inMap) throws BaseException;
	/**
	 * 分页查询
	 * @param inMap
	 * @param param
	 * @return
	 * @throws BaseException
	 */
	public Page findSapFeedbackResultOrderByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
}
