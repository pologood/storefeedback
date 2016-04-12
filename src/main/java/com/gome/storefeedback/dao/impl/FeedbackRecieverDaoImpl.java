package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackRecieverDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackReciever;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * FeedbackRecieverDao实现
 * 
 * @author
 * @date 2015年03月06日 15时31分19秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("feedbackRecieverDao")
public class FeedbackRecieverDaoImpl extends BaseDaoImpl<FeedbackReciever, FeedbackReciever> implements
        FeedbackRecieverDao {

    @Override
    public void insertFeedbackReciever(FeedbackReciever feedbackReciever) throws BaseException {
        try {
            this.insert("Mapper.FeedbackReciever.insert", feedbackReciever);
        } catch (Exception e) {
            throw new BaseException("insert feedbackReciever error.", e);
        }
    }

    @Override
    public void deleteFeedbackReciever(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.FeedbackReciever.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete feedbackReciever error.", e);
        }
    }

    @Override
    public List findFeedbackReciever(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.FeedbackReciever.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findFeedbackReciever error.", e);
        }
    }

    @Override
    public Page findFeedbackRecieverByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.FeedbackReciever.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findFeedbackRecieverByPage error.", e);
        }
    }

    @Override
    public void insertBatchFeedbackReciever(@Param("list") List<FeedbackReciever> feedbackRecievers) throws BaseException {
        try {
            this.insert("Mapper.FeedbackReciever.insertBatch", feedbackRecievers);
        } catch (Exception e) {
            throw new BaseException("insert feedbackReciever error.", e);
        }
    }

}
