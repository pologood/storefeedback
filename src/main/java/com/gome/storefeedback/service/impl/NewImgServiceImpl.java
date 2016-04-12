package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.NewImgDao;
import com.gome.storefeedback.dao.NewModelDao;
import com.gome.storefeedback.entity.NewImg;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.NewImgService;
@Service("newImgService")
public class NewImgServiceImpl  implements NewImgService {
	private NewImgDao newImgDao;
	
	
	public NewImgDao getNewImgDao() {
		return newImgDao;
	}
	@Autowired
    public void setNewImgDao(@Qualifier("newImgDao") NewImgDao newImgDao) {
        this.newImgDao = newImgDao;
    }
	@Override
	public void insert(NewImg record) throws BaseException {
		this.newImgDao.insert(record);
		
	}

	@Override
	public List<NewImg> selectByEntity(Map<String, Object> img) throws BaseException {
		return this.newImgDao.selectByEntity(img);
	}

	@Override
	public void update(NewImg record) throws BaseException {
		this.newImgDao.update(record);
		
	}

	@Override
	public void delete(NewImg img) throws BaseException {
		this.newImgDao.delete(img);
		
	}

	
}
