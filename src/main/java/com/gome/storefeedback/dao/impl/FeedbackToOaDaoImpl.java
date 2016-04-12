package com.gome.storefeedback.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackToOaDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.exception.BaseException;
@Component("feedbackToOaDao")
public class FeedbackToOaDaoImpl extends BaseDaoImpl<FeedbackToOa, FeedbackToOa>  implements FeedbackToOaDao {

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FeedbackToOa record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(FeedbackToOa record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FeedbackToOa selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(FeedbackToOa record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(FeedbackToOa record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertBatch(List<FeedbackToOa> resultList) throws BaseException {
		this.insert("Mapper.FeedbackToOa.insertBatch", resultList);
	}

}
