package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 商品
 * 
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:16:49
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface GoodsService {

    /**
     * 添加商品
     * 
     * @param goods
     * @throws BaseException
     */
    public void insertGoods(Goods goods) throws BaseException;

    /**
     * 更新商品
     * 
     * @param goods
     * @throws BaseException
     */
    public void updateGoods(Goods goods) throws BaseException;

    /**
     * 删除商品
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteGoods(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找商品
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public Goods findGoodsById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找商品列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findGoods(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findGoodsByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 批量插入到数据库
     * 
     * @param parseXML
     * @throws BaseException
     */
    public void insertBatchGoods(List<Goods> parseXML) throws BaseException;

    /**
     * 分页查找 返回完成 Goods信息
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    Page findGoodsByPage2(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 根据商品编码获取商品信息
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
     Map<String,String> goodInfo(Map<String, Object> inMap) throws BaseException;

}
