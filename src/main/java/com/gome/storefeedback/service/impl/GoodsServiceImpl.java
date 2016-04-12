package com.gome.storefeedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.GoodsDao;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:27:17
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao;

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    @Autowired
    public void setGoodsDao(@Qualifier("goodsDao") GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public void insertGoods(Goods goods) throws BaseException {
        this.goodsDao.insertGoods(goods);
    }

    @Override
    public void updateGoods(Goods goods) throws BaseException {
        this.goodsDao.updateGoods(goods);
    }

    @Override
    public void deleteGoods(Map<String, Object> inMap) throws BaseException {
        this.goodsDao.deleteGoods(inMap);
    }

    @Override
    public Goods findGoodsById(Map<String, Object> inMap) throws BaseException {
        return this.goodsDao.findGoodsById(inMap);
    }

    @Override
    public List findGoods(Map<String, Object> inMap) throws BaseException {
        return this.goodsDao.findGoods(inMap);
    }

    @Override
    public Page findGoodsByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.goodsDao.findGoodsByPage(inMap, param);
    }

    @Override
    public void insertBatchGoods(List<Goods> goods) throws BaseException {
        for (Goods good : goods) {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("goodsCode", good.getGoodsCode());
            Goods oldGood = this.findGoodsById(inMap);
            if (null == oldGood) {
                // Insert
                good.setUpdateFlag("C");
                this.insertGoods(good);
            } else {
                // Delete
                if (good.getUpdateFlag() != null && "D".equals(good.getUpdateFlag())) {
                    this.deleteGoods(inMap);
                } else {
                    // Update
                    good.setUpdateFlag("M");
                    this.updateGoods(good);
                }
            }
        }
    }

    @Override
    public Page findGoodsByPage2(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.goodsDao.findGoodsByPage2(inMap, param);
    }
    
    @Override
    public Map<String,String> goodInfo(Map<String, Object> inMap) throws BaseException{
        return this.goodsDao.goodInfo(inMap);
    }
}
