package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gome.gsm.entity.org.Employee;
import com.gome.storefeedback.common.BusinessGlossary;
import com.gome.storefeedback.dao.FeedbackDao;
import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.entity.FeedbackMessagePushRecord;
import com.gome.storefeedback.entity.FeedbackReciever;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.jms.FeedbackMessageSender;
import com.gome.storefeedback.service.EmployeeRemoteService;
import com.gome.storefeedback.service.FeedbackRecieverService;
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
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private EmployeeRemoteService employeeRemoteService;
    @Resource
    private FeedbackMessageSender feedbackMessageSender;
    private FeedbackDao feedbackDao;
    @Resource
    private FeedbackRecieverService feedbackRecieverService;

    public FeedbackDao getFeedbackDao() {
        return feedbackDao;
    }

    @Autowired
    public void setFeedbackDao(@Qualifier("feedbackDao") FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public void insertFeedback(Feedback feedback) throws BaseException {
        Employee emp = this.employeeRemoteService.findEmployeeById(feedback.getSponsorId());
        if (emp != null && emp.getPositions() != null && emp.getPositions().size() > 0) {
            // feedback.setStoreCode(emp.getPositions().get(0).getStoreCode());
            System.out.println("storeCode :" + emp.getPositions().get(0).getPositionCode());
            if (emp.getPositions().get(0).getPositionCode().length() >= 4) {
                feedback.setStoreCode(emp.getPositions().get(0).getPositionCode().substring(0, 4));
            } else {
                throw new BaseException("获取门店编码失败");
            }
        } else {
            throw new BaseException("获取门店编码失败");
        }
        this.feedbackDao.insertFeedback(feedback);
        // List<Employee> employees =
        // this.employeeRemoteService.findBusinessEmployee(feedback.getSponsorId(),null);
        List<Employee> employees = null;
        try {
            employees = this.employeeRemoteService.findBusinessEmployee(feedback.getSponsorId(), null);
            employees = new ArrayList<Employee>(new HashSet<Employee>(employees));
        } catch (Exception e) {
            System.out.println("EMP_ID为 ：[" + feedback.getSponsorId() + "]的反馈人的反馈信息没有找到回执人。返回数据位null");
            e.printStackTrace();
        }
        List<FeedbackMessagePushRecord> sendMessages = null;
        if (employees != null && employees.size() > 0) {
            List<FeedbackReciever> feedbackRecievers = new ArrayList<FeedbackReciever>();
            sendMessages = new ArrayList<FeedbackMessagePushRecord>();
            for (Employee employee : employees) {
                feedbackRecievers.add(getFeedbackReciever(feedback, employee));
                sendMessages.add(getSendMessage(feedback, employee));
            }
            this.feedbackRecieverService.insertBatchFeedbackReciever(feedbackRecievers);
        }

        // ////////////
        // if (sendMessages == null) {
        // sendMessages = new ArrayList<FeedbackMessagePushRecord>();
        // sendMessages.add(getSendMessageTest(feedback, null));
        // }

        // //////////////////////

        // // ====测试阶段 start====
        // else {
        // employees = getEmployees();
        // List<FeedbackReciever> feedbackRecievers = new
        // ArrayList<FeedbackReciever>();
        // sendMessages=new ArrayList<FeedbackMessagePushRecord>();
        // for (Employee employee : employees) {
        // feedbackRecievers.add(getFeedbackReciever(feedback, employee));
        // sendMessages.add(getSendMessage(feedback, employee));
        // }
        // this.feedbackRecieverService.insertBatchFeedbackReciever(feedbackRecievers);
        // }
        // // ====测试阶段 end====
        if (sendMessages != null) {
            for (FeedbackMessagePushRecord sendMessage : sendMessages) {
                this.feedbackMessageSender.send(JsonUtil.javaObjectToJsonString(sendMessage));
            }
        }
    }

    @Override
    public void updateFeedback(Feedback feedback) throws BaseException {
        this.feedbackDao.updateFeedback(feedback);
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("feedbackId", feedback.getId());
        this.feedbackRecieverService.deleteFeedbackReciever(inMap);
        List<Employee> employees = null;
        try {
            employees = this.employeeRemoteService.findBusinessEmployee(feedback.getSponsorId(), null);
            employees = new ArrayList<Employee>(new HashSet<Employee>(employees));
        } catch (Exception e) {
            System.out.println("EMP_ID为 ：[" + feedback.getSponsorId() + "]的反馈人的反馈信息没有找到回执人。返回数据位null");
            e.printStackTrace();
        }
        List<FeedbackMessagePushRecord> sendMessages = null;
        if (employees != null && employees.size() > 0) {
            List<FeedbackReciever> feedbackRecievers = new ArrayList<FeedbackReciever>();
            sendMessages = new ArrayList<FeedbackMessagePushRecord>();
            for (Employee employee : employees) {
                feedbackRecievers.add(getFeedbackReciever(feedback, employee));
                sendMessages.add(getSendMessage(feedback, employee));
            }
            this.feedbackRecieverService.insertBatchFeedbackReciever(feedbackRecievers);
        }
        // // ====测试阶段 start====
        // else {
        // employees = getEmployees();
        // List<FeedbackReciever> feedbackRecievers = new
        // ArrayList<FeedbackReciever>();
        // sendMessages=new ArrayList<FeedbackMessagePushRecord>();
        // for (Employee employee : employees) {
        // feedbackRecievers.add(getFeedbackReciever(feedback, employee));
        // sendMessages.add(getSendMessage(feedback, employee));
        // }
        // this.feedbackRecieverService.insertBatchFeedbackReciever(feedbackRecievers);
        // }
        // ====测试阶段 end====
        for (FeedbackMessagePushRecord sendMessage : sendMessages) {
            this.feedbackMessageSender.send(JsonUtil.javaObjectToJsonString(sendMessage));
        }
    }

    @Override
    public void deleteFeedback(Map<String, Object> inMap) throws BaseException {
        Map<String, Object> inMap2 = new HashMap<String, Object>();
        inMap.put("feedbackId", inMap.get("id"));
        this.feedbackRecieverService.deleteFeedbackReciever(inMap2);
        this.feedbackDao.deleteFeedback(inMap);
    }

    @Override
    public Feedback findFeedbackById(Map<String, Object> inMap) throws BaseException {
        return this.feedbackDao.findFeedbackById(inMap);
    }

    @Override
    public List findFeedback(Map<String, Object> inMap) throws BaseException {
        return this.feedbackDao.findFeedback(inMap);
    }

    @Override
    public Page findFeedbackByPage(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.feedbackDao.findFeedbackByPage(inMap, param);
    }

    @Override
    public Page findPageByReciever(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.feedbackDao.findPageByReciever(inMap, param);
    }

    @Override
    public Page findPageBySponsor(Map<String, Object> inMap, PaginationParameters param) throws BaseException {
        return this.feedbackDao.findPageBySponsor(inMap, param);
    }

    @Override
    public void updateHasReturn(Map<String, Object> inMap) throws BaseException {
        this.feedbackDao.updateHasReturn(inMap);
    }

    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Employee emp1 = new Employee();
        emp1.setId("1000011");
        emp1.setEmployeeId("11");
        emp1.setEmployeeName("11Name");
        employees.add(emp1);
        Employee emp2 = new Employee();
        emp2.setId("1000022");
        emp2.setEmployeeId("22");
        emp2.setEmployeeName("22Name");
        employees.add(emp2);
        return employees;
    }

    private static FeedbackReciever getFeedbackReciever(Feedback feedback, Employee employee) {
        FeedbackReciever feedbackReciever = new FeedbackReciever();
        feedbackReciever.setFeedbackId(feedback.getId());
        feedbackReciever.setFeedbackPersonId(employee.getId());
        return feedbackReciever;
    }

    private static FeedbackMessagePushRecord getSendMessage(Feedback feedback, Employee employee) {
        FeedbackMessagePushRecord sendMessage = new FeedbackMessagePushRecord();
        sendMessage.setId(UUIDUtil.getUUID());
        sendMessage.setFeedbackId(feedback.getId());
        sendMessage.setContent("您有一条缺断货反馈");
        sendMessage.setType(BusinessGlossary.INFORM_TYPE_STORES);
        sendMessage.setTitle(BusinessGlossary.INFORM_TITLE_STORES);
        sendMessage.setNotificationId(employee.getId());
        sendMessage.setNotificationEmployeeId(employee.getEmployeeId());
        sendMessage.setNotificationEmployeeName(employee.getEmployeeName());
        sendMessage.setSendId(feedback.getSponsorId());
        sendMessage.setSendEmployeeId(feedback.getSponsorEmployeeId());
        sendMessage.setSendEmployeeName(feedback.getSponsorEmployeeName());
        sendMessage.setNotificationTime(new Date());
        return sendMessage;
    }

    private static FeedbackMessagePushRecord getSendMessageTest(Feedback feedback, Employee employee) {
        FeedbackMessagePushRecord sendMessage = new FeedbackMessagePushRecord();
        sendMessage.setId(UUIDUtil.getUUID());
        sendMessage.setFeedbackId(feedback.getId());
        sendMessage.setContent("您有一条缺断货反馈");
        sendMessage.setType(BusinessGlossary.INFORM_TYPE_STORES);
        sendMessage.setTitle(BusinessGlossary.INFORM_TITLE_STORES);
        sendMessage.setNotificationId("1000000000333");
        sendMessage.setNotificationEmployeeId("00000333");
        sendMessage.setNotificationEmployeeName("孙兰兰");
        sendMessage.setSendId(feedback.getSponsorId());
        sendMessage.setSendEmployeeId(feedback.getSponsorEmployeeId());
        sendMessage.setSendEmployeeName(feedback.getSponsorEmployeeName());
        sendMessage.setNotificationTime(new Date());
        return sendMessage;
    }
}
