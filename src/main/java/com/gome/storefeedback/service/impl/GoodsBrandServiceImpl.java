package com.gome.storefeedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.GoodsBrandDao;
import com.gome.storefeedback.dao.GoodsDao;
import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.entity.GoodsBrand;
import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsBrandService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:25:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("goodsBrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {

    private GoodsBrandDao goodsBrandDao;

    public GoodsBrandDao getGoodsBrandDao() {
        return goodsBrandDao;
    }

    @Autowired
    public void setGoodsBrandDao(@Qualifier("goodsBrandDao") GoodsBrandDao goodsBrandDao) {
        this.goodsBrandDao = goodsBrandDao;
    }

    @Override
    public void insertGoodsBrand(GoodsBrand goodsBrand) throws BaseException {
        this.goodsBrandDao.insertGoodsBrand(goodsBrand);
    }

    @Override
    public void updateGoodsBrand(GoodsBrand goodsBrand) throws BaseException {
        this.goodsBrandDao.updateGoodsBrand(goodsBrand);
    }

    @Override
    public void deleteGoodsBrand(Map<String, Object> inMap) throws BaseException {
        this.goodsBrandDao.deleteGoodsBrand(inMap);
    }

    @Override
    public GoodsBrand findGoodsBrandById(Map<String, Object> inMap) throws BaseException {
        return this.goodsBrandDao.findGoodsBrandById(inMap);
    }

    @Override
    public List findGoodsBrand(Map<String, Object> inMap) throws BaseException {
        return this.goodsBrandDao.findGoodsBrand(inMap);
    }

    @Override
    public Page findGoodsBrandByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.goodsBrandDao.findGoodsBrandByPage(inMap, param);
    }

    @Override
    public Page findAppListByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.goodsBrandDao.findAppListByPage(inMap, param);
    }

    @Override
    public void insertBatchGoodsBrand(List<GoodsBrand> goodsBrands) throws BaseException {
        for (GoodsBrand goodsBrand : goodsBrands) {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("brandCode", goodsBrand.getBrandCode());
            GoodsBrand oldGoodsBrand = this.findGoodsBrandById(inMap);
            if (null == oldGoodsBrand) {
                // Insert
                goodsBrand.setUpdateFlag("C");
                this.insertGoodsBrand(goodsBrand);
            } else {
                // Delete
                if (goodsBrand.getUpdateFlag() != null && "D".equals(goodsBrand.getUpdateFlag())) {
                    this.deleteGoodsBrand(inMap);
                } else {
                    // Update
                    goodsBrand.setUpdateFlag("M");
                    this.updateGoodsBrand(goodsBrand);
                }
            }
        }
    }
}
