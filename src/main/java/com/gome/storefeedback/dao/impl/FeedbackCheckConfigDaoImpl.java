package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.FeedbackCheckConfigDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.FeedbackAppeal;
import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
@Component("feedbackCheckConfigDao")
public class FeedbackCheckConfigDaoImpl  extends BaseDaoImpl<FeedbackCheckConfig, FeedbackCheckConfig> implements FeedbackCheckConfigDao {

	@Override
	public int delete(Map<String, Object> inMap) throws BaseException {
		return this.delete("Mapper.FeedbackCheckConfig.deleteByPrimaryKey", inMap);
	}

	@Override
	public int insert(FeedbackCheckConfig record) throws BaseException {
		return this.insert("Mapper.FeedbackCheckConfig.insert",record);
	}

	@Override
	public List find(Map<String, Object> inMap) throws BaseException {
		return this.findByParam("Mapper.FeedbackCheckConfig.list", inMap);
	}

	@Override
	public FeedbackCheckConfig findById(Map<String, Object> inMap)
			throws BaseException {
		return null;
		//return this.f("Mapper.FeedbackCheckConfig.findById", inMap);
	}

	@Override
	public int update(FeedbackCheckConfig record) throws BaseException {
		return this.update("Mapper.FeedbackCheckConfig.update", record);
	}

	@Override
	public Page findByPage(Map<String, Object> inMap, PaginationParameters param)
			throws BaseException {
		return this.findByPage("Mapper.FeedbackCheckConfig.findByPage", inMap, param);
	}

	@Override
	public List<FeedbackCheckConfig> findCheckEmpList(Map<String, Object> inMap) throws BaseException {
		//return this.findByParam("Mapper.FeedbackCheckConfig.findCheckEmpList",positionMap);
		return this.findByParam("Mapper.FeedbackCheckConfig.findCheckEmpList", inMap);
	}

}
