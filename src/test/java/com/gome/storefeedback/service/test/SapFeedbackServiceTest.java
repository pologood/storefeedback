package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.SapFeedback;
import com.gome.storefeedback.service.SapFeedbackService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackService 测试
 * 
 * @author 
 * @date 2015年07月23日 09时21分29秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class SapFeedbackServiceTest extends AbstractTransactionalSpringContextTestCase{

    private SapFeedbackService sapFeedbackService = null;

    @Before
    public void init() {
        sapFeedbackService = (SapFeedbackService) this.getBeanByName("sapFeedbackService");
    }
    
    @Test
    @Rollback(false)
    public void batchSapFeedbackTest() throws Exception {
    	
    	List<SapFeedback> sapFeedbackList=new ArrayList<SapFeedback>();
        SapFeedback sapFeedback=new SapFeedback();

        sapFeedbackList.add(sapFeedback);
        
        sapFeedbackService.batchSapFeedback(sapFeedbackList);
    }
    
    @Test
    @Rollback(false)
    public void insertSapFeedbackTest() throws Exception {
        SapFeedback sapFeedback=new SapFeedback();

        sapFeedbackService.insertSapFeedback(sapFeedback);
    }

    @Test
    @Rollback(false)
    public void updateSapFeedbackTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        SapFeedback sapFeedback=sapFeedbackService.findSapFeedbackById(inMap);
        sapFeedbackService.updateSapFeedback(sapFeedback);
    }

    @Test
    @Rollback(false)
    public void deleteSapFeedbackTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        sapFeedbackService.deleteSapFeedback(inMap);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackByIdTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        SapFeedback sapFeedback=sapFeedbackService.findSapFeedbackById(inMap);
        System.out.println(sapFeedback);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackTest() throws Exception {
        List sapFeedbacks = sapFeedbackService.findSapFeedback(null);
        System.out.println(sapFeedbacks.size());
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackByPageTest() throws Exception {
        Page page = sapFeedbackService.findSapFeedbackByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
