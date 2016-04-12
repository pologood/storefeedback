package com.gome.storefeedback.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gome.storefeedback.util.DataBack;
import com.gome.storefeedback.util.DataBackUtil;
import com.gome.storefeedback.util.JsonUtil;

public class DataBackListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(DataBackListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage receiveMessage = (TextMessage) message;
                String jsonStr = receiveMessage.getText();
                logger.info("----接收到Feedback消息： " + jsonStr + "--------------------");
                DataBack feedback = (DataBack) JsonUtil.getJsonStringToObject(jsonStr, DataBack.class);
                DataBackUtil.response2XML(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
