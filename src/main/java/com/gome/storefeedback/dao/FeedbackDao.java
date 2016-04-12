package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午2:27:35
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackDao {

    /**
     * 添加缺货记录反馈
     * 
     * @param Feedback
     * @throws BaseException
     */
    public void insertFeedback(Feedback feedback) throws BaseException;

    /**
     * 更新缺货记录反馈
     * 
     * @param Feedback
     * @throws BaseException
     */
    public void updateFeedback(Feedback feedback) throws BaseException;

    /**
     * 删除缺货记录反馈
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteFeedback(Map<String, Object> inMap) throws BaseException;

    /**
     * 通过Id查找缺货记录反馈
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public Feedback findFeedbackById(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找缺货记录反馈列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findFeedback(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 分页查询 根据回执人
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findPageByReciever(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 分页查询 根据反馈人
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findPageBySponsor(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

    /**
     * 更新操作 是否回执 每有一次回执 hasReturn++
     * 
     * @param inMap
     * @throws BaseException 
     */
    public void updateHasReturn(Map<String, Object> inMap) throws BaseException;
}
