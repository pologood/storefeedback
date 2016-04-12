package com.gome.storefeedback.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackCheckEmpToHrDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;
import com.gome.storefeedback.exception.BaseException;

@Component("sapFeedbackCheckEmpToHrDao")
public class SapFeedbackCheckEmpToHrDaoImpl extends BaseDaoImpl<CheckSapFeedbackEmpCount, CheckSapFeedbackEmpCount> implements
SapFeedbackCheckEmpToHrDao {

	@Override
	public List<CheckSapFeedbackEmpCount> getEmpCheck(HashMap<String, Object> inMap) throws BaseException {
		try {
			return this.findByParam("Mapper.SapFeedbackCheckEmpToHrMapper.getEmpCheck", inMap);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToHrMapper.getEmpCheck error.", e);
		}
		
	}

	@Override
	public int insertToHrScan(List<CheckSapFeedbackEmpCount> list)
			throws BaseException {
		try {
			return this.insert("Mapper.SapFeedbackCheckEmpToHrMapper.batchInsert", list);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToHrMapper.batchInsert error.", e);
		}
	}

	@Override
	public void deleteFlag() throws BaseException {
		try {
			this.delete("Mapper.SapFeedbackCheckEmpToHrMapper.deleteFlag", null);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToHrMapper.deleteFlag error.", e);
		}
	}

	@Override
	public List<CheckSapFeedbackEmpCount> getEmpByHr() throws BaseException {
		try {
			return this.findByParam("Mapper.SapFeedbackCheckEmpToHrMapper.getEmpByHr", null);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToHrMapper.getEmpByHr error.", e);
		}
	}

	@Override
	public int updateByEmpNumber(String empNumber) throws BaseException {
		try {
			return this.update("Mapper.SapFeedbackCheckEmpToHrMapper.updateByEmpNumber", empNumber);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToHrMapper.updateByEmpNumber error.", e);
		}
	}

	@Override
	public CheckSapFeedbackEmpCount getEmpByEmpNumber(Map<String,Object> inMap)
			throws BaseException {
		try {
			return this.findByID("Mapper.SapFeedbackCheckEmpToHrMapper.getEmpByEmpNumber", inMap);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToHrMapper.getEmpByEmpNumber error.", e);
		}
	}

	@Override
	public void deleteByEmpNumber(@Param("list") ArrayList<String> empNumbers)
			throws BaseException {
		try {
			this.delete("Mapper.SapFeedbackCheckEmpToHrMapper.deleteByEmpNumber", empNumbers);
		} catch (Exception e) {
			throw new BaseException("SapFeedbackCheckEmpToHrMapper.deleteByEmpNumber error.", e);
		}
	}

}
