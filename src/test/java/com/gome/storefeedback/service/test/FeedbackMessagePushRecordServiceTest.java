package com.gome.storefeedback.service.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackMessagePushRecordService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 反馈通知消息推送记录业务层 测试用例
 * @author Ma.Mingyang
 * @date 2015年1月22日下午5:43:10
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackMessagePushRecordServiceTest extends AbstractTransactionalSpringContextTestCase{

	@Resource
	private FeedbackMessagePushRecordService recordService;
	
	/*@Before
	public void init(){
		recordService=(FeedbackMessagePushRecordService) this.getBeanByName("feedbackMessagePushRecordService");
	}*/
	@Test
	@Rollback(false)
	public void testInsertFeedbackMessagePushRecord() throws BaseException {
		FeedbackMessagePushRecord  record=new FeedbackMessagePushRecord();
		record.setId(UUIDUtil.getUUID().toString());
		record.setType(1);
		record.setTitle("通知1");
		record.setNotificationTime(new Date());
		record.setSendId("1");
		record.setSendEmployeeName("111");
		recordService.insertFeedbackMessagePushRecord(record);
	}

	@Test
	@Rollback(false)
	public void testDeleteFeedbackMessagePushRecord() throws BaseException {
		Map inMap =new HashMap<String, String>();
		inMap.put("id", "c969fe07da3840258a16367da023397e");
		recordService.deleteFeedbackMessagePushRecord(inMap);
	}

	@Test
	@Rollback(false)
	public void testUpdateFeedbackMessagePushRecord() throws BaseException {
		FeedbackMessagePushRecord  record=new FeedbackMessagePushRecord();
		record.setId("c969fe07da3840258a16367da023397e");
		record.setTitle("update");
		recordService.updateFeedbackMessagePushRecord(record);
	}

	@Test
	public void testFindFeedbackMessagePushRecordById() throws BaseException {
		Map inMap =new HashMap<String, String>();
		inMap.put("id", "aabe01e532164cff9d35a73a23a4d012");
		FeedbackMessagePushRecord  record=recordService.findFeedbackMessagePushRecordById(inMap);
		System.out.println(record);
	}
	/**
	 * 参数查询测试
	 * @throws BaseException
	 */
	@Test
	public void testFindByParam() throws BaseException{
		Map<String, Object> inMap =new HashMap<String, Object>();
		inMap.put("title", "update");
		PaginationParameters param = new PaginationParameters();
		param.setPageMaxSize(10);
		List list=recordService.findFeedbackMessagePushRecord(inMap);
		System.out.println(list.size()+"-----------");
	}
	/**
	 * 分页查询测试
	 * @throws BaseException
	 */
	@Test
	public void testFindByPage() throws BaseException{
		Map<String, Object> inMap =new HashMap<String, Object>();
		inMap.put("title", "update");
		PaginationParameters param = new PaginationParameters();
		param.setPageMaxSize(10);
		Page page=recordService.findFeedbackMessagePushRecordByPage(inMap, param);
		System.out.println(page.getTotalSize()+"-----------");
	}
}
