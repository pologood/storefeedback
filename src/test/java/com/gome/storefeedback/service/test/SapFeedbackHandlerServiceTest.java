package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.SapFeedbackHandler;
import com.gome.storefeedback.service.SapFeedbackHandlerService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * SapFeedbackHandlerService 测试
 * 
 * @author 
 * @date 2015年07月23日 18时35分24秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class SapFeedbackHandlerServiceTest extends AbstractTransactionalSpringContextTestCase{

    private SapFeedbackHandlerService sapFeedbackHandlerService = null;

    @Before
    public void init() {
        sapFeedbackHandlerService = (SapFeedbackHandlerService) this.getBeanByName("sapFeedbackHandlerService");
    }
    
    @Test
    @Rollback(false)
    public void batchSapFeedbackHandlerTest() throws Exception {
    	
    	List<SapFeedbackHandler> sapFeedbackHandlerList=new ArrayList<SapFeedbackHandler>();
        SapFeedbackHandler sapFeedbackHandler=new SapFeedbackHandler();

        sapFeedbackHandlerList.add(sapFeedbackHandler);
        
        sapFeedbackHandlerService.batchSapFeedbackHandler(sapFeedbackHandlerList);
    }
    
    @Test
    @Rollback(false)
    public void insertSapFeedbackHandlerTest() throws Exception {
        SapFeedbackHandler sapFeedbackHandler=new SapFeedbackHandler();
        sapFeedbackHandler.setDatapakid(10D);
        sapFeedbackHandler.setRequest("DTPR_52GYLI23JGTHE363WXJC7HKP5");
        sapFeedbackHandler.setRecord(23267);
        
        int result = sapFeedbackHandlerService.insertSapFeedbackHandler(sapFeedbackHandler);
        System.out.println(sapFeedbackHandler.getId());
        System.out.println(result);
    }

    @Test
    @Rollback(false)
    public void updateSapFeedbackHandlerTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        
        inMap.put("request", "DTPR_52GYLI23JGTHE363WXJC7HKP5");
        inMap.put("datapakid", 6D);
        inMap.put("record", 14066);
        List<SapFeedbackHandler> result = sapFeedbackHandlerService.findSapFeedbackHandler(inMap);
        SapFeedbackHandler sapFeedbackHandler=result.get(0);
        sapFeedbackHandler.setResultYesOrderNum(1);
                
        sapFeedbackHandlerService.updateSapFeedbackHandler(sapFeedbackHandler);
    }

    @Test
    @Rollback(false)
    public void deleteSapFeedbackHandlerTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        sapFeedbackHandlerService.deleteSapFeedbackHandler(inMap);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackHandlerByIdTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        SapFeedbackHandler sapFeedbackHandler=sapFeedbackHandlerService.findSapFeedbackHandlerById(inMap);
        System.out.println(sapFeedbackHandler);
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackHandlerTest() throws Exception {
        List sapFeedbackHandlers = sapFeedbackHandlerService.findSapFeedbackHandler(null);
        System.out.println(sapFeedbackHandlers.size());
    }

    @Test
    @Rollback(false)
    public void findSapFeedbackHandlerByPageTest() throws Exception {
        Page page = sapFeedbackHandlerService.findSapFeedbackHandlerByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
