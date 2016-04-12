package com.gome.storefeedback.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackMessagePushRecordService;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 消息管理控制器
 * @author Ma.Mingyang
 * @date 2015年1月27日上午10:07:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("message")
public class MessageController {

	//注入service
	@Resource
	private FeedbackMessagePushRecordService recordService;
	
	/**
	 * 分页查询消息
	 * @param req
	 * @param res
	 * @return json
	 * @throws BaseException 
	 */
	@RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String findByPage(FeedbackMessagePushRecord record,HttpServletRequest req,HttpServletResponse res) throws BaseException{
		//结果map
		Map<String,Object> result=new HashMap<String,Object>();
		//参数map
		Map<String,Object> inMap=new HashMap<String,Object>();
		//初始化
		PaginationParameters param = new PaginationParameters(req, res);
		//组装查询条件
		if(StringUtils.isNotBlank(record.getTitle())){
			inMap.put("title", record.getTitle());
		}
		if(StringUtils.isNotBlank(record.getContent())){
			inMap.put("content", record.getContent());
		}
		if(StringUtils.isNotBlank(record.getNotificationEmployeeId())){
			inMap.put("notificationEmployeeId", record.getNotificationEmployeeId());
		}
		if(StringUtils.isNotBlank(record.getNotificationEmployeeName())){
			inMap.put("notificationEmployeeName", record.getNotificationEmployeeName());
		}
		
		//inMap.put("", CurrentUser.getCurrentUser(req).getUsername())
		Page<FeedbackMessagePushRecord> recordByPage = recordService.findFeedbackMessagePushRecordByPage(inMap, param);
		if (null==recordByPage) {
			result.put("total", 0);
			result.put("rows", new ArrayList<FeedbackMessagePushRecord>());
		}else {
			result.put("total", recordByPage.getTotalSize());
			result.put("rows", recordByPage.getDataList());
		}
		return JsonUtil.javaObjectToJsonString(result);
	}
}
