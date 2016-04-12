package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.NewCategoryDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.NewCategory;
import com.gome.storefeedback.exception.BaseException;
@Component("newCategoryDao")
public class NewCategoryDaoImpl  extends BaseDaoImpl<NewCategory, NewCategory> implements NewCategoryDao {

	@Override
	public void deleteByPrimaryKey(String id) throws BaseException {
		this.delete("Mapper.NewCategory.delete", id);
	}

	@Override
	public void insertCategory(NewCategory record) throws BaseException {
		try {
			this.insert("Mapper.NewCategory.insertSelective",
					record);
		} catch (Exception e) {
			throw new BaseException("insert NewCategory error!",
					e);
		}

	}

	@Override
	public NewCategory selectByPrimaryKey(String id) throws BaseException {
		 return this.findByID("Mapper.NewCategory.selectByPrimaryKey", id);
	}

	@Override
	public void updateByPrimaryKey(NewCategory record) throws BaseException {
		this.update("Mapper.NewCategory.updateByPrimaryKey", record);

	}

	@Override
	public int checkNewCategory(NewCategory record) throws BaseException {
		 return this.getSqlSession().insert("Mapper.NewCategory.selectCategorycount", record);
	}

	@Override
	public int checkNewModel(NewCategory record) throws BaseException {
		return this.getSqlSession().insert("Mapper.NewCategory.selectModelcount", record);
	}

	@Override
	public void updateByPrimaryKeySelective(NewCategory record)
			throws BaseException {
		this.update("Mapper.NewCategory.updateByPrimaryKeySelective", record);
		
	}

	@Override
	public List<NewCategory> selectList(Map<String, Object> cate)
			throws BaseException {
		return this.getSqlSession().selectList("Mapper.NewCategory.selectList", cate);
	}

}
