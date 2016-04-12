package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.SapFeedbackResultOrder;
import com.gome.storefeedback.service.SapFeedbackResultOrderService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackResultOrderService 测试
 * 
 * @author 
 * @date 2015年07月23日 19时07分04秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class SapFeedbackResultOrderServiceTest extends AbstractTransactionalSpringContextTestCase{

    private SapFeedbackResultOrderService sapFeedbackResultOrderService = null;

    @Before
    public void init() {
        sapFeedbackResultOrderService = (SapFeedbackResultOrderService) this.getBeanByName("sapFeedbackResultOrderService");
    }
    
    @Test
    @Rollback(false)
    public void batchSapFeedbackResultOrderTest() throws Exception {
    	
    	List<SapFeedbackResultOrder> sapFeedbackResultOrderList=new ArrayList<SapFeedbackResultOrder>();
        SapFeedbackResultOrder sapFeedbackResultOrder=new SapFeedbackResultOrder();
        sapFeedbackResultOrder.setId("1");
        sapFeedbackResultOrder.setOrderId("4800000765");
        sapFeedbackResultOrderList.add(sapFeedbackResultOrder);
        SapFeedbackResultOrder sapFeedbackResultOrder2=new SapFeedbackResultOrder();
        sapFeedbackResultOrder2.setId("2");
        sapFeedbackResultOrder2.setOrderId("4800000766");
        sapFeedbackResultOrderList.add(sapFeedbackResultOrder2);
        sapFeedbackResultOrderService.batchSapFeedbackResultOrder(sapFeedbackResultOrderList);
    }
    
    @Test
    @Rollback(false)
    public void insertSapFeedbackResultOrderTest() throws Exception {
        SapFeedbackResultOrder sapFeedbackResultOrder=new SapFeedbackResultOrder();

        sapFeedbackResultOrderService.insertSapFeedbackResultOrder(sapFeedbackResultOrder);
    }

    @Test
    @Rollback(false)
    public void updateSapFeedbackResultOrderTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        SapFeedbackResultOrder sapFeedbackResultOrder=sapFeedbackResultOrderService.findSapFeedbackResultOrderById(inMap);
        sapFeedbackResultOrderService.updateSapFeedbackResultOrder(sapFeedbackResultOrder);
    }

    @Test
    @Rollback(false)
    public void deleteSapFeedbackResultOrderTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        sapFeedbackResultOrderService.deleteSapFeedbackResultOrder(inMap);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackResultOrderByIdTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        SapFeedbackResultOrder sapFeedbackResultOrder=sapFeedbackResultOrderService.findSapFeedbackResultOrderById(inMap);
        System.out.println(sapFeedbackResultOrder);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackResultOrderTest() throws Exception {
        List sapFeedbackResultOrders = sapFeedbackResultOrderService.findSapFeedbackResultOrder(null);
        System.out.println(sapFeedbackResultOrders.size());
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackResultOrderByPageTest() throws Exception {
        Page page = sapFeedbackResultOrderService.findSapFeedbackResultOrderByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
