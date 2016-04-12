package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;

public interface SapFeedbackCheckEmpToDisplayDao {

	public List<SapFeedbackCheckEmp> getAllDisplayByEmp(Map<String, Object> map) throws BaseException;
}
