package com.gome.storefeedback.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;
import com.gome.storefeedback.exception.BaseException;

public interface SapFeedbackCheckEmpToHrDao {

	public List<CheckSapFeedbackEmpCount> getEmpCheck(HashMap<String, Object> inMap) throws BaseException;
	public int insertToHrScan(List<CheckSapFeedbackEmpCount> checkSapFeedbackEmpCount) throws BaseException;
	public void deleteFlag() throws BaseException;
	public List<CheckSapFeedbackEmpCount> getEmpByHr() throws BaseException;
	public int updateByEmpNumber(String empNumber) throws BaseException;
	public CheckSapFeedbackEmpCount getEmpByEmpNumber(Map<String,Object> inMap) throws BaseException;
	public void deleteByEmpNumber(ArrayList<String> empNumbers) throws BaseException;
}
