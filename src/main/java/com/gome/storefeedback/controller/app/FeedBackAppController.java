package com.gome.storefeedback.controller.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.entity.LackCategory;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.FeedbackInsertSender;
import com.gome.storefeedback.jms.FeedbackMessageSender;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackService;
import com.gome.storefeedback.service.LackCategoryService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 缺货记录反馈 app接口
 * 
 * @author Gong.ZhiBin
 * @date 2015年3月4日下午1:21:25
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/feedBackApp")
public class FeedBackAppController {
    private static final Logger logger = LoggerFactory.getLogger(FeedBackAppController.class);
    @Resource
    private EmployeeRemoteService employeeRemoteService;
    @Resource
    private FeedbackMessageSender feedbackMessageSender;
    @Resource
    private LogMessageSender logMessageSender;
    @Resource
    private FeedbackService feedbackService;
    @Resource
    private LackCategoryService lackCategoryService;
    @Resource
    private FeedbackInsertSender feedbackInsertSender;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 根据接收人获取缺货记录反馈
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findListByReciever")
    public @ResponseBody
    String findListByReciever(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap,//
                        "startTime", //
                        "storeCode",//
                        "storeName",//
                        "goodsCode",//
                        "goodsName");
                if (pmap.containsKey("endTime")) {
                    if (pmap.get("endTime") != null && pmap.get("endTime").toString().trim().length() > 0) {
                        Date date = new Date();
                        try {
                            date = sdf.parse(pmap.get("endTime").toString().trim());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        inMap.put("endTime", sdf.format(new Date(date.getTime() + 1 * 24 * 60 * 60 * 1000)));
                    }
                }
                if (pmap.containsKey("hasReturn")) {
                    if (pmap.get("hasReturn") != null) {
                        inMap.put("hasReturn", Integer.parseInt(pmap.get("hasReturn").toString()));
                    }
                }
                inMap.put("feedbackPersonId", pmap.get("storeFeedbackUserId"));
                Page page = this.feedbackService.findPageByReciever(inMap, AppUtil.getPaginationParameters(pmap));
                AppUtil.succussHandler(page, result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query Feedback Page error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 根据反馈人获取缺货记录反馈
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findFeedBacks")
    public @ResponseBody
    String findFeedBacks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "startTime");
                inMap.put("sponsorId", pmap.get("storeFeedbackUserId"));

                if (pmap.containsKey("endTime")) {
                    if (pmap.get("endTime") != null && pmap.get("endTime").toString().trim().length() > 0) {
                        Date date = new Date();
                        try {
                            date = sdf.parse(pmap.get("endTime").toString().trim());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        inMap.put("endTime", sdf.format(new Date(date.getTime() + 1 * 24 * 60 * 60 * 1000)));
                    }
                }
                if (pmap.containsKey("hasReturn")) {
                    if (pmap.get("hasReturn") != null) {
                        inMap.put("hasReturn", Integer.parseInt(pmap.get("hasReturn").toString()));
                    }
                }

                Page page = this.feedbackService.findFeedbackByPage(inMap, AppUtil.getPaginationParameters(pmap));
                AppUtil.succussHandler(page, result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query Feedback Page error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 根据ID获取缺货记录反馈
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findFeedBacksById")
    public @ResponseBody
    String findFeedBacksById(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "id");
                // Feedback feedback =
                // this.feedbackService.findFeedbackById(inMap);
                // if (null != feedback) {
                // result.put("result", feedback);
                // } else {
                // result.put("result", AppUtil.DATA_ENTITY_NULL);
                // }
                Map<String, Object> returnMap = new HashMap<String, Object>();
                Page page = this.feedbackService.findFeedbackByPage(inMap, new PaginationParameters());
                if (page != null && page.getTotalSize() > 0) {
                    returnMap = (Map<String, Object>) page.getDataList().get(0);
                    result.put("result", returnMap);
                } else {
                    result.put("result", AppUtil.DATA_ENTITY_NULL);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query Feedback error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 根据 主键 删除缺货记录反馈
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody
    String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "id");
                this.feedbackService.deleteFeedback(inMap);
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                throw new BaseException("delete Feedback error", e);
            } finally {
                System.out.println(JsonUtil.javaObjectToJsonString(result));
                return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            }
        } else {
            System.out.println(JsonUtil.javaObjectToJsonString(result));
            return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }

    /**
     * 更新缺货记录反馈
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    public @ResponseBody
    String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                pmap.put("sponsorId", pmap.get("storeFeedbackUserId"));
                pmap.put("sponsorCompanyId", pmap.get("companyId"));
                pmap.put("sponsorEmployeeId", pmap.get("employeeId"));
                pmap.put("sponsorEmployeeName", pmap.get("employeeName"));
                Feedback feedback = map2Feedback(pmap);
                this.feedbackService.updateFeedback(feedback);
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                throw new BaseException("update Feedback error", e);
            } finally {
                return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            }
        } else {
            return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }

    /**
     * 保存缺货记录反馈 异步
     * 
     * 如果要用同步方式
     *   本类方法insert2MQ的注解 @RequestMapping(value = "/insert2")
     *   本类方法insert        的注解 @RequestMapping(value = "/insert")
     * 如果要用异步方式
     *   本类方法insert2MQ的注解 @RequestMapping(value = "/insert")
     *   本类方法insert        的注解 @RequestMapping(value = "/insert2")
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insert")
    public @ResponseBody
    String insert2MQ(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                pmap.put("sponsorId", pmap.get("storeFeedbackUserId"));
                pmap.put("sponsorCompanyId", pmap.get("companyId"));
                pmap.put("sponsorEmployeeId", pmap.get("employeeId"));
                pmap.put("sponsorEmployeeName", pmap.get("employeeName"));
                Feedback feedback = map2Feedback(pmap);
                feedback.setId(UUIDUtil.getUUID());
                this.feedbackInsertSender.send(JsonUtil.javaObjectToJsonString(feedback));
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
            } finally {
                System.out.println(JsonUtil.javaObjectToJsonString(result));
                return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            }
        } else {
            System.out.println(JsonUtil.javaObjectToJsonString(result));
            return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }

    /**
     * 保存缺货记录反馈 同步
     * 
     * 如果要用同步方式
     *   本类方法insert2MQ的注解 @RequestMapping(value = "/insert2")
     *   本类方法insert        的注解 @RequestMapping(value = "/insert")
     * 如果要用异步方式
     *   本类方法insert2MQ的注解 @RequestMapping(value = "/insert")
     *   本类方法insert        的注解 @RequestMapping(value = "/insert2")
     *   
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insert2")
    public @ResponseBody
    String insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                pmap.put("sponsorId", pmap.get("storeFeedbackUserId"));
                pmap.put("sponsorCompanyId", pmap.get("companyId"));
                pmap.put("sponsorEmployeeId", pmap.get("employeeId"));
                pmap.put("sponsorEmployeeName", pmap.get("employeeName"));
                Feedback feedback = map2Feedback(pmap);
                feedback.setId(UUIDUtil.getUUID());
                this.feedbackService.insertFeedback(feedback);
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                throw new BaseException("insert Feedback error", e);
            } finally {
                System.out.println(JsonUtil.javaObjectToJsonString(result));
                return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            }
        } else {
            System.out.println(JsonUtil.javaObjectToJsonString(result));
            return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }

    /**
     * 获取缺货类型
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findLackCategory")
    public @ResponseBody
    String findLackCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                List<LackCategory> lackCategoryList = this.lackCategoryService.findLackCategory(null);
                if (lackCategoryList != null) {
                    List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
                    for (LackCategory item : lackCategoryList) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("keyName", item.getCategoryName());
                        map.put("keyValue", item.getCategoryCode());
                        returnList.add(map);
                    }
                    result.put("result", returnList);
                } else {
                    result.put("result", AppUtil.DATA_ENTITY_NULL);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query lackCategory error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    private static Feedback map2Feedback(Map<String, Object> pmap) {
        Feedback feedback = new Feedback();
        feedback.setCreateTime(new Date());
        feedback.setBrandCode(pmap.get("brandCode").toString());
        feedback.setGoodsCode(pmap.get("goodsCode").toString());
        feedback.setLackCategory(pmap.get("lackCategory").toString());
        feedback.setFirstCategory(pmap.get("firstCategory").toString());
        feedback.setSecondCategory(pmap.get("secondCategory").toString());
        feedback.setSponsorId(pmap.get("sponsorId").toString());
        feedback.setSponsorCompanyId(pmap.get("sponsorCompanyId").toString());
        feedback.setSponsorEmployeeId(pmap.get("sponsorEmployeeId").toString());
        feedback.setSponsorEmployeeName(pmap.get("sponsorEmployeeName").toString());
        feedback.setHasReturn(0);
        if (pmap.get("storeCode") != null) {
            feedback.setStoreCode(pmap.get("storeCode").toString());
        }
        if (pmap.get("anticipatedDatesSoldOut") != null) {
            String dateString = pmap.get("anticipatedDatesSoldOut").toString();
            try {
                Date parse = sdf.parse(dateString);
                feedback.setAnticipatedDatesSoldOut(parse.getTime() + "");
            } catch (ParseException e) {
                feedback.setAnticipatedDatesSoldOut(dateString);
            }
        }
        if (pmap.get("quantity") != null) {
            feedback.setQuantity(Integer.parseInt(pmap.get("quantity").toString()));
        }
        if (pmap.get("saleOutDate") != null) {
            feedback.setSaleOutDate(Integer.parseInt(pmap.get("saleOutDate").toString()));
        }
        if (pmap.get("id") != null) {
            feedback.setId(pmap.get("id").toString());
        }
        System.out.println(feedback.getAnticipatedDatesSoldOut());
        return feedback;
    }
}
