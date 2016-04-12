package com.gome.storefeedback.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.GoodsCategoryDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午3:12:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("goodsCategoryDao")
public class GoodsCategoryDaoImpl extends BaseDaoImpl<GoodsCategory, GoodsCategory> implements GoodsCategoryDao {

    @Override
    public void insertGoodsCategory(GoodsCategory goodsCategory) throws BaseException {
        try {
            this.insert("Mapper.GoodsCategory.insert", goodsCategory);
        } catch (Exception e) {
            throw new BaseException("insert goodsCategory error.", e);
        }
    }

    @Override
    public void updateGoodsCategory(GoodsCategory goodsCategory) throws BaseException {
        try {
            this.update("Mapper.GoodsCategory.update", goodsCategory);
        } catch (Exception e) {
            throw new BaseException("update goodsCategory error.", e);
        }
    }

    @Override
    public void deleteGoodsCategory(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.GoodsCategory.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete goodsCategory error.", e);
        }
    }

    @Override
    public GoodsCategory findGoodsCategoryById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.GoodsCategory.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findGoodsCategoryById error.", e);
        }
    }

    @Override
    public List findGoodsCategory(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.GoodsCategory.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findGoodsCategory error.", e);
        }
    }

    @Override
    public Page findGoodsCategoryByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.GoodsCategory.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findGoodsCategoryByPage error.", e);
        }
    }

    @Override
    public Page findAppListByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.GoodsCategory.applist", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findGoodsCategoryByPage error.", e);
        }
    }

    @Override
    public void insertBatchGoodsCategory(List<GoodsCategory> goodsCategorys) throws BaseException {
        try {
            this.insert("Mapper.GoodsCategory.batchInsert", goodsCategorys);
        } catch (Exception e) {
            throw new BaseException("insert GoodsCategory batch error.", e);
        }

    }

    @Override
    public List findBaseCategorys(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.GoodsCategory.BaseCategorylist", inMap);
        } catch (Exception e) {
            throw new BaseException("BaseCategorylist error.", e);
        }
    }

}
