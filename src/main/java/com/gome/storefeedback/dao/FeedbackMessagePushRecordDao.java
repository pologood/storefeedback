package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 反馈通知消息推送记录接口
 * 
 * @author Ma.Mingyang
 * @date 2015年1月22日下午2:22:03
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackMessagePushRecordDao {
	/**
	 * 添加记录
	 * @param feedbackMessagePushRecord
	 * @throws BaseException
	 */
	public void insertFeedbackMessagePushRecord(FeedbackMessagePushRecord feedbackMessagePushRecord) throws BaseException;
	/**
	 * 根据主键删除记录
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteFeedbackMessagePushRecord(Map<String, Object> inMap) throws BaseException;
	/**
	 * 更新记录
	 * @param feedbackMessagePushRecord
	 * @throws BaseException
	 */
	public void updateFeedbackMessagePushRecord(FeedbackMessagePushRecord feedbackMessagePushRecord) throws BaseException;
	/**
	 * 根据主键查找
	 * @param inMap
	 * @return FeedbackMessagePushRecord 
	 * @throws BaseException
	 */
	public FeedbackMessagePushRecord findFeedbackMessagePushRecordById(Map<String, Object> inMap) throws BaseException;
	/**
	 * 根据参数参照记录集合
	 * @param inMap
	 * @return List
	 * @throws BaseException
	 */
	public List<FeedbackMessagePushRecord> findFeedbackMessagePushRecord(Map<String, Object> inMap) throws BaseException;
	/**
	 * 分页查询
	 * @param inMap
	 * @param param
	 * @return page
	 * @throws BaseException
	 */
	public Page<FeedbackMessagePushRecord> findFeedbackMessagePushRecordByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException;
} 
