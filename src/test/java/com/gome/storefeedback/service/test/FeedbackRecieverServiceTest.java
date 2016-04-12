package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.FeedbackReciever;
import com.gome.storefeedback.service.FeedbackRecieverService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * FeedbackRecieverService 测试
 * 
 * @author
 * @date 2015年03月06日 15时31分19秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class FeedbackRecieverServiceTest extends AbstractTransactionalSpringContextTestCase {

    private FeedbackRecieverService feedbackRecieverService = null;

    @Before
    public void init() {
        feedbackRecieverService = (FeedbackRecieverService) this.getBeanByName("feedbackRecieverService");
    }

    @Test
    @Rollback(false)
    public void insertBatchFeedbackRecieverTest() throws Exception {
        List<FeedbackReciever> feedbackRecievers=new ArrayList<FeedbackReciever>();
        FeedbackReciever feedbackReciever = new FeedbackReciever();
        feedbackReciever.setFeedbackId("23fb4011e64a4251b16c5be9f5e22a58");
        feedbackReciever.setFeedbackPersonId("1");
        feedbackRecievers.add(feedbackReciever);
        FeedbackReciever feedbackReciever2 = new FeedbackReciever();
        feedbackReciever2.setFeedbackId("23fb4011e64a4251b16c5be9f5e22a58");
        feedbackReciever2.setFeedbackPersonId("2");
        feedbackRecievers.add(feedbackReciever2);
        feedbackRecieverService.insertBatchFeedbackReciever(feedbackRecievers);
    }
    
    @Test
    @Rollback(false)
    public void insertFeedbackRecieverTest() throws Exception {
        FeedbackReciever feedbackReciever = new FeedbackReciever();
        feedbackReciever.setFeedbackId("23fb4011e64a4251b16c5be9f5e22a58");
        feedbackReciever.setFeedbackPersonId("3");
        feedbackRecieverService.insertFeedbackReciever(feedbackReciever);
    }

    @Test
    @Rollback(false)
    public void deleteFeedbackRecieverTest() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("feedbackId", "23fb4011e64a4251b16c5be9f5e22a58");
        feedbackRecieverService.deleteFeedbackReciever(inMap);
    }

    @Test
    @Rollback(false)
    public void findFeedbackRecieverTest() throws Exception {
        List feedbackRecievers = feedbackRecieverService.findFeedbackReciever(null);
        System.out.println(feedbackRecievers.size());
    }

    @Test
    @Rollback(false)
    public void findFeedbackRecieverByPageTest() throws Exception {
        Page page = feedbackRecieverService.findFeedbackRecieverByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
