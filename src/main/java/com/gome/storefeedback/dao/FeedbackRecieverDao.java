package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackReciever;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * 缺货记录_接收人Dao接口
 * 
 * @author
 * @date 2015年03月06日 15时31分19秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackRecieverDao {

    /**
     * 添加缺货记录_接收人
     * 
     * @param feedbackReciever
     * @throws BaseException
     */
    public void insertFeedbackReciever(FeedbackReciever feedbackReciever) throws BaseException;

    /**
     * 批量新增缺货记录_接收人
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
