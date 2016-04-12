package com.gome.storefeedback.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.gome.storefeedback.entity.Person;
import com.gome.storefeedback.service.FeedbackService;
import com.gome.storefeedback.service.PersonService;
import com.gome.storefeedback.util.JsonUtil;


/**
 * 缺货记录反馈Controller
 * @author Zhang.Jingang
 * @date 2015年1月28日下午5:02:41
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/feedback")
public class FeedBackController {

	private FeedbackService feedbackService;
	

	public FeedbackService getFeedbackService() {
        return feedbackService;
    }
	
	@Autowired
    public void setFeedbackService(@Qualifier("feedbackService")FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }



	@RequestMapping(value="/list", produces="text/plain;charset=UTF-8")
    public @ResponseBody String FeedbackList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        String sponsorEmployeeName = request.getParameter("sponsorEmployeeName");
        String storeCode = request.getParameter("storeCode");
        inMap.put("sponsorEmployeeName", sponsorEmployeeName);
        inMap.put("storeCode", storeCode);
        Page page = this.feedbackService.findFeedbackByPage(inMap, new PaginationParameters(request, response));
        if(null == page){
            result.put("total", 0);
            result.put("rows", new ArrayList<Object>());
        }else{
            result.put("total", page.getTotalSize());
            result.put("rows", page.getDataList());
        }
        
        return JsonUtil.javaObjectToJsonString(result);
    }
    @RequestMapping(value="/findFeedback", produces="text/plain;charset=UTF-8")
	public @ResponseBody String findFeedbackById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> inMap = new HashMap<String, Object>();
        return null;
		
	}
}
