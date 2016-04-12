package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackCheckEmpDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.SapFeedbackCheckEmp;
import com.gome.storefeedback.exception.BaseException;

@Component("sapFeedbackCheckEmpDao")
public class SapFeedbackCheckEmpDaoImpl extends BaseDaoImpl<Map<String, Object> ,SapFeedbackCheckEmp> implements SapFeedbackCheckEmpDao {

	@Override
	public int insert(Map<String, Object> pmap) throws BaseException {
		
		try {
			return this.insert("Mapper.SapFeedBackCheckEmpMapper.insert",pmap);
		} catch (Exception e) {
			throw new BaseException("insertSapFeedbackCheckEmp error.", e);
		}
	}

	@Override
	public List<Map<String, Object>> getAllSapCheck() throws BaseException {
		try {
			return this.findByParam("Mapper.SapFeedBackCheckEmpMapper.list", null);
		} catch (Exception e) {
			throw new BaseException("findSapFeedbackCheckEmp error.", e);
		}
	}

	@Override
	public int batchInsert(@Param("list") List<SapFeedbackCheckEmp> list) throws BaseException {
		try {
			return this.insert("Mapper.SapFeedBackCheckEmpMapper.batchInsert", list);
		} catch (Exception e) {
			throw new BaseException("batchInsertSapFeedbackCheckEmp error.", e);
		}
	}

	@Override
	public int clear() throws BaseException {
		try {
			return this.delete("Mapper.SapFeedBackCheckEmpMapper.truncate", null);
		} catch (Exception e) {
			throw new BaseException("batchInsertSapFeedbackCheckEmp error.", e);
		}
	}

}
