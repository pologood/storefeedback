package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gome.storefeedback.dao.CheckSapFeedbackDao;
import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.CheckSapFeedbackService;
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
@Service("checkSapFeedbackService")
public class CheckSapFeedbackServiceImpl implements CheckSapFeedbackService {
	@Resource(name="checkSapFeedbackDao")
    private CheckSapFeedbackDao checkSapFeedbackDao;


    public CheckSapFeedbackDao getChecksapFeedbackDao() {
		return checkSapFeedbackDao;
	}

	@Autowired
    public void setChecksapFeedbackDao(@Qualifier("checkSapFeedbackDao") CheckSapFeedbackDao checkSapFeedbackDao) {
        this.checkSapFeedbackDao = checkSapFeedbackDao;
    }

	@Resource(name="checkSapFeedbackDao2")
	private CheckSapFeedbackDao checkSapFeedbackDao2;

	    
    @Override
    public void batchSapFeedback(List<SapFeedback> sapFeedbacks) throws BaseException {
        this.checkSapFeedbackDao.batchSapFeedback(sapFeedbacks);
    }

    @Override
    public void insertSapFeedback(SapFeedback sapFeedback) throws BaseException {
        this.checkSapFeedbackDao.insertSapFeedback(sapFeedback);
    }

    @Override
    public void updateSapFeedback(SapFeedback sapFeedback) throws BaseException {
        this.checkSapFeedbackDao.updateSapFeedback(sapFeedback);
    }

    @Override
    public void deleteSapFeedback(Map<String, Object> inMap) throws BaseException {
        this.checkSapFeedbackDao.deleteSapFeedback(inMap);
    }

    @Override
    public SapFeedback findSapFeedbackById(Map<String, Object> inMap) throws BaseException {
        return this.checkSapFeedbackDao.findSapFeedbackById(inMap);
    }

    @Override
    public List findSapFeedback(Map<String, Object> inMap) throws BaseException {
        return this.checkSapFeedbackDao.findSapFeedback(inMap);
    }

    @Override
    public Page findSapFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.checkSapFeedbackDao.findSapFeedbackByPage(inMap, param);
    }

    @Override
    public Page findByParams(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.checkSapFeedbackDao.findByParams(inMap, param);
    }

    @Override
    public List findPushJiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        return this.checkSapFeedbackDao.findPushJiCaiByCategory(inMap);
    }

    @Override
    public List findPushDiCaiByCategory(Map<String, Object> inMap) throws BaseException {
        return this.checkSapFeedbackDao.findPushDiCaiByCategory(inMap);
    }

	@Override
	public List findBrandList(Map<String, Object> inMap) throws BaseException {
		 return this.checkSapFeedbackDao.findBrandList(inMap);
	}

	@Transactional
	@Override
	public void updateBatchSapFeedbackHisByAppeal(List<FeedbackAppeal> resultList) throws BaseException {
		this.checkSapFeedbackDao.updateBatchSapFeedbackHisByAppeal(resultList);
	}

	@Override
	public void updateBatchSapFeedbackHisByToOa(List<FeedbackToOa> resultList)
			throws BaseException {
		this.checkSapFeedbackDao.updateBatchSapFeedbackHisByToOa(resultList);
	}

	@Override
	public void updateSapFeedbackHisByAppeal(FeedbackAppeal feedbackAppeal)
			throws BaseException {
		this.checkSapFeedbackDao.updateSapFeedbackHisByAppeal(feedbackAppeal);
	}

	@Override
	public void batchInsert(ArrayList<SapFeedbackCheckEmp> sapFeedbackCheckList)
			throws BaseException {
		this.checkSapFeedbackDao.batchInsert(sapFeedbackCheckList);
	}

	@Override
	public List<Map<String, Object>> getCheckByEmp(Map<String, Object> inMap) throws BaseException {
		return checkSapFeedbackDao2.getCheckByEmp(inMap);
	}
}
