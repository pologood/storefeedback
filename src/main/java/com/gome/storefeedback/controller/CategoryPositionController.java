package com.gome.storefeedback.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.service.CategoryPositionService;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;
/**
 * 品类职务对应关系
 * @author Zhang.Mingji
 * @date 2015年7月20日下午3:22:04
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/categoryPosition")
public class CategoryPositionController {
	
	@Resource
	private CategoryPositionService categoryPositionService;

	/**
	 * 分页查找品类职务对应关系
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCategoryPositionByPage")
	public @ResponseBody Map<String,Object> findCategoryPositionByPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> inMap = new HashMap<String,Object>();
		String goodsCategoryId = request.getParameter("goodsCategoryId");
		String positionCode = request.getParameter("positionCode");
		String positionName = request.getParameter("positionName");
		if(goodsCategoryId != null && !"".equals(goodsCategoryId)){
			inMap.put("goodsCategoryId", goodsCategoryId);
			inMap.put("positionCode", positionCode);
			inMap.put("positionDesc", positionName);
			PaginationParameters param = new PaginationParameters(request);
			Page page = this.categoryPositionService.findCategoryPositionByPage(inMap, param );
			if(null == page){
				result.put("total", 0);
				result.put("rows", new ArrayList());
			}else{
				result.put("total", page.getTotalSize());
				result.put("rows", page.getDataList());
			}
		}else{
			result.put("total", 0);
			result.put("rows", new ArrayList());
		}
		return result;
	}
	
	/**
	 * 添加品类职务对应关系
	 * @param request
	 * @param response
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addCategoryPosition")
	public @ResponseBody Map<String,Object> addCategoryPosition(HttpServletRequest request, HttpServletResponse response,
			CategoryPosition cp) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		cp.setCreateTime(new Date());
		cp.setId(UUIDUtil.getUUID());
		int r = this.categoryPositionService.insertCategoryPosition(cp);
		if(r > 0){
			result.put("flag", "1");
			result.put("message", "添加成功");
		}else{
			result.put("flag", "0");
			result.put("message", "添加失败");
		}
		return result;
	}
	/**
	 * 更新品类职务
	 * @param request
	 * @param response
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateCategoryPosition")
	public @ResponseBody Map<String,Object> updateCategoryPosition(HttpServletRequest request, HttpServletResponse response,
			CategoryPosition cp) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		int r = this.categoryPositionService.updateCategoryPosition(cp);
		if(r > 0){
			result.put("flag", "1");
			result.put("message", "更新成功");
		}else{
			result.put("flag", "0");
			result.put("message", "更新失败");
		}
		return result;
	}
	
	/**
	 * 删除品类职务
	 * @param request
	 * @param response
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteCategoryPosition")
	public @ResponseBody Map<String,Object> deleteCategoryPosition(HttpServletRequest request, HttpServletResponse response,
			CategoryPosition cp) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> inMap = new HashMap<String,Object>();
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)){
			inMap.put("id", id);
			int r = this.categoryPositionService.deleteCategoryPosition(inMap);
			if(r > 0){
				result.put("flag", "1");
				result.put("message", "删除成功");
			}else{
				result.put("flag", "0");
				result.put("message", "删除失败");
			}
		}else{
			result.put("flag", "0");
			result.put("message", "删除失败");
		}
		return result;
	}
	
	
}
