package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.exception.BaseException;

public interface FeedbackAppealService {
	public int deleteByPrimaryKey(String id);

	public int insert(FeedbackAppeal record);

	public int insertSelective(FeedbackAppeal record);

	public FeedbackAppeal selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(FeedbackAppeal record);

	public int updateByPrimaryKey(FeedbackAppeal record) throws BaseException;
	
	

	public void insertBatch(List<FeedbackAppeal> resultList) throws BaseException;

	public List<FeedbackAppeal> findFeedbackAppealList(Map<String, Object> record) throws BaseException;
}
