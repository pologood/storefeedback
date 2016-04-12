package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.NewModelDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.NewModel;
import com.gome.storefeedback.exception.BaseException;
@Component("newModelDao")
public class NewModelDaoImpl  extends BaseDaoImpl<NewModel, NewModel>  implements NewModelDao {

	@Override
	public void deleteByPrimaryKey(String id) throws BaseException {
		this.delete("Mapper.NewModel.delete", id);
	}

	@Override
	public void insert(NewModel record) throws BaseException {
		this.insert("Mapper.NewModel.insertSelective", record);

	}

	@Override
	public NewModel selectByPrimaryKey(String id) throws BaseException {
		 return this.findByID("Mapper.NewModel.selectByPrimaryKey", id);
	}

	@Override
	public void updateByPrimaryKey(NewModel record) throws BaseException {
		this.update("Mapper.NewModel.updateByPrimaryKey", record);

	}

	@Override
	public void updateByPrimaryKeySelective(NewModel record)
			throws BaseException {
		this.update("Mapper.NewModel.updateByPrimaryKeySelective", record);
		
	}

	@Override
	public List<NewModel> selectList(Map<String, Object> record)
			throws BaseException {
		return this.findByParam("Mapper.NewModel.selectList", record);
	}

	@Override
	public int checkNewCategory(NewModel record) throws BaseException {
		 return this.getSqlSession().insert("Mapper.NewModel.selectCategorycount", record);
	}

	@Override
	public int checkNewModel(NewModel record) throws BaseException {
		return this.getSqlSession().insert("Mapper.NewModel.selectModelcount", record);
	}


}
