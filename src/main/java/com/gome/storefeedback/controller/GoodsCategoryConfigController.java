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

import com.gome.storefeedback.entity.GoodsCategoryConfig;
import com.gome.storefeedback.service.GoodsCategoryConfigService;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * @author Zhang.Mingji
 * @date 2015年7月20日下午2:39:32
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/goodsCategoryConfig")
public class GoodsCategoryConfigController {
	
	@Resource
	private GoodsCategoryConfigService goodsCategoryConfigService;
	/*@Resource
	private CategoryPositionCacheManager categoryPositionCacheManager;*/

	/**
	 * 分页查询十大品类配置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findGoodsCategoryConfigByPage")
	public @ResponseBody Map<String,Object> findGoodsCategoryConfigByPage(HttpServletRequest request, HttpServletResponse response,
			String categoryCode, String categoryName) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("categoryCode", categoryCode);
		inMap.put("categoryName", categoryName);
		Page page = this.goodsCategoryConfigService.findGoodsCategoryConfigByPage(inMap, new PaginationParameters(request));
		if(null == page){
			result.put("total", 0);
			result.put("rows", new ArrayList());
		}else{
			result.put("total", page.getTotalSize());
			result.put("rows", page.getDataList());
		}
		return result;
	}
	
	/**
	 * 添加十大品类
	 * @param request
	 * @param response
	 * @param ccc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addGoodsCategoryConfig")
	public @ResponseBody Map<String,Object> addGoodsCategoryConfig(HttpServletRequest request, HttpServletResponse response,
			GoodsCategoryConfig ccc)  throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		ccc.setCreateTime(new Date());
		ccc.setId(UUIDUtil.getUUID());
		int r = this.goodsCategoryConfigService.insertGoodsCategoryConfig(ccc);
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
	 * 更新十大品类
	 * @param request
	 * @param response
	 * @param ccc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateGoodsCategoryConfig")
	public @ResponseBody Map<String,Object> updateGoodsCategoryConfig(HttpServletRequest request, HttpServletResponse response,
			GoodsCategoryConfig ccc)  throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		int r = this.goodsCategoryConfigService.updateGoodsCategoryConfig(ccc);
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
	 * 删除十大品类
	 * @param request
	 * @param response
	 * @param ccc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteGoodsCategoryConfig")
	public @ResponseBody Map<String,Object> deleteGoodsCategoryConfig(HttpServletRequest request, HttpServletResponse response,
			String cccId)  throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("id", cccId);
		int r = this.goodsCategoryConfigService.deleteGoodsCategoryConfig(inMap);
		if(r > 0){
			result.put("flag", "1");
			result.put("message", "删除成功");
		}else{
			result.put("flag", "0");
			result.put("message", "删除失败");
		}
		return result;
	}
	
	/**
	 * 通过ID查找品类
	 * @param request
	 * @param response
	 * @param cccId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findGoodsCategoryConfigById")
	public @ResponseBody GoodsCategoryConfig findGoodsCategoryConfigById(HttpServletRequest request, HttpServletResponse response,
			String cccId)  throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("id", cccId);
		GoodsCategoryConfig cc = this.goodsCategoryConfigService.findGoodsCategoryConfigById(inMap);
		return cc;
	}
	/**
	 * 加载缓存
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping("/loadGoodsCategoryConfigCache")
	public @ResponseBody Map<String,Object> loadGoodsCategoryConfigCache(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		this.categoryPositionCacheManager.loadCategoryPositionDataToCache();
		result.put("flag", "1");
		result.put("message", "加载成功");
		return result;
	}*/
}
