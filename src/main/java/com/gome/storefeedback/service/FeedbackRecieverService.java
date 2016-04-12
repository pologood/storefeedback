package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackReciever;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * 缺货记录_接收人 Service接口
 * 
 * @author
 * @date 2015年03月06日 15时31分19秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackRecieverService {

    /**
     * 添加缺货记录_接收人
     * 
     * @param feedbackReciever
     * @throws BaseException
     */
    public void insertFeedbackReciever(FeedbackReciever feedbackReciever) throws BaseException;

    /**
     * 批量插入缺货记录_接收人
     * 
     * @param feedbackReciever
     * @throws BaseException
     */
    public void insertBatchFeedbackReciever(List<FeedbackReciever> feedbackRecievers) throws BaseException;

    /**
     * 删除缺货记录_接收人
     * 
     * @param inMap
     * @throws BaseException
     */
    public void deleteFeedbackReciever(Map<String, Object> inMap) throws BaseException;

    /**
     * 查找缺货记录_接收人列表
     * 
     * @param inMap
     * @return
     * @throws BaseException
     */
    public List findFeedbackReciever(Map<String, Object> inMap) throws BaseException;

    /**
     * 分页查询
     * 
     * @param inMap
     * @param param
     * @return
     * @throws BaseException
     */
    public Page findFeedbackRecieverByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;

}
