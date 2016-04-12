package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.FeedbackRecieverDao;
import com.gome.storefeedback.entity.FeedbackReciever;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackRecieverService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * FeedbackRecieverService实现
 * 
 * @author 
 * @date 2015年03月06日 15时31分19秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("feedbackRecieverService")
public class FeedbackRecieverServiceImpl implements FeedbackRecieverService {

	private FeedbackRecieverDao feedbackRecieverDao;
	
	
	public FeedbackRecieverDao getFeedbackRecieverDao() {
		return feedbackRecieverDao;
	}

	@Autowired
	public void setFeedbackRecieverDao(@Qualifier("feedbackRecieverDao")FeedbackRecieverDao feedbackRecieverDao) {
		this.feedbackRecieverDao = feedbackRecieverDao;
	}

	@Override
	public void insertFeedbackReciever(FeedbackReciever feedbackReciever) throws BaseException {
		this.feedbackRecieverDao.insertFeedbackReciever(feedbackReciever);
	}

	@Override
	public void insertBatchFeedbackReciever(List<FeedbackReciever> feedbackRecievers) throws BaseException {
		this.feedbackRecieverDao.insertBatchFeedbackReciever(feedbackRecievers);
	}

	@Override
	public void deleteFeedbackReciever(Map<String, Object> inMap) throws BaseException {
		this.feedbackRecieverDao.deleteFeedbackReciever(inMap);
	}

	@Override
	public List findFeedbackReciever(Map<String, Object> inMap) throws BaseException {
		return this.feedbackRecieverDao.findFeedbackReciever(inMap);
	}

	@Override
	public Page findFeedbackRecieverByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.feedbackRecieverDao.findFeedbackRecieverByPage(inMap, param);
	}

}
