package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackPushCategoryConfigDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午3:26:57
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("feedbackPushCategoryConfigDao")
public  class FeedbackPushCategoryConfigDaoImpl extends
        BaseDaoImpl<FeedbackPushCategoryConfig, FeedbackPushCategoryConfig> implements FeedbackPushCategoryConfigDao {

    @Override
    public int delete(Map<String, Object> inMap) throws BaseException {
        try {
            return this.delete("Mapper.FeedbackPushCategoryConfig.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete error.", e);
        }
    }

    @Override
    public int insert(FeedbackPushCategoryConfig record) throws BaseException {
        try {
            return this.insert("Mapper.FeedbackPushCategoryConfig.insert", record);
        } catch (Exception e) {
            throw new BaseException("insert error.", e);
        }
    }

    @Override
    public List find(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.FeedbackPushCategoryConfig.list", inMap);
        } catch (Exception e) {
            throw new BaseException("find error.", e);
        }
    }

    @Override
    public FeedbackPushCategoryConfig findById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.FeedbackPushCategoryConfig.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findById error.", e);
        }
    }

    @Override
    public int update(FeedbackPushCategoryConfig record) throws BaseException {
        try {
            return this.update("Mapper.FeedbackPushCategoryConfig.update", record);
        } catch (Exception e) {
            throw new BaseException("update error.", e);
        }
    }

    @Override
    public Page findByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.FeedbackPushCategoryConfig.findByPage", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findByPage error.", e);
        }
    }

	@Override
	public List<FeedbackPushCategoryConfig> findList(Map<String, Object> inMap) throws BaseException {
		 return this.findByParam("Mapper.FeedbackPushCategoryConfig.findList", inMap);
	}

}
