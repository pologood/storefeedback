package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.FeedbackPushCategoryPositionConfigDao;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午4:48:21
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("feedbackPushCategoryPositionConfigService")
public class FeedbackPushCategoryPositionConfigServiceImpl implements FeedbackPushCategoryPositionConfigService {

    @Resource
    private FeedbackPushCategoryPositionConfigDao feedbackPushCategoryPositionConfigDao;

    @Override
    public int delete(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.delete(inMap);
    }

    @Override
    public int insert(FeedbackPushCategoryPositionConfig record) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.insert(record);
    }

    @Override
    public List find(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.find(inMap);
    }

    @Override
    public FeedbackPushCategoryPositionConfig findById(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.findById(inMap);
    }

    @Override
    public int update(FeedbackPushCategoryPositionConfig record) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.update(record);
    }

    @Override
    public Page findByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.findByPage(inMap, param);
    }

    @Override
    public List<FeedbackPushCategoryPositionConfig> findAll(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.find(inMap);
    }

    @Override
    public Page findOrgPageByCategory(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.findOrgPageByCategory(inMap, param);
    }

    @Override
    public List<FeedbackPushCategoryPositionConfig> findPushPosition(Map<String, Object> positionMap)
            throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.findPush(positionMap);
    }

    @Override
    public List findRoleId(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.findRoleId(inMap);
    }
    
    @Override
    public List<FeedbackPushCategoryPositionConfig> checkCategoryPosition(Map<String, Object> inMap) throws BaseException {
        return this.feedbackPushCategoryPositionConfigDao.checkCategoryPosition(inMap);
    }

    /**
     * 根据 部门、品类编码 获取 FeedbackPushCategoryPositionConfig
     * @param inMap
     * @return
     * @throws BaseException
     */
	@Override
	public List<FeedbackPushCategoryPositionConfig> findPushCategoryPosition(Map<String, Object> inMap)
			throws BaseException {
		return this.feedbackPushCategoryPositionConfigDao.findPushCategoryPosition(inMap);
	}
}
