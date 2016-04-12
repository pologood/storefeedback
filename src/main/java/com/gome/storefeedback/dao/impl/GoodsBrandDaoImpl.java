package com.gome.storefeedback.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.GoodsBrandDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.entity.GoodsBrand;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:14:54
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("goodsBrandDao")
public class GoodsBrandDaoImpl extends BaseDaoImpl<GoodsBrand, GoodsBrand> implements GoodsBrandDao {

    @Override
    public void insertGoodsBrand(GoodsBrand goodsBrand) throws BaseException {
        try {
            this.insert("Mapper.GoodsBrand.insert", goodsBrand);
        } catch (Exception e) {
            throw new BaseException("insert goodsBrand error.", e);
        }
    }

    @Override
    public void updateGoodsBrand(GoodsBrand goodsBrand) throws BaseException {
        try {
            this.update("Mapper.GoodsBrand.update", goodsBrand);
        } catch (Exception e) {
            throw new BaseException("update goodsBrand error.", e);
        }
    }

    @Override
    public void deleteGoodsBrand(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.GoodsBrand.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete goodsBrand error.", e);
        }
    }

    @Override
    public GoodsBrand findGoodsBrandById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.GoodsBrand.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findGoodsBrandById error.", e);
        }
    }

    @Override
    public List findGoodsBrand(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.GoodsBrand.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findGoodsBrand error.", e);
        }
    }

    @Override
    public Page findGoodsBrandByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.GoodsBrand.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findGoodsBrandByPage error.", e);
        }
    }

    @Override
    public Page findAppListByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.GoodsBrand.applist", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findGoodsBrandByPage error.", e);
        }
    }

    @Override
    public void insertBatchGoodsBrand(List<GoodsBrand> goodsBrands) throws BaseException {
        try {
            this.insert("Mapper.GoodsBrand.batchInsert", goodsBrands);
        } catch (Exception e) {
            throw new BaseException("inset GoodsBrand batch error.", e);
        }
    }

}
