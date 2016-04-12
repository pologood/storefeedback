package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.SapFeedbackCheckPushDao;
import com.gome.storefeedback.entity.SapFeedbackCheckPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackCheckPushService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackCheckPushService实现
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("aapFeedbackCheckPushService")
public class SapFeedbackCheckPushServiceImpl implements SapFeedbackCheckPushService {

    private SapFeedbackCheckPushDao sapFeedbackCheckPushDao;

    public SapFeedbackCheckPushDao getSapFeedbackCheckPushDao() {
        return sapFeedbackCheckPushDao;
    }

    @Autowired
    public void setSapFeedbackCheckPushDao(@Qualifier("sapFeedbackCheckPushDao") SapFeedbackCheckPushDao sapFeedbackCheckPushDao) {
        this.sapFeedbackCheckPushDao = sapFeedbackCheckPushDao;
    }

    @Override
    public void batchSapFeedbackCheckPush(List<SapFeedbackCheckPush> sapFeedbackCheckPushs) throws BaseException {
        this.sapFeedbackCheckPushDao.batchSapFeedbackCheckPush(sapFeedbackCheckPushs);
    }

    @Override
    public void insertSapFeedbackCheckPush(SapFeedbackCheckPush sapFeedbackCheckPushs) throws BaseException {
        this.sapFeedbackCheckPushDao.insertSapFeedbackCheckPush(sapFeedbackCheckPushs);
    }

    @Override
    public void updateSapFeedbackCheckPush(SapFeedbackCheckPush sapFeedbackCheckPushs) throws BaseException {
        this.sapFeedbackCheckPushDao.updateSapFeedbackCheckPush(sapFeedbackCheckPushs);
    }

    @Override
    public void deleteSapFeedbackCheckPush(Map<String, Object> inMap) throws BaseException {
        this.sapFeedbackCheckPushDao.deleteSapFeedbackCheckPush(inMap);
    }

    @Override
    public SapFeedbackCheckPush findSapFeedbackCheckPushById(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackCheckPushDao.findSapFeedbackCheckPushById(inMap);
    }

    @Override
    public List findSapFeedbackCheckPush(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackCheckPushDao.findSapFeedbackCheckPush(inMap);
    }

    @Override
    public Page findSapFeedbackCheckPushByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.sapFeedbackCheckPushDao.findSapFeedbackCheckPushByPage(inMap, param);
    }
}
