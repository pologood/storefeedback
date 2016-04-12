package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackPushCategoryPositionConfigDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午3:38:55
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("feedbackPushCategoryPositionConfigDao")
public class FeedbackPushCategoryPositionConfigDaoImpl extends
        BaseDaoImpl<FeedbackPushCategoryPositionConfig, FeedbackPushCategoryPositionConfig> implements
        FeedbackPushCategoryPositionConfigDao {

    @Override
    public int delete(Map<String, Object> inMap) throws BaseException {
        try {
            return this.delete("Mapper.FeedbackPushCategoryPositionConfig.deleteByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("delete FeedbackPushCategoryPositionConfig error.", e);
        }
    }

    @Override
    public int insert(FeedbackPushCategoryPositionConfig record) throws BaseException {
        try {
            return this.insert("Mapper.FeedbackPushCategoryPositionConfig.insert", record);
        } catch (Exception e) {
            throw new BaseException("insert FeedbackPushCategoryPositionConfig error.", e);
        }
    }

    @Override
    public List find(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.FeedbackPushCategoryPositionConfig.list", inMap);
        } catch (Exception e) {
            throw new BaseException("find FeedbackPushCategoryPositionConfig error.", e);
        }
    }

    @Override
    public FeedbackPushCategoryPositionConfig findById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.FeedbackPushCategoryPositionConfig.selectByPrimaryKey", inMap);
        } catch (Exception e) {
            throw new BaseException("findById FeedbackPushCategoryPositionConfig error.", e);
        }
    }

    @Override
    public int update(FeedbackPushCategoryPositionConfig record) throws BaseException {
        try {
            return this.update("Mapper.FeedbackPushCategoryPositionConfig.update", record);
        } catch (Exception e) {
            throw new BaseException("update FeedbackPushCategoryPositionConfig error.", e);
        }
    }

    @Override
    public Page findByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.FeedbackPushCategoryPositionConfig.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findByPage FeedbackPushCategoryPositionConfig error.", e);
        }
    }

    @Override
    public Page findOrgPageByCategory(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.FeedbackPushCategoryPositionConfig.findOrgPageByCategory", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findOrgPageByCategory FeedbackPushCategoryPositionConfig error.", e);
        }
    }

    @Override
    public List<FeedbackPushCategoryPositionConfig> findPush(Map<String, Object> positionMap) throws BaseException {
        try {
            return this.findByParam("Mapper.FeedbackPushCategoryPositionConfig.pushlist", positionMap);
        } catch (Exception e) {
            throw new BaseException("findPush FeedbackPushCategoryPositionConfig error.", e);
        }
    }
    
    @Override
    public List findRoleId(Map<String, Object> positionMap) throws BaseException {
        try {
            return this.findByParam("Mapper.FeedbackPushCategoryPositionConfig.findRoleId", positionMap);
        } catch (Exception e) {
            throw new BaseException("findRoleId FeedbackPushCategoryPositionConfig error.", e);
        }
    }

	@Override
	public List<FeedbackPushCategoryPositionConfig> checkCategoryPosition(Map<String, Object> inMap)throws BaseException {
	       return this.findByParam("Mapper.FeedbackPushCategoryPositionConfig.checkCategoryPosition", inMap);
	}

	@Override
	public List<FeedbackPushCategoryPositionConfig> findPushCategoryPosition(Map<String, Object> inMap)
			throws BaseException {
		try {
            return this.findByParam("Mapper.FeedbackPushCategoryPositionConfig.findPushCategoryPosition", inMap);
        } catch (Exception e) {
            throw new BaseException("findPushCategoryPosition FeedbackPushCategoryPositionConfig error.", e);
        }
	}
    
}
