package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.SapOrderDao;
import com.gome.storefeedback.entity.SapOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapOrderService实现
 * 
 * @author
 * @date 2015年07月20日 15时38分36秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("sapOrderService")
public class SapOrderServiceImpl implements SapOrderService {

    private SapOrderDao sapOrderDao;

    public SapOrderDao getSapOrderDao() {
        return sapOrderDao;
    }

    @Autowired
    public void setSapOrderDao(@Qualifier("sapOrderDao") SapOrderDao sapOrderDao) {
        this.sapOrderDao = sapOrderDao;
    }

    @Override
    public void pi(List<SapOrder> sapOrders) throws BaseException {
        List<SapOrder> insertList = new ArrayList<SapOrder>();
        for (SapOrder sapOrder : sapOrders) {
            HashMap<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("orderId", sapOrder.getOrderId().trim());
            inMap.put("orderContent", sapOrder.getOrderContent());
//            inMap.put("orderDate", sapOrder.getOrderDate());
//            inMap.put("goodsCode", sapOrder.getGoodsCode());
            List orderList = this.findSapOrder(inMap);
            if (orderList != null && orderList.size() > 0) {
                // 修改操作
                sapOrder.setUpdateTime(new Date());
                this.updateByOrderIdSapOrder(sapOrder);
            } else {
                // 增加操作
                sapOrder.setCreateTime(new Date());
                insertList.add(sapOrder);
            }
        }
        if (insertList != null && insertList.size() > 0) {
            this.batchSapOrder(insertList);
        }
    }

    @Override
    public void batchSapOrder(List<SapOrder> sapOrders) throws BaseException {
        this.sapOrderDao.batchSapOrder(sapOrders);
    }

    @Override
    public void insertSapOrder(SapOrder sapOrder) throws BaseException {
        this.sapOrderDao.insertSapOrder(sapOrder);
    }

    @Override
    public void updateByOrderIdSapOrder(SapOrder sapOrder) throws BaseException {
        this.sapOrderDao.updateByOrderIdSapOrder(sapOrder);
    }

    @Override
    public void updateSapOrder(SapOrder sapOrder) throws BaseException {
        this.sapOrderDao.updateSapOrder(sapOrder);
    }

    @Override
    public void deleteSapOrder(Map<String, Object> inMap) throws BaseException {
        this.sapOrderDao.deleteSapOrder(inMap);
    }

    @Override
    public SapOrder findSapOrderById(Map<String, Object> inMap) throws BaseException {
        return this.sapOrderDao.findSapOrderById(inMap);
    }

    @Override
    public List findSapOrder(Map<String, Object> inMap) throws BaseException {
        return this.sapOrderDao.findSapOrder(inMap);
    }

    @Override
    public Page findSapOrderByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.sapOrderDao.findSapOrderByPage(inMap, param);
    }

}
