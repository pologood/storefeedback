package com.gome.storefeedback.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.LackDivisionDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.LackDivision;
import com.gome.storefeedback.exception.BaseException;
@Component("lackDivisionDao")
public class LackDivisionDaoImpl extends BaseDaoImpl<LackDivision, LackDivision> implements LackDivisionDao {

	@Override
	public void deleteByPrimaryKey(String divisionCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(LackDivision record) throws BaseException {
		this.insert("Mapper.LackDivision.insert", record);

	}

	@Override
	public void insertSelective(LackDivision record) throws BaseException {
		this.insert("Mapper.LackDivision.insertSelective", record);

	}

	@Override
	public LackDivision selectByPrimaryKey(String divisionCode)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
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
	public int selectCountByEntity(LackDivision record) throws BaseException {
		 return this.getSqlSession().selectList("Mapper.LackDivision.selectList", record).size();
	}

	@Override
	public List<LackDivision> lackDivisionList() throws BaseException {
		return this.findByParam("Mapper.LackDivision.lackDivisionList", null);
	}

}
