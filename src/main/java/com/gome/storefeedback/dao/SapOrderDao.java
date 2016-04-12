package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * 采购订单数据Dao接口
 * 
 * @author
 * @date 2015年07月20日 15时38分36秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface SapOrderDao {
    /**
     * 批量添加采购订单数据
     * 
     * @param sapOrder
     * @throws BaseException
     */
    public void batchSapOrder(List<SapOrder> sapOrders) throws BaseException;

    /**
     * 添加采购订单数据
     * 
     * @param sapOrder
     * @throws BaseException
     */
    public void insertSapOrder(SapOrder sapOrder) throws BaseException;

    /**
     * 根据订单号 更新采购订单数据
     * 
     * @param sapOrder
     * @throws BaseException
     */
    public void updateByOrderIdSapOrder(SapOrder sapOrder) throws BaseException;

    /**
     * 更新采购订单数据
     * 
     * @param sapOrder
     * @throws BaseException
     */
    public void updateSapOrder(SapOrder sapOrder) throws BaseException;

    /**
     * 删除采购订单数据
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteSapOrder(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找采购订单数据
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public SapOrder findSapOrderById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找采购订单数据列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findSapOrder(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findSapOrderByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

}
