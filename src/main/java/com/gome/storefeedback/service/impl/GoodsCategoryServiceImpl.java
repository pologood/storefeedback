package com.gome.storefeedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.GoodsCategoryDao;
import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsCategoryService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:26:25
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private GoodsCategoryDao goodsCategoryDao;

    public GoodsCategoryDao getGoodsCategoryDao() {
        return goodsCategoryDao;
    }

    @Autowired
    public void setGoodsCategoryDao(@Qualifier("goodsCategoryDao") GoodsCategoryDao goodsCategoryDao) {
        this.goodsCategoryDao = goodsCategoryDao;
    }

    @Override
    public void insertGoodsCategory(GoodsCategory goodsCategory) throws BaseException {
        this.goodsCategoryDao.insertGoodsCategory(goodsCategory);
    }

    @Override
    public void updateGoodsCategory(GoodsCategory goodsCategory) throws BaseException {
        this.goodsCategoryDao.updateGoodsCategory(goodsCategory);
    }

    @Override
    public void deleteGoodsCategory(Map<String, Object> inMap) throws BaseException {
        this.goodsCategoryDao.deleteGoodsCategory(inMap);
    }

    @Override
    public GoodsCategory findGoodsCategoryById(Map<String, Object> inMap) throws BaseException {
        return this.goodsCategoryDao.findGoodsCategoryById(inMap);
    }

    @Override
    public List findGoodsCategory(Map<String, Object> inMap) throws BaseException {
        return this.goodsCategoryDao.findGoodsCategory(inMap);
    }

    @Override
    public Page findGoodsCategoryByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.goodsCategoryDao.findGoodsCategoryByPage(inMap, param);
    }

    @Override
    public void insertBatchGoodsCategory(List<GoodsCategory> goodsCategorys) throws BaseException {
        for (GoodsCategory goodsCategory : goodsCategorys) {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("classCode", goodsCategory.getClassCode());
            GoodsCategory oldgoodsCategory = this.findGoodsCategoryById(inMap);
            if (null == oldgoodsCategory) {
                // Insert
                goodsCategory.setUpdateFlag("C");
                this.insertGoodsCategory(goodsCategory);
            } else {
                // Delete
                if (goodsCategory.getUpdateFlag() != null && "D".equals(goodsCategory.getUpdateFlag())) {
                    this.deleteGoodsCategory(inMap);
                } else {
                    // Update
                    goodsCategory.setUpdateFlag("M");
                    this.updateGoodsCategory(goodsCategory);
                }
            }
        }
    }

    @Override
    public Page findAppListByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.goodsCategoryDao.findAppListByPage(inMap, param);
    }

    @Override
    public List findBaseCategorys(Map<String, Object> inMap) throws BaseException {
        return this.goodsCategoryDao.findBaseCategorys(inMap);
    }

}
