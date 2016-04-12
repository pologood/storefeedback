package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午2:42:03
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("feedbackDao")
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Feedback> implements FeedbackDao {

    @Override
    public void insertFeedback(Feedback feedback) throws BaseException {
        try {
            this.insert("Mapper.Feedback.insert", feedback);
        } catch (Exception e) {
            throw new BaseException("insert feedback error.", e);
        }
    }

    @Override
    public void updateFeedback(Feedback feedback) throws BaseException {
        try {
            this.update("Mapper.Feedback.update", feedback);
        } catch (Exception e) {
            throw new BaseException("update feedback error.", e);
        }
    }

    @Override
    public void deleteFeedback(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.Feedback.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete feedback error.", e);
        }
    }

    @Override
    public Feedback findFeedbackById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.Feedback.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findFeedbackById error.", e);
        }
    }

    @Override
    public List findFeedback(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.Feedback.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findFeedback error.", e);
        }
    }

    @Override
    public Page findFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.Feedback.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findFeedbackByPage error.", e);
        }
    }

    @Override
    public Page findPageBySponsor(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.Feedback.listBySponsor", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findPageBySponsor error.", e);
        }
    }

    @Override
    public Page findPageByReciever(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.Feedback.listByReciever", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findPageByReciever error.", e);
        }
    }

    @Override
    public void updateHasReturn(Map<String, Object> inMap) throws BaseException {
        try {
            this.update("Mapper.Feedback.updateHasReturn", inMap);
        } catch (Exception e) {
            throw new BaseException("update feedback error.", e);
        }
    }

}
