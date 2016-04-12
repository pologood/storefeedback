package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午4:46:08
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackPushCategoryPositionConfigService {
    /**
     * 删除品类职务
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public int delete(Map<String, Object> inMap) throws BaseException;

    /**
     * 添加品类职务
     * 
     * @param record
     * @return
     * @throws BaseException
     */
    public int insert(FeedbackPushCategoryPositionConfig record) throws BaseException;

    /**
     * 查找品类职务
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List find(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过ID查找品类职务
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public FeedbackPushCategoryPositionConfig findById(Map<String, Object> inMap) throws BaseException;

    /**
     * 更新品类职务
     * 
     * @param record
     * @return
     * @throws BaseException
     */
    public int update(FeedbackPushCategoryPositionConfig record) throws BaseException;

    /**
     * 分页查找品类职务
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 查找品类职务
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List<FeedbackPushCategoryPositionConfig> findAll(Map<String, Object> inMap) throws BaseException;

    /**
     * 
     * 根据 品类编码 获取部门
     * 
     * @param inMap
     * @param param
     * @return
     */
    public Page findOrgPageByCategory(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    public List<FeedbackPushCategoryPositionConfig> findPushPosition(Map<String, Object> positionMap) throws BaseException;

    /**
     * 根据 部门、品类编码 获取 FeedbackPushCategoryPositionConfig
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List<FeedbackPushCategoryPositionConfig> findPushCategoryPosition(Map<String,Object> inMap)throws BaseException;
    
    public List findRoleId(Map<String,Object> inMap)throws BaseException;

    public List checkCategoryPosition(Map<String, Object> inMap) throws BaseException;
}
