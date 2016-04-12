package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.SapFeedbackDao;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.entity.SapFeedbackDicai;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackService实现
 * 
 * @author
 * @date 2015年07月23日 09时21分29秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("sapFeedbackService")
public class SapFeedbackServiceImpl implements SapFeedbackService {

    private SapFeedbackDao sapFeedbackDao;

    public SapFeedbackDao getSapFeedbackDao() {

        return sapFeedbackDao;
    }

    @Autowired
    public void setSapFeedbackDao(@Qualifier("sapFeedbackDao") SapFeedbackDao sapFeedbackDao) {
        this.sapFeedbackDao = sapFeedbackDao;
    }

    @Override
    public void batchSapFeedback(List<SapFeedback> sapFeedbacks) throws BaseException {
        this.sapFeedbackDao.batchSapFeedback(sapFeedbacks);
    }

    @Override
    public void insertSapFeedback(SapFeedback sapFeedback) throws BaseException {
        this.sapFeedbackDao.insertSapFeedback(sapFeedback);
    }

    @Override
    public void updateSapFeedback(SapFeedback sapFeedback) throws BaseException {
        this.sapFeedbackDao.updateSapFeedback(sapFeedback);
    }

    @Override
    public void deleteSapFeedback(Map<String, Object> inMap) throws BaseException {
        this.sapFeedbackDao.deleteSapFeedback(inMap);
    }

    @Override
    public SapFeedback findSapFeedbackById(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackDao.findSapFeedbackById(inMap);
    }

    @Override
    public List findSapFeedback(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackDao.findSapFeedback(inMap);
    }

    @Override
    public Page findSapFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.sapFeedbackDao.findSapFeedbackByPage(inMap, param);
    }

    @Override
    public Page findByParams(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.sapFeedbackDao.findByParams(inMap, param);
    }

    @Override
    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackDao.findPushJiCaiByCategory(inMap);
    }

    @Override
    public List findPushDiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        return this.sapFeedbackDao.findPushDiCaiByCategory(inMap);
    }

    /**
     * 把BW推送的缺断货数据关联empNumber之后插入到zmm_ds62_toHr表，每月推送数据给HR的数据来源
     * @param currentDateTime
     * @throws BaseException
     */
	@Override
	public int insertIntoHr(List<SapFeedbackCheckEmp> list) throws BaseException {
		return this.sapFeedbackDao.insertIntoHr(list);
	}
}
