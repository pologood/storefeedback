package com.gome.storefeedback.service.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.GoodsCategory;
import com.gome.storefeedback.service.GoodsCategoryService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

public class GoodsCategoryServiceTest extends AbstractTransactionalSpringContextTestCase {

    @Test
    @Rollback(false)
    public void testInsertGoodsCategory() throws Exception {
        GoodsCategoryService goodsCategoryService = (GoodsCategoryService) this.applicationContext
                .getBean("goodsCategoryService");
        for (int i = 0; i < 20; i++) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setClassCode("classCode" + i);
            goodsCategory.setClassLevel("level");
            goodsCategory.setClassName("className" + i);
            goodsCategory.setCreateTime(new Date());
            goodsCategory.setDivisionCode("divisionCode" + i);
            goodsCategory.setDivisionName("divisionName" + i);
            goodsCategory.setIsLeaf(new Integer((int) 1));
            goodsCategory.setParentClassCode("parentClassCode");
            goodsCategory.setUpdateFlag("F");
            goodsCategoryService.insertGoodsCategory(goodsCategory);
        }
    }

    @Test
    @Rollback(false)
    public void testUpdateGoodsCategory() throws Exception {
        GoodsCategoryService goodsCategoryService = (GoodsCategoryService) this.applicationContext
                .getBean("goodsCategoryService");
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("classCode", "classCode");
        GoodsCategory goodsCategory = goodsCategoryService.findGoodsCategoryById(inMap);
        goodsCategory.setUpdateFlag("D");
        goodsCategoryService.updateGoodsCategory(goodsCategory);
    }

    @Test
    @Rollback(false)
    public void testDeleteGoodsCategory() throws Exception {
        GoodsCategoryService goodsCategoryService = (GoodsCategoryService) this.applicationContext
                .getBean("goodsCategoryService");
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("classCode", "classCode");
        goodsCategoryService.deleteGoodsCategory(inMap);
    }

    @Test
    @Rollback(false)
    public void testFindGoodsCategoryById() throws Exception {
        GoodsCategoryService goodsCategoryService = (GoodsCategoryService) this.applicationContext
                .getBean("goodsCategoryService");
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("classCode", "classCode");
        GoodsCategory goodsCategory = goodsCategoryService.findGoodsCategoryById(inMap);
        System.out.println(goodsCategory.getClassName());
    }

    @Test
    @Rollback(false)
    public void testFindGoodsCategory() throws Exception {
        GoodsCategoryService goodsCategoryService = (GoodsCategoryService) this.applicationContext
                .getBean("goodsCategoryService");
        List<GoodsCategory> list = goodsCategoryService.findGoodsCategory(null);
        System.out.println(list.size());
    }

    @Test
    @Rollback(false)
    public void testFindGoodsCategoryByPage() throws Exception {
        GoodsCategoryService goodsCategoryService = (GoodsCategoryService) this.applicationContext
                .getBean("goodsCategoryService");
        PaginationParameters paginationParameters = new PaginationParameters();
        paginationParameters.setPageMaxSize(10);
        Page page = goodsCategoryService.findGoodsCategoryByPage(null, paginationParameters);
        System.out.println(page.getTotalSize());
        for (GoodsCategory g : (List<GoodsCategory>) page.getDataList()) {
            System.out.println(g.getClassName());
        }
    }
    @Test
    @Rollback(false)
    public void testfindBaseCategorys() throws Exception {
        GoodsCategoryService goodsCategoryService = (GoodsCategoryService) this.applicationContext
                .getBean("goodsCategoryService");
        
        List<Map<String, Object>> list = goodsCategoryService.findBaseCategorys(null);
        for(Map<String, Object> map : list){
            System.out.println(map.get("keyValue"));
           /* for(Map.Entry<String, Object> entry : map.entrySet()){
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }*/
        }
    }
}
