package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.ResultNoReasonDao;
import com.gome.storefeedback.entity.ResultNoReason;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.ResultNoReasonService;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 
 * ResultNoReasonService实现
 * 
 * @author 
 * @date 2015年07月22日 16时05分35秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("resultNoReasonService")
public class ResultNoReasonServiceImpl implements ResultNoReasonService {

	private ResultNoReasonDao resultNoReasonDao;
	
	
	public ResultNoReasonDao getResultNoReasonDao() {
		return resultNoReasonDao;
	}

	@Autowired
	public void setResultNoReasonDao(@Qualifier("resultNoReasonDao")ResultNoReasonDao resultNoReasonDao) {
		this.resultNoReasonDao = resultNoReasonDao;
	}

	@Override
	public void batchResultNoReason(List<ResultNoReason> resultNoReasons) throws BaseException {
		this.resultNoReasonDao.batchResultNoReason(resultNoReasons);
	}
	
	@Override
	public void insertResultNoReason(ResultNoReason resultNoReason) throws BaseException {
		this.resultNoReasonDao.insertResultNoReason(resultNoReason);
	}

	@Override
	public void updateResultNoReason(ResultNoReason resultNoReason) throws BaseException {
		this.resultNoReasonDao.updateResultNoReason(resultNoReason);
	}

	@Override
	public void deleteResultNoReason(Map<String, Object> inMap) throws BaseException {
		this.resultNoReasonDao.deleteResultNoReason(inMap);
	}

	@Override
	public ResultNoReason findResultNoReasonById(Map<String, Object> inMap)
			throws BaseException {
		return this.resultNoReasonDao.findResultNoReasonById(inMap);
	}

	@Override
	public List findResultNoReason(Map<String, Object> inMap) throws BaseException {
		return this.resultNoReasonDao.findResultNoReason(inMap);
	}

	@Override
	public Page findResultNoReasonByPage(Map<String, Object> inMap,
			PaginationParameters param) throws BaseException {
		return this.resultNoReasonDao.findResultNoReasonByPage(inMap, param);
	}

    @Override
    public List findCombo(Map<String, Object> inMap) throws BaseException{
        return this.resultNoReasonDao.findCombo(inMap);
    }
}
