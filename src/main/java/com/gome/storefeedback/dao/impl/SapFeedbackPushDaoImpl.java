package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackPushDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackPushDao实现
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("sapFeedbackPushDao")
public class SapFeedbackPushDaoImpl extends BaseDaoImpl<SapFeedbackPush, SapFeedbackPush> implements SapFeedbackPushDao {

    @Override
    public void batchSapFeedbackPush(List<SapFeedbackPush> sapFeedbackPushs) throws BaseException {
        try {
            this.insert("Mapper.SapFeedbackPush.batch", sapFeedbackPushs);
        } catch (Exception e) {
            throw new BaseException("batch sapFeedbackPush error.", e);
        }
    }

    @Override
    public void insertSapFeedbackPush(SapFeedbackPush sapFeedbackPush) throws BaseException {
        try {
            this.insert("Mapper.SapFeedbackPush.insert", sapFeedbackPush);
        } catch (Exception e) {
            throw new BaseException("insert sapFeedbackPush error.", e);
        }
    }

    @Override
    public void updateSapFeedbackPush(SapFeedbackPush sapFeedbackPush) throws BaseException {
        try {
            this.update("Mapper.SapFeedbackPush.updateByPK", sapFeedbackPush);
        } catch (Exception e) {
            throw new BaseException("update sapFeedbackPush error.", e);
        }
    }

    @Override
    public void deleteSapFeedbackPush(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.SapFeedbackPush.deleteByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("delete sapFeedbackPush error.", e);
        }
    }

    @Override
    public SapFeedbackPush findSapFeedbackPushById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.SapFeedbackPush.selectByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackPushById error.", e);
        }
    }

    @Override
    public List findSapFeedbackPush(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.SapFeedbackPush.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackPush error.", e);
        }
    }

    @Override
    public Page findSapFeedbackPushByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.SapFeedbackPush.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackPushByPage error.", e);
        }
    }

}
