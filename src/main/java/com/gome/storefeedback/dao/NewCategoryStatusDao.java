package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.NewCategoryStatus;
import com.gome.storefeedback.exception.BaseException;

public interface NewCategoryStatusDao {
	public List<NewCategoryStatus> selectStatusList(Map<String, Object> obj)  throws BaseException ;
}