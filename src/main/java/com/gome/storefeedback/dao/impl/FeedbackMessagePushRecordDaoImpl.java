package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.FeedbackMessagePushRecordDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 反馈通知消息推送记录接口实现类
 * 
 * @author Ma.Mingyang
 * @date 2015年1月22日下午3:30:15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Repository("feedbackMessagePushRecordDao")
public class FeedbackMessagePushRecordDaoImpl extends
		BaseDaoImpl<FeedbackMessagePushRecord, FeedbackMessagePushRecord>
		implements FeedbackMessagePushRecordDao {

	@Override
	public void insertFeedbackMessagePushRecord(
			FeedbackMessagePushRecord feedbackMessagePushRecord)
			throws BaseException {
		try {
			this.insert("Mapper.FeedbackMessagePushRecord.insertRecord",
					feedbackMessagePushRecord);
		} catch (Exception e) {
			throw new BaseException("insert feedbackMessagePushRecord error!",
					e);
		}
	}

	@Override
	public void deleteFeedbackMessagePushRecord(Map<String, Object> inMap)
			throws BaseException {
		try {
			this.delete(
					"Mapper.FeedbackMessagePushRecord.deleteRecordByPrimaryKey",
					inMap);
		} catch (Exception e) {
			throw new BaseException("delete feedbackMessagePushRecord error!",
					e);
		}
	}

	@Override
	public void updateFeedbackMessagePushRecord(
			FeedbackMessagePushRecord feedbackMessagePushRecord)
			throws BaseException {
		try {
			this.update("Mapper.FeedbackMessagePushRecord.updateRecord",
					feedbackMessagePushRecord);
		} catch (Exception e) {
			throw new BaseException("update feedbackMessagePushRecord error!",
					e);
		}
	}

	@Override
	public FeedbackMessagePushRecord findFeedbackMessagePushRecordById(
			Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByID(
					"Mapper.FeedbackMessagePushRecord.findRecordById", inMap);
		} catch (Exception e) {
			throw new BaseException("find feedbackMessagePushRecord error!", e);
		}
	}

	@Override
	public List<FeedbackMessagePushRecord> findFeedbackMessagePushRecord(
			Map<String, Object> inMap) throws BaseException {
		try {
			return this
					.findByParam(
							"Mapper.FeedbackMessagePushRecord.findRecordByParam",
							inMap);
		} catch (Exception e) {
			throw new BaseException("find FeedbackMessagePushRecord error!", e);
		}
	}

	@Override
	public Page<FeedbackMessagePushRecord> findFeedbackMessagePushRecordByPage(
			Map<String, Object> inMap, PaginationParameters param)
			throws BaseException {
		try {
			return this.findByPage(
					"Mapper.FeedbackMessagePushRecord.findRecordByParam", inMap,
					param);
		} catch (Exception e) {
			throw new BaseException(
					"find FeedbackMessagePushRecord by page error!", e);
		}
	}

}
