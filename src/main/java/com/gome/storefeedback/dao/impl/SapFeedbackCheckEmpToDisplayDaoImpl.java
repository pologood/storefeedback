package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackCheckEmpToDisplayDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;
@Component("sapFeedbackCheckEmpToDisplayDao")
public class SapFeedbackCheckEmpToDisplayDaoImpl extends BaseDaoImpl<SapFeedbackCheckEmp, SapFeedbackCheckEmp> implements
		SapFeedbackCheckEmpToDisplayDao  {

	@Override
	public List<SapFeedbackCheckEmp> getAllDisplayByEmp(Map<String, Object> inMap) throws BaseException {
		try {
			return this.findByParam("Mapper.SapFeedbackCheckEmpToDisplayMapper.getAllDisplayByEmp", inMap);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToDisplayMapper.getAllDisplayByEmp error.", e);
		}
	}

}
