package com.gome.storefeedback.controller.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.storefeedback.entity.FeedbackRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.FeedbackRecordInsertSender;
import com.gome.storefeedback.jms.LogMessageSender;
import com.gome.storefeedback.service.FeedbackRecordService;
import com.gome.storefeedback.util.AppUtil;
import com.gome.storefeedback.util.GzipAESUtil;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.LogUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;

/**
 * 反馈信息记录
 * 
 * @author Gong.ZhiBin
 * @date 2015年3月4日下午1:20:42
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Controller
@RequestMapping("/feedBackRecordApp")
public class FeedBackRecordAppController {
    private static final Logger logger = LoggerFactory.getLogger(FeedBackRecordAppController.class);
    @Resource
    private LogMessageSender logMessageSender;
    @Resource
    private FeedbackRecordService feedbackRecordService;
    @Resource
    private FeedbackRecordInsertSender feedbackRecordInsertSender;

    /**
     * 根据用户获取反馈信息记录
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findListByPK")
    public @ResponseBody
    String findListByPK(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "feedbackId");
                Page page = this.feedbackRecordService.findFeedbackRecordByPage(inMap,
                        AppUtil.getPaginationParameters(pmap));
                AppUtil.succussHandler(page, result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query FeedBackRecord Page error",e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 根据用户获取反馈信息记录
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findFeedBackRecords")
    public @ResponseBody
    String findFeedBackRecords(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();

        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "feedbackId");
                inMap.put("feedbackPersonId", pmap.get("storeFeedbackUserId"));
                Page page = this.feedbackRecordService.findFeedbackRecordByPage(inMap,
                        AppUtil.getPaginationParameters(pmap));
                AppUtil.succussHandler(page, result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query FeedBackRecord Page error",e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 根据ID获取反馈信息记录
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findFeedBackRecordById")
    public @ResponseBody
    String findFeedBacksRecordById(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if (AppUtil.requestHandler(request, pmap, result)) {
            try {
                Map<String, Object> inMap = AppUtil.getInMap(pmap, "id");
                FeedbackRecord entity = this.feedbackRecordService.findFeedbackRecordById(inMap);
                if (entity != null) {
                    result.put("result", entity);
                } else {
                    result.put("result", AppUtil.DATA_ENTITY_NULL);
                }
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                // throw new BaseException("query FeedbackRecord error", e);
            }
        }
        System.out.println(JsonUtil.javaObjectToJsonString(result));
        return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
    }

    /**
     * 根据 主键 删除反馈信息记录
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
                this.feedbackRecordService.deleteFeedbackRecord(inMap);
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                throw new BaseException("delete FeedbackRecord error", e);
            } finally {
                return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            }
        } else {
            return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }

    /**
     * 更新反馈信息记录
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
                FeedbackRecord feedbackRecord = new FeedbackRecord();
                feedbackRecord.setFeedbackContent(pmap.get("feedbackContent").toString());
                feedbackRecord.setFeedbackId(pmap.get("feedbackId").toString());
                feedbackRecord.setFeedbackPersonEmployeeId(pmap.get("employeeId").toString());
                feedbackRecord.setFeedbackPersonEmployeeName(pmap.get("employeeName").toString());
                feedbackRecord.setFeedbackPersonId(pmap.get("storeFeedbackUserId").toString());
                feedbackRecord.setFeedbackTime(new Date());
                feedbackRecord.setId(pmap.get("id").toString());
                this.feedbackRecordService.updateFeedbackRecord(feedbackRecord);
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                throw new BaseException("update FeedbackRecord error", e);
            } finally {
                return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            }
        } else {
            return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }

    /**
     * 保存反馈信息记录 异步
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
                FeedbackRecord feedbackRecord = new FeedbackRecord();
                feedbackRecord.setFeedbackContent(pmap.get("feedbackContent").toString());
                feedbackRecord.setFeedbackId(pmap.get("feedbackId").toString());
                feedbackRecord.setFeedbackPersonEmployeeId(pmap.get("employeeId").toString());
                feedbackRecord.setFeedbackPersonEmployeeName(pmap.get("employeeName").toString());
                feedbackRecord.setFeedbackPersonId(pmap.get("storeFeedbackUserId").toString());
                feedbackRecord.setFeedbackTime(new Date());
                feedbackRecord.setId(UUIDUtil.getUUID());
                this.feedbackRecordInsertSender.send(JsonUtil.javaObjectToJsonString(feedbackRecord));
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
     * 保存反馈信息记录 同步
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
                FeedbackRecord feedbackRecord = new FeedbackRecord();
                feedbackRecord.setFeedbackContent(pmap.get("feedbackContent").toString());
                feedbackRecord.setFeedbackId(pmap.get("feedbackId").toString());
                feedbackRecord.setFeedbackPersonEmployeeId(pmap.get("employeeId").toString());
                feedbackRecord.setFeedbackPersonEmployeeName(pmap.get("employeeName").toString());
                feedbackRecord.setFeedbackPersonId(pmap.get("storeFeedbackUserId").toString());
                feedbackRecord.setFeedbackTime(new Date());
                feedbackRecord.setId(UUIDUtil.getUUID());
                this.feedbackRecordService.insertFeedbackRecord(feedbackRecord);
                AppUtil.succussHandler(result);
                logMessageSender.send(LogUtil.getLogJsonString(pmap, this.getClass().getName(),
                        JsonUtil.javaObjectToJsonString(result)));
            } catch (BaseException e) {
                logger.error(e.getMessage(), e);
                AppUtil.exceptionHandler(result);
                throw new BaseException("insert FeedbackRecord error", e);
            } finally {
                System.out.println(JsonUtil.javaObjectToJsonString(result));
                return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
            }
        } else {
            System.out.println(JsonUtil.javaObjectToJsonString(result));
            return GzipAESUtil.compressThenEncryptAES(JsonUtil.javaObjectToJsonString(result));
        }
    }
}
