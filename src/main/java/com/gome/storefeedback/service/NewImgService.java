package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.NewImg;
import com.gome.storefeedback.exception.BaseException;

public interface NewImgService {
	
	public void delete(NewImg img)  throws BaseException ;

	public void insert(NewImg record)  throws BaseException ;

	public List<NewImg> selectByEntity(Map<String, Object> img)  throws BaseException ;

    public void update(NewImg record)  throws BaseException ;

}
