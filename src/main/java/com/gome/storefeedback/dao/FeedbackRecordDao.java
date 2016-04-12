package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午4:44:02
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FeedbackRecordDao {

	/**
	 * 添加反馈信息记录
	 * @param feedbackRecord
	 * @throws BaseException
	 */
	public void insertFeedbackRecord(FeedbackRecord feedbackRecord) throws BaseException;
	/**
	 * 更新反馈信息记录
	 * @param feedbackRecord
	 * @throws BaseException
	 */
	public void updateFeedbackRecord(FeedbackRecord feedbackRecord) throws BaseException;
	/**
	 * 删除反馈信息记录
	 * @param inMap
	 * @throws BaseException
	 */
	public void deleteFeedbackRecord(Map<String, Object> inMap) throws BaseException;
	/**
	 * 通过Id查找反馈信息记录
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public FeedbackRecord findFeedbackRecordById(Map<String, Object> inMap) throws BaseException;
	/**
	 * 查找反馈信息记录列表
	 * @param inMap
	 * @return
	 * @throws BaseException
	 */
	public List findFeedbackRecord(Map<String, Object> inMap) throws BaseException;
	/**
	 * 分页查询
	 * @param inMap
	 * @param param
	 * @return
	 * @throws BaseException
	 */
	public Page findFeedbackRecordByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException;
}
