package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.NewCategoryStatusDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.NewCategoryStatus;
import com.gome.storefeedback.exception.BaseException;
@Component("newCategoryStatusDao")
public class NewCategoryStatusDaoImpl extends BaseDaoImpl<NewCategoryStatus, NewCategoryStatus> implements NewCategoryStatusDao {

	@Override
	public List<NewCategoryStatus> selectStatusList(Map<String, Object> obj) throws BaseException {
		return this.findByParam("Mapper.NewCategoryStatus.selectStatusList", obj);
	}

}
