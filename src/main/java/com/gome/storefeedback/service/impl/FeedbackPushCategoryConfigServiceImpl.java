package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.FeedbackPushCategoryConfigDao;
import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackPushCategoryConfigService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午4:41:51
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("feedbackPushCategoryConfigService")
public class FeedbackPushCategoryConfigServiceImpl implements FeedbackPushCategoryConfigService {

    private FeedbackPushCategoryConfigDao feedbackPushCategoryConfigDao;
    
    public FeedbackPushCategoryConfigDao getFeedbackPushCategoryConfigDao() {
		return feedbackPushCategoryConfigDao;
	}
    @Autowired
	public void setFeedbackPushCategoryConfigDao(@Qualifier("feedbackPushCategoryConfigDao")FeedbackPushCategoryConfigDao feedbackPushCategoryConfigDao) {
		this.feedbackPushCategoryConfigDao = feedbackPushCategoryConfigDao;
	}

	@Override
    public int delete(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryConfigDao.delete(inMap);
    }

    @Override
    public int insert(FeedbackPushCategoryConfig record) throws BaseException {
        return this.feedbackPushCategoryConfigDao.insert(record);
    }

    @Override
    public List find(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryConfigDao.find(inMap);
    }

    @Override
    public FeedbackPushCategoryConfig findById(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryConfigDao.findById(inMap);
    }

    @Override
    public int update(FeedbackPushCategoryConfig record) throws BaseException {
        return this.feedbackPushCategoryConfigDao.update(record);
    }

    @Override
    public Page findByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.feedbackPushCategoryConfigDao.findByPage(inMap, param);
    }

	@Override
	public List<FeedbackPushCategoryConfig> findList(Map<String, Object> inMap) throws BaseException {
		 return this.feedbackPushCategoryConfigDao.findList(inMap);
	}

}
