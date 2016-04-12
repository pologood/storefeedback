package com.gome.storefeedback.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.CheckSapFeedbackDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackToOa;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
@Component("checkSapFeedbackDao2")
public class CheckSapFeedBackDaoImpl2 extends BaseDaoImpl<Map<String, Object>,Map<String, Object>> implements CheckSapFeedbackDao {

	@Override
	public void batchSapFeedback(List<SapFeedback> sapFeedbacks)
			throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertSapFeedback(SapFeedback sapFeedback) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSapFeedback(SapFeedback sapFeedback) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSapFeedback(Map<String, Object> inMap)
			throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public SapFeedback findSapFeedbackById(Map<String, Object> inMap)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findSapFeedback(Map<String, Object> inMap) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findSapFeedbackByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findByParams(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findPushJiCaiByCategory(Map<String, Object> inMap)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findPushDiCaiByCategory(Map<String, Object> inMap)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findBrandList(Map<String, Object> inMap) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBatchSapFeedbackHisByAppeal(
			List<FeedbackAppeal> resultList) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBatchSapFeedbackHisByToOa(List<FeedbackToOa> resultList)
			throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSapFeedbackHisByAppeal(FeedbackAppeal feedbackAppeal)
			throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchInsert(ArrayList<SapFeedbackCheckEmp> sapFeedbackCheckList)
			throws BaseException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<Map<String, Object>> getCheckByEmp(Map<String, Object> inMap) throws BaseException {
		try {
            return this.findByParam("Mapper.CheckSapFeedback.getCheckByEmp", inMap);
        } catch (Exception e) {
            throw new BaseException("getCheckByEmp error.", e);
        }
	}

}
