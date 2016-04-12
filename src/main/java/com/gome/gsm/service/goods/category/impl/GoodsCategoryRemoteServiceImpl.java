package com.gome.gsm.service.goods.category.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.gsm.service.goods.category.GoodsCategoryRemoteService;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsCategoryService;


public class GoodsCategoryRemoteServiceImpl implements GoodsCategoryRemoteService {

    private Logger logger = LoggerFactory.getLogger(GoodsCategoryRemoteServiceImpl.class);

    @Resource(name="goodsCategoryService")
    private GoodsCategoryService goodsCategoryService;
    
    
    @Override
    public List getBaseCategory() throws BaseException {
        logger.info("------------invoke--getBaseCategory------------");
        return this.goodsCategoryService.findBaseCategorys(null);
    }
}
