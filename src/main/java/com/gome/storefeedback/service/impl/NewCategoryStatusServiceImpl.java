package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.NewCategoryStatusDao;
import com.gome.storefeedback.dao.NewImgDao;
import com.gome.storefeedback.entity.NewCategoryStatus;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.NewCategoryStatusService;
@Service("newCategoryStatusService")
public class NewCategoryStatusServiceImpl implements NewCategoryStatusService {
	private NewCategoryStatusDao newCategoryStatusDao;
	
	
	public NewCategoryStatusDao getNewCategoryStatusDao() {
		return newCategoryStatusDao;
	}

	@Autowired
	public void setNewCategoryStatusDao(@Qualifier("newCategoryStatusDao") NewCategoryStatusDao newCategoryStatusDao) {
		this.newCategoryStatusDao = newCategoryStatusDao;
	}


	@Override
	public List<NewCategoryStatus> selectByEntity(Map<String, Object> obj)
			throws BaseException {
		 return this.newCategoryStatusDao.selectStatusList(obj);
	}

}
