package com.gome.storefeedback.service;

import java.util.List;

import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.exception.BaseException;

public interface FeedbackToOaService {
	 	int deleteByPrimaryKey(String id);

	    int insert(FeedbackToOa record);

	    int insertSelective(FeedbackToOa record);

	    FeedbackToOa selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(FeedbackToOa record);

	    int updateByPrimaryKey(FeedbackToOa record);

		void insertBatch(List<FeedbackToOa> resultList) throws BaseException;
}
