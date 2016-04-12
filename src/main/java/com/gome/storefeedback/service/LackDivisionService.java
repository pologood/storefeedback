package com.gome.storefeedback.service;

import java.util.List;

import com.gome.storefeedback.entity.LackDivision;
import com.gome.storefeedback.entity.NewCategory;
import com.gome.storefeedback.exception.BaseException;

public interface LackDivisionService {
	public void  deleteByPrimaryKey(String divisionCode);

	public void  insert(LackDivision record) throws BaseException;

	public void  insertSelective(LackDivision record) throws BaseException;

	public LackDivision selectByPrimaryKey(String divisionCode) throws BaseException;
	
	public List<LackDivision> lackDivisionList() throws BaseException;

    public void  updateByPrimaryKeySelective(LackDivision record) throws BaseException;

    public void  updateByPrimaryKey(LackDivision record) throws BaseException;

	public int checkNewCategory(LackDivision record) throws BaseException;
}
