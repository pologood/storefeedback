package com.gome.storefeedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.LackDivisionDao;
import com.gome.storefeedback.dao.NewCategoryStatusDao;
import com.gome.storefeedback.entity.LackDivision;
import com.gome.storefeedback.entity.NewCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.LackDivisionService;
@Service("lackDivisionService")
public class LackDivisionServiceImpl implements LackDivisionService {
    private LackDivisionDao lackDivisionDao;
	
	
	public LackDivisionDao geLackDivisionDao() {
		return lackDivisionDao;
	}
	
	@Autowired
    public void setLackDivisionDao(@Qualifier("lackDivisionDao") LackDivisionDao lackDivisionDao) {
        this.lackDivisionDao = lackDivisionDao;
    }
	@Override
	public void deleteByPrimaryKey(String divisionCode) {
		// TODO Auto-generated method stub 

	}

	@Override
	public void insert(LackDivision record) throws BaseException {
		this.lackDivisionDao.insert(record);

	}

	@Override
	public void insertSelective(LackDivision record) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public LackDivision selectByPrimaryKey(String divisionCode)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LackDivision> lackDivisionList()
			throws BaseException {
		return this.lackDivisionDao.lackDivisionList();
	}

	@Override
	public void updateByPrimaryKeySelective(LackDivision record)
			throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateByPrimaryKey(LackDivision record) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public int checkNewCategory(LackDivision record) throws BaseException {
		 return this.lackDivisionDao.selectCountByEntity(record);
	}

}
