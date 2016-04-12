package com.gome.storefeedback.service.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.GoodsBrand;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.GoodsBrandService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午4:48:55
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class GoodsBrandServiceTest extends AbstractTransactionalSpringContextTestCase {

    private GoodsBrandService goodsBrandService = null;

    @Before
    public void init() {
        goodsBrandService = (GoodsBrandService) this.getBeanByName("goodsBrandService");
    }

    @Test
    @Rollback(false)
    public void testInsertGoodsBrand() throws Exception {
        GoodsBrand goodsBrand0 = new GoodsBrand();
        goodsBrand0.setBrandCode("brandCode");
        goodsBrand0.setBrandClass("brandClass");
        goodsBrand0.setCnText("cnText");
        goodsBrand0.setCreateTime(new Date());
        goodsBrand0.setEnText("enText");
        goodsBrand0.setUpdateFlag("F");
        goodsBrandService.insertGoodsBrand(goodsBrand0);
        for (int i = 0; i < 20; i++) {
            GoodsBrand goodsBrand = new GoodsBrand();
            goodsBrand.setBrandCode("brandCode" + i);
            goodsBrand.setBrandClass("brandClass" + i);
            goodsBrand.setCnText("cnText");
            goodsBrand.setCreateTime(new Date());
            goodsBrand.setEnText("enText");
            goodsBrand.setUpdateFlag("F" + i);
            goodsBrandService.insertGoodsBrand(goodsBrand);
        }
    }

    @Test
    @Rollback(false)
    public void testUpdateGoodsBrand() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("brandCode", "brandCode");
        GoodsBrand goodsBrand = goodsBrandService.findGoodsBrandById(inMap);
        goodsBrand.setUpdateFlag("D");
        goodsBrandService.updateGoodsBrand(goodsBrand);

    }

    @Test
    @Rollback(false)
    public void testDeleteGoodsBrand() throws Exception {
        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("brandCode", "brandCode");
        goodsBrandService.deleteGoodsBrand(inMap);
    }

    @Test
    @Rollback(false)
    public void testFindGoodsBrandById() throws Exception {

        Map<String, Object> inMap = new HashMap<String, Object>();
        inMap.put("brandCode", "brandCode");
        GoodsBrand goodsBrand = goodsBrandService.findGoodsBrandById(inMap);
        System.out.println(goodsBrand.getBrandClass());
    }

    @Test
    @Rollback(false)
    public void testFindGoodsBrand() throws Exception {
        List<GoodsBrand> goodsBrands = goodsBrandService.findGoodsBrand(null);
        System.out.println(goodsBrands.size());
        for (GoodsBrand goodsBrand : goodsBrands) {
            System.out.println(goodsBrand.getBrandClass());
        }
    }

    @Test
    @Rollback(false)
    public void testFindGoodsBrandByPage() throws Exception {
        PaginationParameters param = new PaginationParameters(0, 10);
        Page page = goodsBrandService.findGoodsBrandByPage(null, param);
        System.out.println(page.getTotalSize());
        List<GoodsBrand> dataList = page.getDataList();
        for (GoodsBrand goodsBrand : dataList) {
            System.out.println(goodsBrand.getBrandClass());
        }
    }

}
