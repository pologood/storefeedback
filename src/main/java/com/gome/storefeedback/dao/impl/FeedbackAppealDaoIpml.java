package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackAppealDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.exception.BaseException;
@Component("feedbackAppealDao")
public class FeedbackAppealDaoIpml extends BaseDaoImpl<FeedbackAppeal, FeedbackAppeal> implements FeedbackAppealDao {

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FeedbackAppeal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(FeedbackAppeal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FeedbackAppeal selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(FeedbackAppeal record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(FeedbackAppeal record) throws BaseException {
		return this.update("Mapper.FeedbackAppeal.updateByPrimaryKey", record);
	}

	@Override
	public void insertBatch(List<FeedbackAppeal> resultList)
			throws BaseException {
		this.insert("Mapper.FeedbackAppeal.insertBatch", resultList);

	}

	@Override
	public List<FeedbackAppeal> findFeedbackAppealList(Map<String, Object> inMap)
			throws BaseException {
		return this.findByParam("Mapper.FeedbackAppeal.findFeedbackAppealList", inMap);
		
	}

}
