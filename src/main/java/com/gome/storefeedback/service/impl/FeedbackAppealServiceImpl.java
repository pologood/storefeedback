package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.FeedbackAppealDao;
import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackAppealService;
@Service("feedbackAppealService")
public class FeedbackAppealServiceImpl implements FeedbackAppealService {
	
	
	private FeedbackAppealDao feedbackAppealDao;


    public FeedbackAppealDao getFeedbackAppealDao() {
		return feedbackAppealDao;
	}

	@Autowired
    public void setFeedbackAppealDao(@Qualifier("feedbackAppealDao")FeedbackAppealDao feedbackAppealDao) {
        this.feedbackAppealDao = feedbackAppealDao;
    }

	
	@Override
	public int deleteByPrimaryKey(String id) {
		return this.feedbackAppealDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FeedbackAppeal record) {
		return this.feedbackAppealDao.insert(record);
	}

	@Override
	public int insertSelective(FeedbackAppeal record) {
		return this.feedbackAppealDao.insertSelective(record);
	}

	@Override
	public FeedbackAppeal selectByPrimaryKey(String id) {
		return this.feedbackAppealDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FeedbackAppeal record) {
		return this.feedbackAppealDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FeedbackAppeal record) throws BaseException {
		return this.feedbackAppealDao.updateByPrimaryKey(record);
	}

	@Override
	public void insertBatch(List<FeedbackAppeal> resultList) throws BaseException {
		 this.feedbackAppealDao.insertBatch(resultList);
		
	}

	@Override
	public List<FeedbackAppeal> findFeedbackAppealList(Map<String, Object>  record) throws BaseException {
		return this.feedbackAppealDao.findFeedbackAppealList(record);
	}
}
