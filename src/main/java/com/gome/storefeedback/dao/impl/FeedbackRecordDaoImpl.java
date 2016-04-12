package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackRecordDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午5:02:33
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("feedbackRecordDao")
public class FeedbackRecordDaoImpl extends BaseDaoImpl<FeedbackRecord, FeedbackRecord> implements FeedbackRecordDao {

    @Override
    public void insertFeedbackRecord(FeedbackRecord feedbackRecord) throws BaseException {
        try {
            this.insert("Mapper.FeedbackRecord.insert", feedbackRecord);
        } catch (Exception e) {
            throw new BaseException("insert feedbackRecord error.", e);
        }
    }

    @Override
    public void updateFeedbackRecord(FeedbackRecord feedbackRecord) throws BaseException {
        try {
            this.update("Mapper.FeedbackRecord.update", feedbackRecord);
        } catch (Exception e) {
            throw new BaseException("update feedbackRecord error.", e);
        }
    }

    @Override
    public void deleteFeedbackRecord(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.FeedbackRecord.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete feedbackRecord error.", e);
        }
    }

    @Override
    public FeedbackRecord findFeedbackRecordById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.FeedbackRecord.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findFeedbackRecordById error.", e);
        }
    }

    @Override
    public List findFeedbackRecord(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.FeedbackRecord.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findFeedbackRecord error.", e);
        }
    }

    @Override
    public Page findFeedbackRecordByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.FeedbackRecord.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findFeedbackRecordByPage error.", e);
        }
    }


}
