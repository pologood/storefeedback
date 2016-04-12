package com.gome.storefeedback.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.ResultNoReason;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.ResultNoReasonService;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 缺断货推送关系
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午4:50:06
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/resultNoReason")
public class ResultNoReasonController {

    @Resource
    private ResultNoReasonService resultNoReasonService;

    /**
     * 获取列表
     * 
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    @RequestMapping("/findList")
    public @ResponseBody
    Map<String, Object> findList(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        List list = resultNoReasonService.findResultNoReason(null);
        if (null != list && list.size() > 0) {
            result.put("total", list.size());
            result.put("rows", list);
        } else {
            result.put("total", 0);
            result.put("rows", new ArrayList());

        }
        return result;
    }

    /**
     * 添加
     * 
     * @param request
     * @param response
     * @param entity
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> insert(HttpServletRequest request, HttpServletResponse response, ResultNoReason entity)
            throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        entity.setIsUsing(1);
        entity.setSortOrder(0);
        try {
            resultNoReasonService.insertResultNoReason(entity);
            result.put("flag", "1");
            result.put("message", "添加成功");
        } catch (BaseException e) {
            result.put("flag", "0");
            result.put("message", "添加失败");
        }
        return result;
    }

    /**
     * 更新
     * 
     * @param request
     * @param response
     * @param cp
     * @return
     * @throws BaseException
     */
    @RequestMapping("/update")
    public @ResponseBody
    Map<String, Object> update(HttpServletRequest request, HttpServletResponse response, ResultNoReason entity)
            throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            resultNoReasonService.updateResultNoReason(entity);
            result.put("flag", "1");
            result.put("message", "修改成功");
        } catch (BaseException e) {
            result.put("flag", "0");
            result.put("message", "修改失败");
        }
        return result;
    }

    /**
     * 删除
     * 
     * @param request
     * @param response
     * @param cp
     * @return
     * @throws BaseException
     */
    @RequestMapping("/delete")
    public @ResponseBody
    Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response, Integer resultNoCode)
            throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, Object> inMap = new HashMap<String, Object>();
            inMap.put("resultNoCode", resultNoCode);
            resultNoReasonService.deleteResultNoReason(inMap);
            result.put("flag", "1");
            result.put("message", "删除成功");
        } catch (BaseException e) {
            result.put("flag", "0");
            result.put("message", "删除失败");
        }
        return result;
    }
}
