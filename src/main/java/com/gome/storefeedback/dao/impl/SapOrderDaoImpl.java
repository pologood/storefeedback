package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapOrderDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapOrder;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapOrderDao实现
 * 
 * @author
 * @date 2015年07月20日 15时38分36秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("sapOrderDao")
public class SapOrderDaoImpl extends BaseDaoImpl<SapOrder, SapOrder> implements SapOrderDao {

    @Override
    public void batchSapOrder(@Param("list") List<SapOrder> sapOrders) throws BaseException {
        try {
            this.insert("Mapper.SapOrder.batch", sapOrders);
        } catch (Exception e) {
            throw new BaseException("batch sapOrder error.", e);
        }
    }

    @Override
    public void insertSapOrder(SapOrder sapOrder) throws BaseException {
        try {
            this.insert("Mapper.SapOrder.insert", sapOrder);
        } catch (Exception e) {
            throw new BaseException("insert sapOrder error.", e);
        }
    }

    @Override
    public void updateByOrderIdSapOrder(SapOrder sapOrder) throws BaseException {
        try {
            this.update("Mapper.SapOrder.updateByOrderId", sapOrder);
        } catch (Exception e) {
            throw new BaseException("updateByOrderId sapOrder error.", e);
        }
    }

    @Override
    public void updateSapOrder(SapOrder sapOrder) throws BaseException {
        try {
            this.update("Mapper.SapOrder.updateByPK", sapOrder);
        } catch (Exception e) {
            throw new BaseException("updateByPK sapOrder error.", e);
        }
    }

    @Override
    public void deleteSapOrder(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.SapOrder.deleteByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("deleteByPK sapOrder error.", e);
        }
    }

    @Override
    public SapOrder findSapOrderById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.SapOrder.selectByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapOrderById error.", e);
        }
    }

    @Override
    public List findSapOrder(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.SapOrder.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapOrder error.", e);
        }
    }

    @Override
    public Page findSapOrderByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.SapOrder.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findSapOrderByPage error.", e);
        }
    }

}
