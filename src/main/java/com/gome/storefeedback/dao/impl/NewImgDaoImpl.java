package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.NewImgDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.NewImg;
import com.gome.storefeedback.exception.BaseException;
@Component("newImgDao")
public class NewImgDaoImpl extends BaseDaoImpl<NewImg, NewImg>  implements NewImgDao {

	@Override
	public void delete(NewImg record) throws BaseException {
		this.delete("Mapper.NewImg.delete", record);
	}

	@Override
	public void insert(NewImg record) throws BaseException {
		this.insert("Mapper.NewImg.insert", record);
	}

	@Override
	public List<NewImg> selectByEntity(Map<String, Object> img) throws BaseException {
		return this.findByParam("Mapper.NewImg.findImgList", img);
	}

	@Override
	public void update(NewImg record) throws BaseException {
		this.update("Mapper.NewImg.update", record);
		
	}
}
