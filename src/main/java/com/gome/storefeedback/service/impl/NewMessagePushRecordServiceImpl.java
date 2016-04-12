package com.gome.storefeedback.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.storefeedback.dao.NewMessagePushRecordDao;
import com.gome.storefeedback.entity.NewMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.NewMessagePushRecordService;
@Service("newMessagePushRecordService")
public class NewMessagePushRecordServiceImpl implements NewMessagePushRecordService {
	
	 private NewMessagePushRecordDao newMessagePushRecordDao;
	 
	public NewMessagePushRecordDao getNewMessagePushRecordDao() {
		return newMessagePushRecordDao;
	}
	@Autowired
	public void setNewMessagePushRecordDao(@Qualifier("newMessagePushRecordDao") NewMessagePushRecordDao newMessagePushRecordDao) {
		this.newMessagePushRecordDao = newMessagePushRecordDao;
	}

	@Override
	public void deleteNewMessagePushRecord(Map<String, Object> inMap)
			throws BaseException {
		this.newMessagePushRecordDao.deleteNewMessagePushRecord(inMap);

	}

	@Override
	public void insertNewMessagePushRecord(NewMessagePushRecord record)
			throws BaseException {
		this.newMessagePushRecordDao.insertNewMessagePushRecord(record);

	}

	@Override
	public void insertSelective(NewMessagePushRecord record)
			throws BaseException {
		this.newMessagePushRecordDao.insertSelective(record);

	}

	@Override
	public NewMessagePushRecord findNewMessagePushRecordById(
			Map<String, Object> inMap) throws BaseException {
		return this.newMessagePushRecordDao.findNewMessagePushRecordById(inMap);
	}

	@Override
	public void updateByPrimaryKeySelective(NewMessagePushRecord record)
			throws BaseException {
		this.newMessagePushRecordDao.updateByPrimaryKeySelective(record);

	}

	@Override
	public List<NewMessagePushRecord> findNewMessagePushRecord(
			Map<String, Object> inMap) throws BaseException {
		return this.newMessagePushRecordDao.findNewMessagePushRecord(inMap);
	}

}
