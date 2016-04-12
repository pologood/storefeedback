package com.gome.storefeedback.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackPushCategoryConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
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
@RequestMapping("/feedbackPush")
public class FeedbackPushConfigController {

    @Resource
    private FeedbackPushCategoryConfigService feedbackPushCategoryConfigService;

    @Resource
    private FeedbackPushCategoryPositionConfigService feedbackPushCategoryPositionConfigService;

    /**
     * 根据 品类编码 获取部门
     * 
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    @RequestMapping("/findOrgPageByCategory")
    public @ResponseBody
    Map<String, Object> findOrgPageByCategory(HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        String categoryCode = request.getParameter("categoryCode");
        User user  = (User) request.getSession().getAttribute("existUser");
        if(!StringUtils.equals(user.getName(), "admin")){
        	inMap.put("orgNumber", user.getName().substring(0, user.getName().length()-3));
        }
        if (categoryCode != null && !"".equals(categoryCode)) {
            inMap.put("categoryCode", categoryCode);
            PaginationParameters param = new PaginationParameters(request,response);
            Page page = this.feedbackPushCategoryPositionConfigService.findOrgPageByCategory(inMap, param);
            if (null == page) {
                result.put("total", 0);
                result.put("rows", new ArrayList());
            } else {
                result.put("total", page.getTotalSize());
                result.put("rows", page.getDataList());
            }
        } else {
            result.put("total", 0);
            result.put("rows", new ArrayList());
        }
        return result;
    }

    /**
     * 分页查找品类职务对应关系
     * 
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    @RequestMapping("/findCategoryPositionByPage")
    public @ResponseBody
    Map<String, Object> findCategoryPositionByPage(HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        String categoryCode = request.getParameter("categoryCode");
        String orgNumber = request.getParameter("orgNumber");
        String srcOrgNumber = request.getParameter("srcOrgNumber");
        String positionCode = request.getParameter("positionCode");
        String positionName = request.getParameter("positionName");
        User user  = (User) request.getSession().getAttribute("existUser");
        if (categoryCode != null && !"".equals(categoryCode)) {
            inMap.put("categoryCode", categoryCode);
            inMap.put("srcOrgNumber", srcOrgNumber);
            if(StringUtils.isBlank(orgNumber) && !StringUtils.equals(user.getName(), "admin")){
            	inMap.put("orgNumber", user.getName().substring(0, user.getName().length()-3));
            }else{
            	inMap.put("orgNumber", orgNumber);
            }
            inMap.put("positionCode", positionCode);
            inMap.put("positionDesc", positionName);
            PaginationParameters param = new PaginationParameters(request,response);
            Page page = this.feedbackPushCategoryPositionConfigService.findByPage(inMap, param);
            if (null == page) {
                result.put("total", 0);
                result.put("rows", new ArrayList());
            } else {
                result.put("total", page.getTotalSize());
                result.put("rows", page.getDataList());
            }
        } else {
            result.put("total", 0);
            result.put("rows", new ArrayList());
        }
        return result;
    }

    /**
     * 添加品类职务对应关系
     * 
     * @param request
     * @param response
     * @param cp
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "/addCategoryPosition", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> addCategoryPosition(HttpServletRequest request, HttpServletResponse response,
            FeedbackPushCategoryPositionConfig cp) throws BaseException {
    	Map<String, Object> result = new HashMap<String, Object>();
    	result = this.checkCategoryPosition(request, response, cp);
//    	if("1".equals(result.get("flag"))){
//    		result.put("flag", "0");
//    		result.put("message", "添加失败");
//    	}else {
    		cp.setCreateTime(new Date());
    		cp.setId(UUIDUtil.getUUID());
    		int r = this.feedbackPushCategoryPositionConfigService.insert(cp);
    		if (r > 0) {
    			result.put("flag", "1");
    			result.put("message", "添加成功");
    		} else {
    			result.put("flag", "0");
    			result.put("message", "添加失败");
    		}
//    	}
        return result;
    }

    /**
     * 更新品类职务
     * 
     * @param request
     * @param response
     * @param cp
     * @return
     * @throws BaseException
     */
    @RequestMapping("/updateCategoryPosition")
    public @ResponseBody
    Map<String, Object> updateCategoryPosition(HttpServletRequest request, HttpServletResponse response,
            FeedbackPushCategoryPositionConfig cp) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        int r = this.feedbackPushCategoryPositionConfigService.update(cp);
        if (r > 0) {
            result.put("flag", "1");
            result.put("message", "更新成功");
        } else {
            result.put("flag", "0");
            result.put("message", "更新失败");
        }
        return result;
    }

    /**
     * 删除品类职务
     * 
     * @param request
     * @param response
     * @param cp
     * @return
     * @throws BaseException
     */
    @RequestMapping("/deleteCategoryPosition")
    public @ResponseBody
    Map<String, Object> deleteCategoryPosition(HttpServletRequest request, HttpServletResponse response,
            CategoryPosition cp) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        String id = request.getParameter("id");
        if (id != null && !"".equals(id)) {
            inMap.put("id", id);
            int r = this.feedbackPushCategoryPositionConfigService.delete(inMap);
            if (r > 0) {
                result.put("flag", "1");
                result.put("message", "删除成功");
            } else {
                result.put("flag", "0");
                result.put("message", "删除失败");
            }
        } else {
            result.put("flag", "0");
            result.put("message", "删除失败");
        }
        return result;
    }

    /**
     * 分页查询十大品类配置
     * 
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    @RequestMapping("/findGoodsCategoryConfigByPage")
    public @ResponseBody
    Map<String, Object> findGoodsCategoryConfigByPage(HttpServletRequest request, HttpServletResponse response,
            String categoryCode, String categoryName) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("categoryCode", categoryCode);
        inMap.put("categoryName", categoryName);
        Page page = this.feedbackPushCategoryConfigService.findByPage(inMap, new PaginationParameters(request,response));
        if (null == page) {
            result.put("total", 0);
            result.put("rows", new ArrayList());
        } else {
            result.put("total", page.getTotalSize());
            result.put("rows", page.getDataList());
        }
        return result;
    }

    /**
     * 添加十大品类
     * 
     * @param request
     * @param response
     * @param ccc
     * @return
     * @throws BaseException
     */
    @RequestMapping("/addGoodsCategoryConfig")
    public @ResponseBody
    Map<String, Object> addGoodsCategoryConfig(HttpServletRequest request, HttpServletResponse response,
            FeedbackPushCategoryConfig ccc) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        ccc.setCreateTime(new Date());
        ccc.setId(UUIDUtil.getUUID());
        int r = this.feedbackPushCategoryConfigService.insert(ccc);
        if (r > 0) {
            result.put("flag", "1");
            result.put("message", "添加成功");
        } else {
            result.put("flag", "0");
            result.put("message", "添加失败");
        }
        return result;
    }

    /**
     * 更新十大品类
     * 
     * @param request
     * @param response
     * @param ccc
     * @return
     * @throws BaseException
     */
    @RequestMapping("/updateGoodsCategoryConfig")
    public @ResponseBody
    Map<String, Object> updateGoodsCategoryConfig(HttpServletRequest request, HttpServletResponse response,
            FeedbackPushCategoryConfig ccc) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        int r = this.feedbackPushCategoryConfigService.update(ccc);
        if (r > 0) {
            result.put("flag", "1");
            result.put("message", "更新成功");
        } else {
            result.put("flag", "0");
            result.put("message", "更新失败");
        }
        return result;
    }

    /**
     * 删除十大品类
     * 
     * @param request
     * @param response
     * @param ccc
     * @return
     * @throws BaseException
     */
    @RequestMapping("/deleteGoodsCategoryConfig")
    public @ResponseBody
    Map<String, Object> deleteGoodsCategoryConfig(HttpServletRequest request, HttpServletResponse response, String cccId)
            throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", cccId);
        int r = this.feedbackPushCategoryConfigService.delete(inMap);
        if (r > 0) {
            result.put("flag", "1");
            result.put("message", "删除成功");
        } else {
            result.put("flag", "0");
            result.put("message", "删除失败");
        }
        return result;
    }

    /**
     * 通过ID查找品类
     * 
     * @param request
     * @param response
     * @param cccId
     * @return
     * @throws BaseException
     */
    @RequestMapping("/findGoodsCategoryConfigById")
    public @ResponseBody
    FeedbackPushCategoryConfig findGoodsCategoryConfigById(HttpServletRequest request, HttpServletResponse response,
            String cccId) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", cccId);
        FeedbackPushCategoryConfig cc = this.feedbackPushCategoryConfigService.findById(inMap);
        return cc;
    }
    
    
    /**
     * 分页查找品类职务对应关系
     * 
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    @RequestMapping("/checkCategoryPosition")
    public @ResponseBody
    Map<String, Object> checkCategoryPosition(HttpServletRequest request, HttpServletResponse response,FeedbackPushCategoryPositionConfig cp) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("categoryCode", cp.getCategoryCode());
        inMap.put("srcOrgNumber", cp.getSrcOrgNumber());
        inMap.put("orgNumber", cp.getOrgNumber());
        inMap.put("positionCode", cp.getPositionCode());
//        inMap.put("roleId", cp.getRoleId());
        List<FeedbackPushCategoryPositionConfig> pushList  = this.feedbackPushCategoryPositionConfigService.checkCategoryPosition(inMap);
        if (pushList !=null && pushList.size() > 0) {
            result.put("flag", "1");
            result.put("message", pushList.get(0).getRoleId());
        } else {
            result.put("flag", "0");
	       }
	       return result;
	}
}
