package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.SapOrder;
import com.gome.storefeedback.service.SapOrderService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapOrderService 测试
 * 
 * @author
 * @date 2015年07月20日 15时38分36秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class SapOrderServiceTest extends AbstractTransactionalSpringContextTestCase {

    private SapOrderService sapOrderService = null;

    @Before
    public void init() {
        sapOrderService = (SapOrderService) this.getBeanByName("sapOrderService");
    }

    @Test
    @Rollback(false)
    public void piTest() throws Exception {
        List<SapOrder> sapOrders = new ArrayList<SapOrder>();

        SapOrder sapOrder5 = new SapOrder();
        sapOrder5.setOrderId("orderId5");
        sapOrder5.setOrderContent("oc5");
        sapOrder5.setGoodsCode("goodsCode");
        sapOrder5.setOrderNum(8L);
        sapOrder5.setCreateTime(new Date());
        sapOrder5.setGoodsCnText("goodsCnText");
        sapOrder5.setLastReceiveDate("20150909");
        sapOrder5.setOnTheRoadNum(3L);
        sapOrder5.setOrderDate("20150721");
        sapOrder5.setOrderFlag("1");
        sapOrder5.setOrderToNum(22L);
        sapOrder5.setOrderType("tt");
        sapOrder5.setUpdateTime(new Date());
        sapOrder5.setStockTypeName("44");
        sapOrder5.setStockTypeId("44");
        sapOrder5.setPlaceName("placeName");
        sapOrder5.setPlaceId("pi");
        sapOrder5.setPlanDate("20150909");
        sapOrders.add(sapOrder5);

        SapOrder sapOrder2 = new SapOrder();
        sapOrder2.setOrderId("orderId2");
        sapOrder2.setOrderContent("oc2");
        sapOrder2.setGoodsCode("goodsCode");
        sapOrder2.setOrderNum(8L);
        sapOrders.add(sapOrder2);

        SapOrder sapOrder00 = new SapOrder();
        sapOrder00.setOrderId("orderId");
        sapOrder00.setOrderContent("oc00");
        sapOrder00.setGoodsCode("goodsCode");
        sapOrder00.setOrderNum(8L);
        sapOrders.add(sapOrder00);

        SapOrder sapOrder = new SapOrder();
        sapOrder.setOrderId("orderId0");
        sapOrder.setOrderContent("oc0");
        sapOrder.setGoodsCode("goodsCode");
        sapOrder.setOrderNum(8L);
        sapOrder.setCreateTime(new Date());
        sapOrder.setGoodsCnText("goodsCnText");
        sapOrder.setLastReceiveDate("20150909");
        sapOrder.setOnTheRoadNum(3L);
        sapOrder.setOrderDate("20150721");
        sapOrder.setOrderFlag("1");
        sapOrder.setOrderToNum(22L);
        sapOrder.setOrderType("tt");
        sapOrder.setUpdateTime(new Date());
        sapOrder.setStockTypeName("44");
        sapOrder.setStockTypeId("44");
        sapOrder.setPlaceName("placeName");
        sapOrder.setPlaceId("pi");
        sapOrder.setPlanDate("20150909");
        sapOrders.add(sapOrder);

        sapOrderService.pi(sapOrders);
    }

    @Test
    @Rollback(false)
    public void batchSapOrderTest() throws Exception {
        List<SapOrder> sapOrders = new ArrayList<SapOrder>();

        SapOrder sapOrder = new SapOrder();
        sapOrder.setOrderId("orderId0");
        sapOrder.setOrderContent("oc");
        sapOrder.setGoodsCode("goodsCode");
        sapOrder.setOrderNum(8L);
        sapOrder.setCreateTime(new Date());
        sapOrder.setGoodsCnText("goodsCnText");
        sapOrder.setLastReceiveDate("20150909");
        sapOrder.setOnTheRoadNum(3L);
        sapOrder.setOrderDate("20150721");
        sapOrder.setOrderFlag("1");
        sapOrder.setOrderToNum(22L);
        sapOrder.setOrderType("tt");
        sapOrder.setUpdateTime(new Date());
        sapOrder.setStockTypeName("44");
        sapOrder.setStockTypeId("44");
        sapOrder.setPlaceName("placeName");
        sapOrder.setPlaceId("pi");
        sapOrder.setPlanDate("20150909");
        sapOrders.add(sapOrder);

        SapOrder sapOrder2 = new SapOrder();
        sapOrder2.setOrderId("orderId2");
        sapOrder2.setOrderContent("oc");
        sapOrder2.setGoodsCode("goodsCode");
        sapOrder.setOrderNum(8L);
        sapOrders.add(sapOrder2);
        sapOrderService.batchSapOrder(sapOrders);
    }

    @Test
    @Rollback(false)
    public void insertSapOrderTest() throws Exception {
        SapOrder sapOrder = new SapOrder();
        sapOrder.setOrderId("orderId");
        sapOrder.setOrderContent("oc");
        sapOrder.setGoodsCode("goodsCode");
        sapOrder.setOrderNum(8L);
        sapOrder.setCreateTime(new Date());
        sapOrder.setGoodsCnText("goodsCnText");
        sapOrder.setLastReceiveDate("20150909");
        sapOrder.setOnTheRoadNum(3L);
        sapOrder.setOrderDate("20150721");
        sapOrder.setOrderFlag("1");
        sapOrder.setOrderToNum(22L);
        sapOrder.setOrderType("tt");
        sapOrder.setUpdateTime(new Date());
        sapOrder.setStockTypeName("44");
        sapOrder.setStockTypeId("44");
        sapOrder.setPlaceName("placeName");
        sapOrder.setPlaceId("pi");
        sapOrder.setPlanDate("20150909");
        sapOrderService.insertSapOrder(sapOrder);
    }

    @Test
    @Rollback(false)
    public void updateSapOrderTest() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", 25);
        SapOrder sapOrder = sapOrderService.findSapOrderById(inMap);
        sapOrder.setUpdateTime(new Date());
        sapOrderService.updateSapOrder(sapOrder);
    }

    @Test
    @Rollback(false)
    public void updateSapOrderByOrderIdTest() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("orderId", "orderId");
        List<SapOrder> sapOrders = sapOrderService.findSapOrder(inMap);
        if (sapOrders != null && sapOrders.size() > 0) {
            SapOrder sapOrder = sapOrders.get(0);
            sapOrder.setUpdateTime(new Date());
            sapOrderService.updateByOrderIdSapOrder(sapOrder);
        }
    }

    @Test
    @Rollback(false)
    public void deleteSapOrderTest() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", 25);
        sapOrderService.deleteSapOrder(inMap);
    }

    @Test
    @Rollback(false)
    public void findSapOrderByIdTest() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("id", 1);
        SapOrder sapOrder = sapOrderService.findSapOrderById(inMap);
        System.out.println(sapOrder);
    }

    @Test
    @Rollback(false)
    public void findSapOrderTest() throws Exception {
        List sapOrders = sapOrderService.findSapOrder(null);
        System.out.println(sapOrders.size());
    }

    @Test
    @Rollback(false)
    public void findSapOrderByPageTest() throws Exception {
        Page page = sapOrderService.findSapOrderByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
