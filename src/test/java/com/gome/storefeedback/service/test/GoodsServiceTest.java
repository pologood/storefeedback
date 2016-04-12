package com.gome.storefeedback.service.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.Goods;
import com.gome.storefeedback.service.GoodsService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

public class GoodsServiceTest extends AbstractTransactionalSpringContextTestCase {

    private GoodsService goodsService;

    @Before
    public void init() {
        goodsService = (GoodsService) this.getBeanByName("goodsService");
    }

    @Test
    @Rollback(false)
    public void testInsertGoods() throws Exception {
        for (int i = 0; i < 20; i++) {
            Goods goods = new Goods();
            goods.setGoodsCode("goodsCode" + i);
            goods.setGoodsClass("goodsClass");
            goods.setGoodsName("goodsName" + i);
            goodsService.insertGoods(goods);
        }
        Goods goods0 = new Goods();
        goods0.setGoodsCode("goodsCode");
        goods0.setGoodsClass("goodsClass");
        goods0.setGoodsName("goodsName");
        goodsService.insertGoods(goods0);
    }

    @Test
    @Rollback(false)
    public void testUpdateGoods() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("goodsCode", "goodsCode");
        Goods goods = goodsService.findGoodsById(inMap);
        if (goods != null) {
            goodsService.updateGoods(goods);
        }
    }

    @Test
    @Rollback(false)
    public void testDeleteGoods() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("goodsCode", "goodsCode");
        goodsService.deleteGoods(inMap);
    }

    @Test
    @Rollback(false)
    public void testFindGoodsById() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("goodsCode", "goodsCode1");
        Goods goods = goodsService.findGoodsById(inMap);
        if (goods != null) {
            System.out.println(goods.getUpdateFlag());
        } else {
            System.out.println(" goods is null..........");
        }
    }

    @Test
    @Rollback(false)
    public void testFindGoods() throws Exception {
        List list = goodsService.findGoods(null);
        System.out.println(list.size());
    }

    @Test
    @Rollback(false)
    public void testFindGoodsByPage() throws Exception {
        Page page = goodsService.findGoodsByPage(null, new PaginationParameters(0, 2));
        if (page != null) {
            System.out.println(page);
            System.out.println(page.getTotalSize());
            List<Goods> list = page.getDataList();
            for (Goods goods : list) {
                System.out.println(goods.getGoodsCode() + " : " + goods.getUpdateFlag());
            }
        }
    }

}
