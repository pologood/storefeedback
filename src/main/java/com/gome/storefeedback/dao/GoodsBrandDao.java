package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.GoodsBrand;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 商品品牌
 * 
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:09:24
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface GoodsBrandDao {

    /**
     * 添加商品品牌
     * 
     * @param goodsBrand
     * @throws BaseException
     */
    public void insertGoodsBrand(GoodsBrand goodsBrand) throws BaseException;

    /**
     * 更新商品品牌
     * 
     * @param goodsBrand
     * @throws BaseException
     */
    public void updateGoodsBrand(GoodsBrand goodsBrand) throws BaseException;

    /**
     * 删除商品品牌
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteGoodsBrand(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找商品品牌
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public GoodsBrand findGoodsBrandById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找商品品牌列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findGoodsBrand(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findGoodsBrandByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 插入数据批量
     * 
     * @param parseXML
     * @throws BaseException
     */
    public void insertBatchGoodsBrand(List<GoodsBrand> parseXML) throws BaseException;

    /**
     * 分页查询 只返回 code name
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findAppListByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
}
