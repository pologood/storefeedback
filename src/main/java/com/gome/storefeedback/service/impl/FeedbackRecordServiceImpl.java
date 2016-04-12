package com.gome.storefeedback.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.dao.FeedbackRecordDao;
import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.entity.FeedbackRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.FeedbackMessageSender;
import com.gome.storefeedback.service.FeedbackRecordService;
import com.gome.storefeedback.service.FeedbackService;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午3:05:03
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("feedbackRecordService")
public class FeedbackRecordServiceImpl implements FeedbackRecordService {

    @Resource
    private FeedbackService feedbackService;
    @Resource
    private FeedbackMessageSender feedbackMessageSender;
    private FeedbackRecordDao feedbackRecordDao;

    public FeedbackRecordDao getFeedbackRecordDao() {
        return feedbackRecordDao;
    }

    @Autowired
    public void setFeedbackRecordRecordDao(@Qualifier("feedbackRecordDao") FeedbackRecordDao feedbackRecordDao) {
        this.feedbackRecordDao = feedbackRecordDao;
    }

    @Override
    public void insertFeedbackRecord(FeedbackRecord feedbackRecord) throws BaseException {
        this.feedbackRecordDao.insertFeedbackRecord(feedbackRecord);
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", feedbackRecord.getFeedbackId());
        inMap.put("option", "insert");
        feedbackService.updateHasReturn(inMap);
        Feedback feedback = feedbackService.findFeedbackById(inMap);
//        feedbackMessageSender.send(JsonUtil.javaObjectToJsonString(getSendMessageTest(feedback, feedbackRecord)));
        feedbackMessageSender.send(JsonUtil.javaObjectToJsonString(getSendMessage(feedback, feedbackRecord)));
    }


    @Override
    public void updateFeedbackRecord(FeedbackRecord feedbackRecord) throws BaseException {
        this.feedbackRecordDao.updateFeedbackRecord(feedbackRecord);
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", feedbackRecord.getFeedbackId());
        Feedback feedback = feedbackService.findFeedbackById(inMap);
        feedbackMessageSender.send(JsonUtil.javaObjectToJsonString(getSendMessage(feedback, feedbackRecord)));
    
    }

    @Override
    public void deleteFeedbackRecord(Map<String, Object> inMap) throws BaseException {
        FeedbackRecord record = this.findFeedbackRecordById(inMap);
        this.feedbackRecordDao.deleteFeedbackRecord(inMap);
        String feedbackId = record.getFeedbackId();
        inMap.put("id", feedbackId);
        Feedback feedback = feedbackService.findFeedbackById(inMap);
        if (feedback != null) {
            if (feedback.getHasReturn() >= 1) {
                inMap.put("option", "delete");
                feedbackService.updateHasReturn(inMap);
            }
        }
    }

    @Override
    public FeedbackRecord findFeedbackRecordById(Map<String, Object> inMap) throws BaseException {
        return this.feedbackRecordDao.findFeedbackRecordById(inMap);
    }

    @Override
    public List findFeedbackRecord(Map<String, Object> inMap) throws BaseException {
        return this.feedbackRecordDao.findFeedbackRecord(inMap);
    }

    @Override
    public Page findFeedbackRecordByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.feedbackRecordDao.findFeedbackRecordByPage(inMap, param);
    }


    private static FeedbackMessagePushRecord getSendMessage(Feedback feedback, FeedbackRecord feedbackRecord) {
        FeedbackMessagePushRecord sendMessage = new FeedbackMessagePushRecord();
        sendMessage.setId(UUIDUtil.getUUID());
        sendMessage.setFeedbackId(feedback.getId());
        sendMessage.setContent("您有一条缺断货回执:[" + feedbackRecord.getFeedbackContent() + "]");
        sendMessage.setType(BusinessGlossary.INFORM_TYPE_BUSINESS);
        sendMessage.setTitle(BusinessGlossary.INFORM_TITLE_BUSINESS);
        sendMessage.setNotificationId(feedback.getSponsorId());
        sendMessage.setNotificationEmployeeId(feedback.getSponsorEmployeeId());
        sendMessage.setNotificationEmployeeName(feedback.getSponsorEmployeeName());
        sendMessage.setSendId(feedbackRecord.getFeedbackPersonId());
        sendMessage.setSendEmployeeId(feedbackRecord.getFeedbackPersonEmployeeId());
        sendMessage.setSendEmployeeName(feedbackRecord.getFeedbackPersonEmployeeName());
        sendMessage.setNotificationTime(new Date());
        return sendMessage;
    }
    
    private static FeedbackMessagePushRecord getSendMessageTest(Feedback feedback, FeedbackRecord feedbackRecord) {
        FeedbackMessagePushRecord sendMessage = new FeedbackMessagePushRecord();
        sendMessage.setId(UUIDUtil.getUUID());
        sendMessage.setFeedbackId(feedback.getId());
        sendMessage.setContent("您有一条缺断货回执:[" + feedbackRecord.getFeedbackContent() + "]");
        sendMessage.setType(BusinessGlossary.INFORM_TYPE_BUSINESS);
        sendMessage.setTitle(BusinessGlossary.INFORM_TITLE_BUSINESS);
        sendMessage.setNotificationId("1000000000333");
        sendMessage.setNotificationEmployeeId("00000333");
        sendMessage.setNotificationEmployeeName("孙兰兰");
        sendMessage.setSendId(feedbackRecord.getFeedbackPersonId());
        sendMessage.setSendEmployeeId(feedbackRecord.getFeedbackPersonEmployeeId());
        sendMessage.setSendEmployeeName(feedbackRecord.getFeedbackPersonEmployeeName());
        sendMessage.setNotificationTime(new Date());
        return sendMessage;
    }
}
