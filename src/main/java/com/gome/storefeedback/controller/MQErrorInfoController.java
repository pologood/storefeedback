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

import com.gome.storefeedback.entity.MQErrorInfo;
import com.gome.storefeedback.service.MQErrorInfoService;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 入库操作失败记录Controller
 * 
 * @author Zhang.Mingji
 * @date 2014年8月7日上午11:17:13
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/mQErrorInfo")
public class MQErrorInfoController {

    private MQErrorInfoService mQErrorInfoService;

    public MQErrorInfoService getMQErrorInfoService() {
        return mQErrorInfoService;
    }

    @Autowired
    public void setMQErrorInfoService(@Qualifier("mQErrorInfoService") MQErrorInfoService mQErrorInfoService) {
        this.mQErrorInfoService = mQErrorInfoService;
    }

    @RequestMapping(value = "/findByPage", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String findByPage(MQErrorInfo mQErrorInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> inMap = new HashMap<String, Object>();
       
        PaginationParameters param = new PaginationParameters(request, response);
        Page<MQErrorInfo> mQErrorInfoPage = this.mQErrorInfoService.findMQErrorInfoByPage(inMap, param);

        Map<String, Object> result = new HashMap<String, Object>();
        
        if (null == mQErrorInfoPage) {
            result.put("total", 0);
            result.put("rows", new ArrayList());
        } else {
            result.put("total", mQErrorInfoPage.getTotalSize());
            result.put("rows", mQErrorInfoPage.getDataList());
        }

        return JsonUtil.javaObjectToJsonString(result);
    }
    
	@RequestMapping(value="/findMQErrorInfo", produces="text/plain;charset=UTF-8")
	public @ResponseBody String findMQErrorInfoById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> inMap = new HashMap<String, Object>();

		MQErrorInfo entity = this.mQErrorInfoService.findMQErrorInfoById(inMap);
		result.put("data", entity);
		return JsonUtil.javaObjectToJsonString(result);
	}
}