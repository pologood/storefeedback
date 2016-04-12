package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.SapFeedbackPush;
import com.gome.storefeedback.service.SapFeedbackPushService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackPushService 测试
 * 
 * @author 
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class SapFeedbackPushServiceTest extends AbstractTransactionalSpringContextTestCase{

    private SapFeedbackPushService sapFeedbackPushService = null;

    @Before
    public void init() {
        sapFeedbackPushService = (SapFeedbackPushService) this.getBeanByName("sapFeedbackPushService");
    }
    
    @Test
    @Rollback(false)
    public void batchSapFeedbackPushTest() throws Exception {
    	
    	List<SapFeedbackPush> sapFeedbackPushList=new ArrayList<SapFeedbackPush>();
        SapFeedbackPush sapFeedbackPush=new SapFeedbackPush();

        sapFeedbackPushList.add(sapFeedbackPush);
        
        sapFeedbackPushService.batchSapFeedbackPush(sapFeedbackPushList);
    }
    
    @Test
    @Rollback(false)
    public void insertSapFeedbackPushTest() throws Exception {
        SapFeedbackPush sapFeedbackPush=new SapFeedbackPush();

        sapFeedbackPushService.insertSapFeedbackPush(sapFeedbackPush);
    }

    @Test
    @Rollback(false)
    public void updateSapFeedbackPushTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        SapFeedbackPush sapFeedbackPush=sapFeedbackPushService.findSapFeedbackPushById(inMap);
        sapFeedbackPushService.updateSapFeedbackPush(sapFeedbackPush);
    }

    @Test
    @Rollback(false)
    public void deleteSapFeedbackPushTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        sapFeedbackPushService.deleteSapFeedbackPush(inMap);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackPushByIdTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        SapFeedbackPush sapFeedbackPush=sapFeedbackPushService.findSapFeedbackPushById(inMap);
        System.out.println(sapFeedbackPush);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackPushTest() throws Exception {
        List sapFeedbackPushs = sapFeedbackPushService.findSapFeedbackPush(null);
        System.out.println(sapFeedbackPushs.size());
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackPushByPageTest() throws Exception {
        Page page = sapFeedbackPushService.findSapFeedbackPushByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
