package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackCheckPushDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapFeedbackCheckPush;
import com.gome.storefeedback.entity.SapFeedbackCheckPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackCheckPushDao实现
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component("sapFeedbackCheckPushDao")
public class SapFeedbackCheckPushDaoImpl extends BaseDaoImpl<SapFeedbackCheckPush, SapFeedbackCheckPush> implements SapFeedbackCheckPushDao {

    @Override
    public void batchSapFeedbackCheckPush(List<SapFeedbackCheckPush> sapFeedbackCheckPush) throws BaseException {
        try {
            this.insert("Mapper.SapFeedbackCheckPush.batch", sapFeedbackCheckPush);
        } catch (Exception e) {
            throw new BaseException("batch SapFeedbackCheckPush error.", e);
        }
    }

    @Override
    public void insertSapFeedbackCheckPush(SapFeedbackCheckPush sapFeedbackCheckPush) throws BaseException {
        try {
            this.insert("Mapper.SapFeedbackCheckPush.insert", sapFeedbackCheckPush);
        } catch (Exception e) {
            throw new BaseException("insert SapFeedbackCheckPush error.", e);
        }
    }

    @Override
    public void updateSapFeedbackCheckPush(SapFeedbackCheckPush sapFeedbackCheckPush) throws BaseException {
        try {
            this.update("Mapper.SapFeedbackCheckPush.updateByPK", sapFeedbackCheckPush);
        } catch (Exception e) {
            throw new BaseException("update SapFeedbackCheckPush error.", e);
        }
    }

    @Override
    public void deleteSapFeedbackCheckPush(Map<String, Object> inMap) throws BaseException {
        try {
            this.delete("Mapper.SapFeedbackCheckPush.deleteByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("delete SapFeedbackCheckPush error.", e);
        }
    }

    @Override
    public SapFeedbackCheckPush findSapFeedbackCheckPushById(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByID("Mapper.SapFeedbackCheckPush.selectByPK", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackCheckPushById error.", e);
        }
    }

    @Override
    public List findSapFeedbackCheckPush(Map<String, Object> inMap) throws BaseException {
        try {
            return this.findByParam("Mapper.SapFeedbackCheckPush.list", inMap);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackCheckPush error.", e);
        }
    }

    @Override
    public Page findSapFeedbackCheckPushByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        try {
            return this.findByPage("Mapper.SapFeedbackCheckPush.list", inMap, param);
        } catch (Exception e) {
            throw new BaseException("findSapFeedbackCheckPushByPage error.", e);
        }
    }

}
