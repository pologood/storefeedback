package com.gome.storefeedback.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Feedback;
import com.gome.storefeedback.service.FeedbackService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.util.JsonUtil;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午3:03:36
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackServiceTest extends AbstractTransactionalSpringContextTestCase {

    @Test
    @Rollback(false)
    public void insertFeedbackTest() throws Exception {
        FeedbackService feedbackService = (FeedbackService) this.getBeanByName("feedbackService");
        Feedback feedback = new Feedback();
        feedback.setId("1");
        feedback.setQuantity(300);
        feedback.setSponsorEmployeeName("小张");
        feedbackService.insertFeedback(feedback);
    }

    @Test
    public void findByIdTest() throws Exception {
        FeedbackService feedbackService = (FeedbackService) this.getBeanByName("feedbackService");
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", "1");
        Feedback fb = feedbackService.findFeedbackById(inMap);
        System.out.println(fb.getSponsorEmployeeName());
    }

    @Test
    public void findFeedbackTest() throws Exception {
        FeedbackService feedbackService = (FeedbackService) this.getBeanByName("feedbackService");
        List<Feedback> list = feedbackService.findFeedback(null);
        for (Feedback f : list) {
            System.out.println(f.getSponsorEmployeeName());
        }
    }

    @Test
    public void updateTest() throws Exception {
        FeedbackService feedbackService = (FeedbackService) this.getBeanByName("feedbackService");
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", "1");
        Feedback fb = feedbackService.findFeedbackById(inMap);
        fb.setQuantity(500);
        feedbackService.updateFeedback(fb);
    }

    @Test
    public void deleteFeedbackTest() throws Exception {
        FeedbackService feedbackService = (FeedbackService) this.getBeanByName("feedbackService");
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", "1");
        feedbackService.deleteFeedback(inMap);
    }

    @Test
    public void findFeedbackByPageTest() throws Exception {
        FeedbackService ps = (FeedbackService) this.getBeanByName("feedbackService");
        Page page = ps.findFeedbackByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        List<Feedback> list = page.getDataList();
        for (Feedback p : list) {
            System.out.println(JsonUtil.javaObjectToJsonString(p));
        }
    }

    @Test
    public void findPageByRecieverTest() throws Exception {
        FeedbackService ps = (FeedbackService) this.getBeanByName("feedbackService");
        Page page = ps.findPageByReciever(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        List<Feedback> list = page.getDataList();
        for (Feedback p : list) {
            System.out.println(JsonUtil.javaObjectToJsonString(p));
        }
    }

    @Test
    public void findPageBySponsorTest() throws Exception {
        FeedbackService ps = (FeedbackService) this.getBeanByName("feedbackService");
        Page page = ps.findPageBySponsor(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        List<Feedback> list = page.getDataList();
        for (Feedback p : list) {
            System.out.println(JsonUtil.javaObjectToJsonString(p));
        }
    }
}
