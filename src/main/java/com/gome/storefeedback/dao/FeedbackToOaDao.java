package com.gome.storefeedback.dao;

import java.util.List;

import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.exception.BaseException;

public interface FeedbackToOaDao {
    int deleteByPrimaryKey(String id);

    int insert(FeedbackToOa record);

    int insertSelective(FeedbackToOa record);

    FeedbackToOa selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FeedbackToOa record);

    int updateByPrimaryKey(FeedbackToOa record);

	void insertBatch(List<FeedbackToOa> resultList) throws BaseException;
}