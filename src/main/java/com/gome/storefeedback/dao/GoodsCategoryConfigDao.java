package com.gome.storefeedback.dao;


import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.GoodsCategoryConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * @author Zhang.Mingji
 * @date 2015年7月17日下午3:22:26
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface GoodsCategoryConfigDao {
	/**
	 * 删除品类配置
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
    int deleteGoodsCategoryConfig(Map<String,Object> inMap) throws BaseException;
    /**
     * 添加品类配置
     * @param record
     * @return
     * @throws BaseException
     */
    int insertGoodsCategoryConfig(GoodsCategoryConfig record) throws BaseException;

    /**
     * 查找品类配置
     * @param inMap
     * @return
     * @throws BaseException
     */
    List findGoodsCategoryConfig(Map<String,Object> inMap) throws BaseException;

    /**
     * 通过ID查找品类配置
     * @param inMap
     * @return
     * @throws BaseException
     */
    GoodsCategoryConfig findGoodsCategoryConfigById(Map<String,Object> inMap) throws BaseException;
    /**
     * 更新品类配置
     * @param record
     * @return
     * @throws BaseException
     */
    int updateGoodsCategoryConfig(GoodsCategoryConfig record) throws BaseException;

    /**
     * 分页查找品类配置
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    Page findGoodsCategoryConfigByPage(Map<String,Object> inMap, PaginationParameters param) throws BaseException;
}