package com.gome.storefeedback.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackCheckEmpDao;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackCheckEmpService;

@Component("sapFeedbackCheckEmpService")
public class SapFeedbackCheckEmpServiceImpl implements
		SapFeedbackCheckEmpService {

	@Resource
	private SapFeedbackCheckEmpDao sapFeedbackCheckEmpDao;
	
	@Override
	public int insert(Map<String, Object> pmap) throws BaseException {
		return this.sapFeedbackCheckEmpDao.insert(pmap);
	}

	@Override
	public List<Map<String, Object>> getAllSapCheck() throws BaseException {
		return this.sapFeedbackCheckEmpDao.getAllSapCheck();
	}

	@Override
	public int batchInsert(List<SapFeedbackCheckEmp> list) throws BaseException {
		return this.sapFeedbackCheckEmpDao.batchInsert(list);
	}

	@Override
	public int clearCheckEmp() throws BaseException {
		return this.sapFeedbackCheckEmpDao.clear();
	}
}
