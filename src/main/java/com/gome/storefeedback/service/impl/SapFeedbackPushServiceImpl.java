package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.SapFeedbackPushDao;
import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackPushService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackPushService实现
 * 
 * @author Gong.ZhiBin
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("sapFeedbackPushService")
public class SapFeedbackPushServiceImpl implements SapFeedbackPushService {

    private SapFeedbackPushDao sapFeedbackPushDao;

    public SapFeedbackPushDao getSapFeedbackPushDao() {
        return sapFeedbackPushDao;
    }

    @Autowired
    public void setSapFeedbackPushDao(@Qualifier("sapFeedbackPushDao") SapFeedbackPushDao sapFeedbackPushDao) {
        this.sapFeedbackPushDao = sapFeedbackPushDao;
    }

    @Override
    public void batchSapFeedbackPush(List<SapFeedbackPush> sapFeedbackPushs) throws BaseException {
        this.sapFeedbackPushDao.batchSapFeedbackPush(sapFeedbackPushs);
    }

    @Override
    public void insertSapFeedbackPush(SapFeedbackPush sapFeedbackPush) throws BaseException {
        this.sapFeedbackPushDao.insertSapFeedbackPush(sapFeedbackPush);
    }

    @Override
    public void updateSapFeedbackPush(SapFeedbackPush sapFeedbackPush) throws BaseException {
        this.sapFeedbackPushDao.updateSapFeedbackPush(sapFeedbackPush);
    }

    @Override
    public void deleteSapFeedbackPush(Map<String, Object> inMap) throws BaseException {
        this.sapFeedbackPushDao.deleteSapFeedbackPush(inMap);
    }

    @Override
    public SapFeedbackPush findSapFeedbackPushById(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackPushDao.findSapFeedbackPushById(inMap);
    }

    @Override
    public List findSapFeedbackPush(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackPushDao.findSapFeedbackPush(inMap);
    }

    @Override
    public Page findSapFeedbackPushByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.sapFeedbackPushDao.findSapFeedbackPushByPage(inMap, param);
    }
}
