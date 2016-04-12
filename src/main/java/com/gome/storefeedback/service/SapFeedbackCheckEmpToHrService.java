package com.gome.storefeedback.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;
import com.gome.storefeedback.exception.BaseException;

public interface SapFeedbackCheckEmpToHrService {
	
	public List<CheckSapFeedbackEmpCount> getEmpCheck(Date currentDateTime) throws BaseException;
	public void pushToHr(Date currentDateTime);
	public int insertToHrScan(List<CheckSapFeedbackEmpCount> checkSapFeedbackEmpCount) throws BaseException;
	public List<CheckSapFeedbackEmpCount> getEmpByHr() throws BaseException;
	public void deleteFlag() throws BaseException;
	public int updateByEmpNumber(String empNumber) throws BaseException;
	public CheckSapFeedbackEmpCount getEmpByEmpNumber(Map<String,Object> inMap) throws BaseException;
	public void deleteByEmpNumber(ArrayList<String> empNumbers) throws BaseException;
	
	
}
