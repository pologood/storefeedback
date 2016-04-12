package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.FeedbackAppealDao;
import com.gome.storefeedback.dao.FeedbackCheckConfigDao;
import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackCheckConfigService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
@Service("feedbackCheckConfigService")
public class FeedbackCheckConfigServiceImpl implements FeedbackCheckConfigService {
	
	
	
	private FeedbackCheckConfigDao feedbackCheckConfigDao;


    

	public FeedbackCheckConfigDao getFeedbackCheckConfigDao() {
		return feedbackCheckConfigDao;
	}
	@Autowired
	public void setFeedbackCheckConfigDao(@Qualifier("feedbackCheckConfigDao")FeedbackCheckConfigDao feedbackCheckConfigDao) {
		this.feedbackCheckConfigDao = feedbackCheckConfigDao;
	}


	@Override
	public List find(Map<String, Object> inMap) throws BaseException {
		return this.feedbackCheckConfigDao.find(inMap);
	}


	@Override
	public Page findByPage(Map<String, Object> inMap, PaginationParameters param)
			throws BaseException {
		return this.feedbackCheckConfigDao.findByPage(inMap,param);
	}
	@Override
	public int insert(FeedbackCheckConfig record) throws BaseException {
		return this.feedbackCheckConfigDao.insert(record);
	}
	@Override
	public int update(FeedbackCheckConfig record) throws BaseException {
		return this.feedbackCheckConfigDao.update(record);
	}
	@Override
	public FeedbackCheckConfig findById(Map<String, Object> inMap)
			throws BaseException {
		return this.feedbackCheckConfigDao.findById(inMap);
	}
	@Override
	public int delete(Map<String, Object> inMap) throws BaseException {
		return this.feedbackCheckConfigDao.delete(inMap);
	}
	@Override
	public List<FeedbackCheckConfig> findCheckEmpList(
			Map<String, Object> positionMap) throws BaseException {
		 return this.feedbackCheckConfigDao.findCheckEmpList(positionMap);
	}

}
