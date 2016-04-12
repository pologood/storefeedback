package com.gome.storefeedback.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 导航控制器
 * 
 * @author Zhang.Mingji
 * @date 2015年1月26日上午11:03:31
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/navigation")
public class NavigationController {

    /**
     * 去主页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToMainPage")
    public ModelAndView goToMainPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("common/main");
        return mv;
    }

    /**
     * 去报表主页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToMainPagenew")
    public ModelAndView goToMainPagenew(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("appmain/main");
        return mv;
    }
    
    /**
     * 去商品品类页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToGoodsCategoryPage")
    public ModelAndView goToGoodsCategoryPage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("goods/goodsCategoryList");
        return mv;
    }

    /**
     * 去商品品牌页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToGoodsBrandPage")
    public ModelAndView goToGoodsBrandPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("goods/goodsBrandList");
        return mv;
    }

    /**
     * 去商品页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToGoodsPage")
    public ModelAndView goToGoodsPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("goods/goodsList");
        return mv;
    }

    /**
     * 去缺断货反馈页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToFeedbackPage")
    public ModelAndView goToFeedbackPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/feedbackList");
        return mv;
    }

    /**
     * 去消息管理页面
     * 
     * @param request
     * @param response
     * @return 视图
     * @throws Exception
     */
    @RequestMapping("/goToMessagePage")
    public ModelAndView goToMessagePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("message/messageManager");
        return mv;
    }
    /**
     * 去门店管理页面
     * 
     * @param request
     * @param response
     * @return 视图
     * @throws Exception
     */
    @RequestMapping("/goToStorePage")
    public ModelAndView goToStorePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("store/storeManager");
    	return mv;
    }
    
    /**
     * 推送配置页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToGoodsCategoryConfigPage")
    public ModelAndView goToGoodsCategoryConfigPage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("goods/goodsCategoryConfigList");
        return mv;
    }
    
    /**
     * 缺断货推送关系维护页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToFeedbackPushConfigPage")
    public ModelAndView goToFeedbackPushConfigPage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("feedback/feedbackPushConfigList");
        return mv;
    }
    
    /**
     * 缺断货考核人员关系维护页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToFeedbackCheckConfigPage")
    public ModelAndView goToFeedbackCheckConfigPage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("feedback/feedbackCheckConfigList");
        return mv;
    }
    
    /**
     * 缺断货推送关系维护页面
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/goToResultNoReasonPage")
    public ModelAndView goToResultNoReasonPage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("feedback/resultNoReasonList");
        return mv;
    }
}
