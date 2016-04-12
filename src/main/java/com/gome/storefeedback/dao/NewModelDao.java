package com.gome.storefeedback.dao;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.NewModel;
import com.gome.storefeedback.exception.BaseException;

public interface NewModelDao {
	 public void  deleteByPrimaryKey(String id) throws BaseException;

	 public void  insert(NewModel record) throws BaseException;

	 public NewModel selectByPrimaryKey(String id) throws BaseException;

	 public void  updateByPrimaryKey(NewModel record) throws BaseException;

	public int checkNewCategory(NewModel record) throws BaseException;

	public int checkNewModel(NewModel record) throws BaseException;

	public void updateByPrimaryKeySelective(NewModel record) throws BaseException;

	public List<NewModel> selectList(Map<String, Object> record) throws BaseException;
}