package com.gome.storefeedback.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class FeedbackInsertSender {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackInsertSender.class);

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void send(final String message) throws Exception {
        this.jmsTemplate.send(queue, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        logger.info("-------发送[Insert]缺断货反馈到消息服务器队列成功--" + message + "-------");
    }
}
