package com.gome.storefeedback.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.GoodsDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:15:04
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods, Goods> implements GoodsDao {

    @Override
    public void insertGoods(Goods goods) throws BaseException {
        try {
            this.insert("Mapper.Goods.insert", goods);
        } catch (Exception e) {
            throw new BaseException("insert goods error.", e);
        }
    }

    @Override
    public void updateGoods(Goods goods) throws BaseException {
        try {
            this.update("Mapper.Goods.update", goods);
        } catch (Exception e) {
            throw new BaseException("update goods error.", e);
        }
    }

    @Override
    public void deleteGoods(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.Goods.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete goods error.", e);
        }
    }

    @Override
    public Goods findGoodsById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.Goods.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findGoodsById error.", e);
        }
    }

    @Override
    public List findGoods(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.Goods.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findGoods error.", e);
        }
    }

    @Override
    public Page findGoodsByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.Goods.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findGoodsByPage error.", e);
        }
    }

    @Override
    public Page findGoodsByPage2(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.Goods.list2", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findGoodsByPage error.", e);
        }
    }

    @Override
    public void insertBatchGoods(List<Goods> goods) throws BaseException {
        try {
            this.insert("Mapper.Goods.batchInsert", goods);
        } catch (Exception e) {
            throw new BaseException("insert Goods batch error.", e);
        }
    }

    @Override
    public Map<String, String> goodInfo(Map<String, Object> inMap) throws BaseException {
        try {
            Page page = this.findByPage("Mapper.Goods.goodsInfo", inMap, new PaginationParameters());
            if (page != null && page.getTotalSize() > 0 && page.getDataList() != null && page.getDataList().size() > 0) {
                return (Map<String, String>) page.getDataList().get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new BaseException("goodInfo error.", e);
        }
    }

}
