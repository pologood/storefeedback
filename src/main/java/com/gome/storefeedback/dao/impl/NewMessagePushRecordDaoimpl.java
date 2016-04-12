package com.gome.storefeedback.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.storefeedback.dao.NewMessagePushRecordDao;
import com.gome.storefeedback.dao.common.BaseDaoImpl;
import com.gome.storefeedback.entity.NewMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
@Repository("newMessagePushRecordDao")
public class NewMessagePushRecordDaoimpl extends BaseDaoImpl<NewMessagePushRecord, NewMessagePushRecord> implements NewMessagePushRecordDao {

	@Override
	public void deleteNewMessagePushRecord(Map<String, Object> inMap)
			throws BaseException {
		 this.delete("Mapper.NewMessagePushRecord.deleteByPrimaryKey", inMap);

	}

	@Override
	public void insertNewMessagePushRecord(NewMessagePushRecord record)
			throws BaseException {
		 this.insert("Mapper.NewMessagePushRecord.insertSelective", record);

	}

	@Override
	public void insertSelective(NewMessagePushRecord record)
			throws BaseException {
		this.insert("Mapper.NewMessagePushRecord.insert", record);

	}

	@Override
	public NewMessagePushRecord findNewMessagePushRecordById(
			Map<String, Object> inMap) throws BaseException {
		 return this.findByID("Mapper.NewMessagePushRecord.selectByPrimaryKey", inMap);
	}

	@Override
	public void updateByPrimaryKeySelective(NewMessagePushRecord record)
			throws BaseException {
		 this.update("Mapper.NewMessagePushRecord.update", record);

	}

	@Override
	public List<NewMessagePushRecord> findNewMessagePushRecord(
			Map<String, Object> inMap) throws BaseException {
		 return this.findByParam("Mapper.NewMessagePushRecord.findList", inMap);
	}

}
