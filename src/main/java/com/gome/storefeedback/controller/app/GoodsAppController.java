package com.gome.storefeedback.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.constant.APPErrorInfo;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.GoodsBrandService;
import com.gome.storefeedback.service.GoodsCategoryService;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.web.page.page.Page;

/**
 * 品牌 品类 商品 app 接口
 * 
 * @author Gong.ZhiBin
 * @date 2015年3月4日下午1:21:42
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/goodsApp")
public class GoodsAppController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsAppController.class);
    @Resource
    private LogMessageSender logMessageSender;
    /** 商品 */
    @Resource
    private GoodsService goodsService;
    /** 品牌 */
    @Resource
    private GoodsBrandService goodsBrandService;
    /** 商品品类 */
    @Resource
    private GoodsCategoryService goodsCategoryService;

    /**
     * 获取一级品类
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findBaseCategorys")
    public @ResponseBody
    String findBaseCategorys(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, null);
                List list = this.goodsCategoryService.findBaseCategorys(inMap);
                if (list != null) {
                    result.put("result", list);
                } else {
                    result.put("result", AppUtil.DATA_LIST_NULL);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query GoodsBrand Page error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 获取二级品类
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findGoodsCategorys")
    public @ResponseBody
    String findGoodsCategorys(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, null);
                if (pmap.containsKey("classLevel")) {
                    String classLevel = pmap.get("classLevel").toString().trim();
                    if (classLevel != null && classLevel.length() > 0) {
                        if (classLevel.equals("1")) {
                            List list = this.goodsCategoryService.findBaseCategorys(null);
                            if (list != null) {
                                result.put("result", list);
                            } else {
                                result.put("result", AppUtil.DATA_LIST_NULL);
                            }
                        } else if (classLevel.equals("2")) {
                            if (pmap.containsKey("parentClassCode")) {
                                inMap.put("divisionCode", pmap.get("parentClassCode"));
                            }
                            if (pmap.containsKey("className")) {
                                inMap.put("className", pmap.get("className"));
                            }
                            Page page = this.goodsCategoryService.findAppListByPage(inMap,
                                    AppUtil.getPaginationParameters(pmap));
                            AppUtil.succussHandler(page, result);
                        }
                    }
                } else {
                    result.put("result", AppUtil.DATA_LIST_NULL);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query GoodsBrand Page error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    @RequestMapping(value = "/findSubCategorys")
    public @ResponseBody
    String findSubCategorys(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "divisionCode",//
                        "className");
                Page page = this.goodsCategoryService.findAppListByPage(inMap, AppUtil.getPaginationParameters(pmap));
                AppUtil.succussHandler(page, result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query GoodsBrand Page error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 获取品牌
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findGoodsBrands")
    public @ResponseBody
    String findGoodsBrands(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "cnText");
                Page page = this.goodsBrandService.findAppListByPage(inMap, AppUtil.getPaginationParameters(pmap));
                AppUtil.succussHandler(page, result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query GoodsBrand Page error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 获取商品
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findGoodses")
    public @ResponseBody
    String findGoodses(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, //
                        "goodsBrand",//
                        "goodsName");
                if (pmap.containsKey("categoryLevel2")) {
                    inMap.put("goodsCategory", pmap.get("categoryLevel2").toString().trim());
                }
                Page page = this.goodsService.findGoodsByPage2(inMap, AppUtil.getPaginationParameters(pmap));
                AppUtil.succussHandler(page, result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));

            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query goods Page error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }
    
    
    /**
     * 格式化 商品编码成18位 不够18位前边补0
     * @param goodsCode
     * @return
     */
    private static String getGoodsCode(String goodsCode){
        if(goodsCode.length()>=18){
            return goodsCode;
        }else{
          int count = 18- goodsCode.length();
          StringBuffer sb = new StringBuffer();
          for(int i=0;i<count;i++)
          {
              sb.append("0");
          }
          sb.append(goodsCode);
          
          return sb.toString();
        }
    }
    /**
     * 根据商品编码获取商品
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goodsInfo")
    public @ResponseBody
    String goodsInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                if(!(pmap.containsKey("goodsCode")&&pmap.get("goodsCode")!=null&&pmap.get("goodsCode").toString().length()>0)){
                    result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E10012);
                    result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M10012);
                    return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
                }
                
                pmap.put("goodsCode",getGoodsCode(pmap.get("goodsCode").toString().trim()));
                
                Map<String, Object> inMap = AppUtil.getInMap(pmap, //
                        "goodsCode",//
                        "goodsName");

                Map<String, String> goodInfo = this.goodsService.goodInfo(inMap);
                if(goodInfo==null){
                    //商品不存在
                    result.put(APPErrorInfo.ERRORCODE, APPErrorInfo.E10011);
                    result.put(APPErrorInfo.ERRORMSG, APPErrorInfo.M10011);
                }else{
                    result.put("result", goodInfo);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }
}
