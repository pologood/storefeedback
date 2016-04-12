package com.gome.storefeedback.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.SapFeedbackCheckEmp;

public interface SapFeedbackCheckEmpToDisplayService {

	public void pushToDisplay(Date currentDateTime);

	public List<SapFeedbackCheckEmp> getAllDisplayByEmp(Map<String,Object> map);
}
