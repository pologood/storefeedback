package com.gome.storefeedback.service;

import java.util.List;
import java.util.Map;

import com.gome.storefeedback.entity.NewMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;

public interface NewMessagePushRecordService {
	
	 public void deleteNewMessagePushRecord(Map<String, Object> inMap) throws BaseException;
	    
	    public void  insertNewMessagePushRecord (NewMessagePushRecord record)  throws BaseException;
	
	    public void insertSelective(NewMessagePushRecord record) throws BaseException;
	
	    public NewMessagePushRecord findNewMessagePushRecordById(Map<String, Object> inMap) throws BaseException;
	
	    public void updateByPrimaryKeySelective(NewMessagePushRecord record) throws BaseException;
	    
	    public List<NewMessagePushRecord> findNewMessagePushRecord(Map<String, Object> inMap) throws BaseException;

}
