package com.gome.storefeedback.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;

public interface SapFeedbackCheckEmpService {
	
	public int insert(Map<String , Object> pmap) throws BaseException;
	
	public int batchInsert(List<SapFeedbackCheckEmp> list) throws BaseException;
	
	public List<Map<String, Object>> getAllSapCheck() throws BaseException;

	public int clearCheckEmp() throws BaseException;

}
