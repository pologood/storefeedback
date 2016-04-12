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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.controller.app.SapFeedbackController;
import com.gome.storefeedback.entity.Brand;
import com.gome.storefeedback.entity.CategoryPosition;
import com.gome.storefeedback.entity.FeedbackCheckConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryConfig;
import com.gome.storefeedback.entity.FeedbackPushCategoryPositionConfig;
import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.entity.User;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.CheckSapFeedbackService;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackCheckConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryConfigService;
import com.gome.storefeedback.service.FeedbackPushCategoryPositionConfigService;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.JsonUtil;
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
@RequestMapping("/feedbackCheck")
public class FeedbackCheckConfigController {

    private static final Logger logger = LoggerFactory.getLogger(SapFeedbackController.class);

    @Resource
    private FeedbackPushCategoryConfigService feedbackPushCategoryConfigService;

    @Resource
    private FeedbackPushCategoryPositionConfigService feedbackPushCategoryPositionConfigService;
    
    
    @Resource
    private FeedbackCheckConfigService feedbackCheckConfigService;
    
    @Resource
    private CheckSapFeedbackService checkSapFeedbackService;
    
    @Resource
    private GoodsService goodsService;
    @Resource
    private EmployeeRemoteService employeeRemoteService;
    
    @RequestMapping(value = "/findbrandCodeList",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String findbrandCodeList(HttpServletRequest request, HttpServletResponse response)
            throws BaseException {
        Map<String, Object> inMap = new HashMap<String, Object>();
        String categoryCode = request.getParameter("categoryCode");
        if (categoryCode != null && !"".equals(categoryCode)) {
            inMap.put("categoryCode", categoryCode);
             List<Brand> list = this.checkSapFeedbackService.findBrandList(inMap);
             List<Brand> listResult  = new ArrayList<Brand>();
             for(Brand sap : list){
            	 Brand brand = new Brand(); 
            	 brand.setBrandCode(sap.getBrandCode());
            	 brand.setBrandName(sap.getBrandName());
            	 listResult.add(brand);
             }
             if (null != listResult) {
                 return JsonUtil.javaObjectToJsonString(listResult);
             }
        }
        return null;
    }
    
    
    @RequestMapping(value = "/findCategoryConfigList",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String findCategoryConfigList(HttpServletRequest request, HttpServletResponse response,
            String categoryCode, String categoryName) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        /*inMap.put("categoryCode", categoryCode);
        inMap.put("categoryName", categoryName);*/
        List<FeedbackPushCategoryConfig> list = this.feedbackPushCategoryConfigService.findList(inMap);
        if (null != list) {
            return JsonUtil.javaObjectToJsonString(list);
        }
        return null;
    }

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
    	this.checkCategoryPosition(request, response, cp);
        Map<String, Object> result = new HashMap<String, Object>();
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
    @RequestMapping("/findConfigByPage")
    public @ResponseBody
    Map<String, Object> findConfigByPage(HttpServletRequest request, HttpServletResponse response,
            String categoryCode, String categoryName,String oaNumber) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("categoryCode", categoryCode);
        inMap.put("categoryName", categoryName);
        try {
        	if(oaNumber != null && oaNumber != ""){
        		inMap.put("empNumber", getEmpByAcccount1(request,response));
        		
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Page page = this.feedbackCheckConfigService.findByPage(inMap, new PaginationParameters(request,response));
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
    @RequestMapping("/addCheckConfig")
    public @ResponseBody
    Map<String, Object> addCheckConfig(HttpServletRequest request, HttpServletResponse response,
    		FeedbackCheckConfig ccc) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        ccc.setCreateTime(new Date());
        ccc.setId(UUIDUtil.getUUID());
        ccc.setSts("A");
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("regionName", ccc.getRegionName());
        inMap.put("orgNumber", ccc.getOrgNumber());
        inMap.put("categoryCode", ccc.getCategoryCode());
        inMap.put("brandCode", ccc.getBrandCode());
        inMap.put("empNumber", ccc.getEmpNumber());
        List list = feedbackCheckConfigService.findCheckEmpList(inMap);
        if(list != null && list.size() > 0){
        	result.put("flag", "3");
    		result.put("mess", "员工：["+ccc.getEmpNumber()+"]已配置品牌："+ccc.getBrandName()+"请重新选择配置！");
        }else{
        	
        	int r = this.feedbackCheckConfigService.insert(ccc);
        	if (r > 0) {
        		result.put("flag", "1");
        		result.put("mess","添加成功" );
        	} else {
        		result.put("flag", "0");
        		result.put("mess", "添加失败");
        	}
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
    		FeedbackCheckConfig ccc) throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        int r = this.feedbackCheckConfigService.update(ccc);
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
    @RequestMapping("/deleteCheckConfig")
    public @ResponseBody
    Map<String, Object> deleteCheckConfig(HttpServletRequest request, HttpServletResponse response, String cccId)
            throws BaseException {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", cccId);
        int r = this.feedbackCheckConfigService.delete(inMap);
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
        inMap.put("roleId", cp.getRoleId());
        List<FeedbackPushCategoryPositionConfig> pushList  = this.feedbackPushCategoryPositionConfigService.checkCategoryPosition(inMap);
        if (pushList !=null && pushList.size() > 0) {
            result.put("flag", "1");
            result.put("message", pushList.get(0).getRoleId());
        } else {
            result.put("flag", "0");
	       }
	       return result;
	}
    
    /**
     * 根据员工域账号查询员工姓名
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEmpByAcccount", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getEmpByAcccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try {
        	pmap.put("empNumber", request.getParameter("empNumber"));
        	Employee emp = employeeRemoteService.findEmpByAdAccount(pmap.get("empNumber").toString().trim().toLowerCase());
        	if(emp != null){
        		data.put("empNum", emp.getEmployeeId());
        		data.put("empName", emp.getEmployeeName());
        		data.put("empAcccount", pmap.get("empNumber").toString());
        	}
        } catch (BaseException e) {
                logger.error(e.getMessage(), e);
            AppUtil.exceptionHandler(result);
        }
        result.put("result", data);
        logger.info(JsonUtil.javaObjectToJsonString(result));
        return JsonUtil.javaObjectToJsonString(result);
    }
    
    public String getEmpByAcccount1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        try {
        	pmap.put("oaNumber", request.getParameter("oaNumber"));
        	Employee emp = employeeRemoteService.findEmpByAdAccount(pmap.get("oaNumber").toString().trim().toLowerCase());
        	if(emp != null){
        		data.put("empNum", emp.getEmployeeId());
        		data.put("empName", emp.getEmployeeName());
        		data.put("empAcccount", pmap.get("oaNumber").toString());
        	}
        } catch (BaseException e) {
                logger.error(e.getMessage(), e);
            AppUtil.exceptionHandler(result);
        }
        result.put("result", data);
        return (String)data.get("empNum");
    }
}
