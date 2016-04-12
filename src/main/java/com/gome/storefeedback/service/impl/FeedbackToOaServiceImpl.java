package com.gome.storefeedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.FeedbackToOaDao;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackToOaService;
@Service("feedbackToOaService")
public class FeedbackToOaServiceImpl implements FeedbackToOaService {
	  private FeedbackToOaDao feedbackToOakDao;


	    public FeedbackToOaDao getFeedbackToOaDao() {
			return feedbackToOakDao;
		}

		@Autowired
	    public void setFeedbackToOaDao(@Qualifier("feedbackToOaDao") FeedbackToOaDao feedbackToOakDao) {
	        this.feedbackToOakDao = feedbackToOakDao;
	    }


	@Override
	public int deleteByPrimaryKey(String id) {
		return this.feedbackToOakDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FeedbackToOa record) {
		return this.feedbackToOakDao.insert(record);
	}

	@Override
	public int insertSelective(FeedbackToOa record) {
		return this.feedbackToOakDao.insertSelective(record);
	}

	@Override
	public FeedbackToOa selectByPrimaryKey(String id) {
		return this.feedbackToOakDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FeedbackToOa record) {
		return this.feedbackToOakDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FeedbackToOa record) {
		return this.feedbackToOakDao.updateByPrimaryKey(record);
	}

	@Override
	public void insertBatch(List<FeedbackToOa> resultList) throws BaseException {
		 this.feedbackToOakDao.insertBatch(resultList);
		
	}

}
