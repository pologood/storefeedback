package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 商品品类
 * 
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:20:47
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface GoodsCategoryService {

    /**
     * 添加商品品类
     * 
     * @param goodsCategory
     * @throws BaseException
     */
    public void insertGoodsCategory(GoodsCategory goodsCategory) throws BaseException;

    /**
     * 更新商品品类
     * 
     * @param goodsCategory
     * @throws BaseException
     */
    public void updateGoodsCategory(GoodsCategory goodsCategory) throws BaseException;

    /**
     * 删除商品品类
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteGoodsCategory(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找商品品类
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public GoodsCategory findGoodsCategoryById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找商品品类列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findGoodsCategory(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findGoodsCategoryByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 批量插入
     * 
     * @param parseXML
     * @throws BaseException
     */
    public void insertBatchGoodsCategory(List<GoodsCategory> parseXML) throws BaseException;

    /**
     * 分页查询 只返回code name
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findAppListByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
    
    /**
     * 查询一级品类
     * @param inMap
     * @param paginationParameters
     * @return
     * @throws BaseException 
     */
    public List findBaseCategorys(Map<String, Object> inMap) throws BaseException;

}
