package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.NewCategory;
import com.gome.storefeedback.exception.BaseException;

public interface NewCategoryService {
	
	
	public void deleteByPrimaryKey(String id) throws BaseException ;
	
	public void insertCategory(NewCategory record) throws BaseException ;
	
	public NewCategory selectByPrimaryKey(String id) throws BaseException ;
	
	public List<NewCategory> selectList(Map<String, Object> cate) throws BaseException ;

	public void updateByPrimaryKey(NewCategory record) throws BaseException ;
	
	public void updateByPrimaryKeySelective(NewCategory record) throws BaseException ;
	
	public int checkNewCategory(NewCategory record) throws BaseException ;
	
	public int checkNewModel(NewCategory record) throws BaseException ;
}
