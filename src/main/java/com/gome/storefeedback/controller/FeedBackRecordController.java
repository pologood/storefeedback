package com.gome.storefeedback.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
import com.gome.storefeedback.entity.FeedbackRecord;
import com.gome.storefeedback.entity.Person;
import com.gome.storefeedback.service.FeedbackRecordService;
import com.gome.storefeedback.service.FeedbackService;
import com.gome.storefeedback.service.PersonService;
import com.gome.storefeedback.util.JsonUtil;

/**
 * 反馈信息记录Controller
 * @author Zhang.Jingang
 * @date 2015年1月28日下午5:03:42
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/feedbackRecord")
public class FeedBackRecordController {

	private FeedbackRecordService feedbackRecordService;
	

	public FeedbackRecordService getFeedbackRecordService() {
        return feedbackRecordService;
    }
	
	@Autowired
    public void setFeedbackRecordService(@Qualifier("feedbackRecordService")FeedbackRecordService feedbackRecordService) {
        this.feedbackRecordService = feedbackRecordService;
    }

	@RequestMapping(value="/list", produces="text/plain;charset=UTF-8")
    public @ResponseBody String FeedbackRecordList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        String fid = request.getParameter("feedbackId");
        inMap.put("feedbackId", fid);
        Page page = this.feedbackRecordService.findFeedbackRecordByPage(inMap, new PaginationParameters(request, response));
        if(null == page){
            result.put("total", 0); 
            result.put("rows", new ArrayList());
        }else{
            result.put("total", page.getTotalSize());
            result.put("rows", page.getDataList());
        }
        
        return JsonUtil.javaObjectToJsonString(result);
    }
}
