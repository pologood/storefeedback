package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.MQErrorInfoDao;
import com.gome.storefeedback.entity.MQErrorInfo;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.MQErrorInfoService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * MQErrorInfoService实现
 * 
 * @author
 * @date 2015年04月01日 15时48分14秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("mQErrorInfoService")
public class MQErrorInfoServiceImpl implements MQErrorInfoService {

    private MQErrorInfoDao mQErrorInfoDao;

    public MQErrorInfoDao getMQErrorInfoDao() {
        return mQErrorInfoDao;
    }

    @Autowired
    public void setMQErrorInfoDao(@Qualifier("mQErrorInfoDao") MQErrorInfoDao mQErrorInfoDao) {
        this.mQErrorInfoDao = mQErrorInfoDao;
    }

    @Override
    public void insertMQErrorInfo(MQErrorInfo mQErrorInfo) throws BaseException {
        this.mQErrorInfoDao.insertMQErrorInfo(mQErrorInfo);
    }

    @Override
    public void deleteMQErrorInfo(Map<String, Object> inMap) throws BaseException {
        this.mQErrorInfoDao.deleteMQErrorInfo(inMap);
    }

    @Override
    public MQErrorInfo findMQErrorInfoById(Map<String, Object> inMap) throws BaseException {
        return this.mQErrorInfoDao.findMQErrorInfoById(inMap);
    }

    @Override
    public List findMQErrorInfo(Map<String, Object> inMap) throws BaseException {
        return this.mQErrorInfoDao.findMQErrorInfo(inMap);
    }

    @Override
    public Page findMQErrorInfoByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.mQErrorInfoDao.findMQErrorInfoByPage(inMap, param);
    }

}
