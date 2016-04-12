package com.gome.storefeedback.jms;

import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.entity.MQErrorInfo;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackService;
import com.gome.storefeedback.service.MQErrorInfoService;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.util.UUIDUtil;

public class FeedbackInsertListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackInsertListener.class);

    @Resource
    public FeedbackService feedbackService;
    @Resource
    public MQErrorInfoService mQErrorInfoService;

    @Override
    public void onMessage(Message message) {
        String jsonStr = null;
        try {
            if (message instanceof TextMessage) {
                TextMessage receiveMessage = (TextMessage) message;
                jsonStr = receiveMessage.getText();
                logger.info("----接收到[Insert]缺断货反馈：-- " + jsonStr + "--------------------");
                // 推送消息 http请求 如果成功 则保存 一下消息内容
                Feedback entity = (Feedback) JsonUtil.getJsonStringToObject(jsonStr, Feedback.class);
                this.feedbackService.insertFeedback(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MQErrorInfo mQErrorInfo = new MQErrorInfo();
            mQErrorInfo.setId(UUIDUtil.getUUID());
            mQErrorInfo.setCreateTime(new Date());
            mQErrorInfo.setContent(jsonStr);
            mQErrorInfo.setType("feedback");
            mQErrorInfo.setError(e.toString());
            try {
                this.mQErrorInfoService.insertMQErrorInfo(mQErrorInfo);
                logger.info("----[Insert]缺断货反馈失败，并记录到失败记录[MQErrorInfo]中成功：--\nFeedback: " + jsonStr
                        + "--------------------");
            } catch (BaseException e1) {
                e1.printStackTrace();
                try {
                    logger.info("----[Insert]缺断货反馈失败，并记录到失败记录[MQErrorInfo]中失败：-- \nFeedback:" + jsonStr
                            + "\nMQErrorInfo:" + JsonUtil.javaObjectToJsonString(mQErrorInfo) + "--------------------");
                } catch (BaseException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
