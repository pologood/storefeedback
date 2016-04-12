package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.FeedbackMessagePushRecordDao;
import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackMessagePushRecordService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 反馈通知消息推送记录 业务层接口实现类
 * @author Ma.Mingyang
 * @date 2015年1月22日下午3:55:48
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("feedbackMessagePushRecordService")
public class FeedbackMessagePushRecordServiceImpl implements
		FeedbackMessagePushRecordService {

	private FeedbackMessagePushRecordDao feedbackMessagePushRecordDao;
	
	
	
	public FeedbackMessagePushRecordDao getFeedbackMessagePushRecordDao() {
		return feedbackMessagePushRecordDao;
	}
	/**
	 * 注入
	 * @param feedbackMessagePushRecordDao
	 */
	@Autowired
	public void setFeedbackMessagePushRecordDao(
			FeedbackMessagePushRecordDao feedbackMessagePushRecordDao) {
		this.feedbackMessagePushRecordDao = feedbackMessagePushRecordDao;
	}

	@Override
	public void insertFeedbackMessagePushRecord(
			FeedbackMessagePushRecord feedbackMessagePushRecord)
			throws BaseException {
		feedbackMessagePushRecordDao.insertFeedbackMessagePushRecord(feedbackMessagePushRecord);
	}

	@Override
	public void deleteFeedbackMessagePushRecord(Map<String, Object> inMap)
			throws BaseException {
		feedbackMessagePushRecordDao.deleteFeedbackMessagePushRecord(inMap);
	}

	@Override
	public void updateFeedbackMessagePushRecord(
			FeedbackMessagePushRecord feedbackMessagePushRecord)
			throws BaseException {
		feedbackMessagePushRecordDao.updateFeedbackMessagePushRecord(feedbackMessagePushRecord);
	}

	@Override
	public FeedbackMessagePushRecord findFeedbackMessagePushRecordById(
			Map<String, Object> inMap) throws BaseException {
		return feedbackMessagePushRecordDao.findFeedbackMessagePushRecordById(inMap);
	}

	@Override
	public List<FeedbackMessagePushRecord> findFeedbackMessagePushRecord(
			Map<String, Object> inMap) throws BaseException {
		return feedbackMessagePushRecordDao.findFeedbackMessagePushRecord(inMap);
	}

	@Override
	public Page<FeedbackMessagePushRecord> findFeedbackMessagePushRecordByPage(
			Map<String, Object> inMap, PaginationParameters param)
			throws BaseException {
		return feedbackMessagePushRecordDao.findFeedbackMessagePushRecordByPage(inMap, param);
	}

}
