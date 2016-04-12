package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;

public interface SapFeedbackCheckEmpDao {

	public int insert(Map<String , Object> pmap) throws BaseException;
	public List<Map<String, Object>> getAllSapCheck() throws BaseException;
	public int batchInsert(List<SapFeedbackCheckEmp> list) throws BaseException;
	public int clear() throws BaseException;
}
