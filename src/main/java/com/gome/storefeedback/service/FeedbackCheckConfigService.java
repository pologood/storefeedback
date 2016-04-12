package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

public interface FeedbackCheckConfigService {
    
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
    public int insert(FeedbackCheckConfig record) throws BaseException;

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
    public FeedbackCheckConfig findById(Map<String, Object> inMap) throws BaseException;

    /**
     * 更新品类职务
     * 
     * @param record
     * @return
     * @throws BaseException
     */
    public int update(FeedbackCheckConfig record) throws BaseException;

    /**
     * 分页查询-考核配置信息
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
    
    
    public List<FeedbackCheckConfig> findCheckEmpList(Map<String, Object> positionMap) throws BaseException;

}
