package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午3:26:39
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackPushCategoryConfigDao {
    /**
     * 删除品类配置
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    int delete(Map<String, Object> inMap) throws BaseException;

    /**
     * 添加品类配置
     * 
     * @param record
     * @return
     * @throws BaseException
     */
    int insert(FeedbackPushCategoryConfig record) throws BaseException;

    /**
     * 查找品类配置
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    List find(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过ID查找品类配置
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    FeedbackPushCategoryConfig findById(Map<String, Object> inMap) throws BaseException;

    /**
     * 更新品类配置
     * 
     * @param record
     * @return
     * @throws BaseException
     */
    int update(FeedbackPushCategoryConfig record) throws BaseException;

    /**
     * 分页查找品类配置
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    Page findByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

	public List<FeedbackPushCategoryConfig> findList(Map<String, Object> inMap) throws BaseException;

}