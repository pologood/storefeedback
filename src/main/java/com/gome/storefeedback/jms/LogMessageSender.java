package com.gome.storefeedback.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author Yan.Kai
 * @date 2014年9月28日下午4:11:25
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class LogMessageSender {

    private static final Logger logger = LoggerFactory.getLogger(LogMessageSender.class);
    private JmsTemplate jmsTemplate;
    private Queue queue;

    /**
     * @return the jmsTemplate
     */
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    /**
     * @param jmsTemplate the jmsTemplate to set
     */
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * @return the queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * @param queue the queue to set
     */
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    /**
     * 发送消息
     * 
     * @param message
     */
    public void send(final String message) {
        this.jmsTemplate.send(this.queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        logger.info("----------发送日志消息到MQ成功-----------");
    }
}
