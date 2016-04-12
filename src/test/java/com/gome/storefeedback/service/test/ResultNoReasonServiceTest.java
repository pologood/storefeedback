package com.gome.storefeedback.service.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.ResultNoReason;
import com.gome.storefeedback.service.ResultNoReasonService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;

/**
 * 
 * ResultNoReasonService 测试
 * 
 * @author 
 * @date 2015年07月22日 16时05分35秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class ResultNoReasonServiceTest extends AbstractTransactionalSpringContextTestCase{

    private ResultNoReasonService resultNoReasonService = null;

    @Before
    public void init() {
        resultNoReasonService = (ResultNoReasonService) this.getBeanByName("resultNoReasonService");
    }
    
    @Test
    @Rollback(false)
    public void batchResultNoReasonTest() throws Exception {
        List<ResultNoReason> resultNoReasonList=new ArrayList<ResultNoReason>();
        ResultNoReason resultNoReason=new ResultNoReason();
        resultNoReason.setResultNoCode(2);
        resultNoReason.setResultNoName("原因2");
        resultNoReason.setIsUsing(0);
        resultNoReason.setSortOrder(2);
        resultNoReasonList.add(resultNoReason);
        resultNoReasonService.batchResultNoReason(resultNoReasonList);
    }
    
    @Test
    @Rollback(false)
    public void insertResultNoReasonTest() throws Exception {
        ResultNoReason resultNoReason=new ResultNoReason();
        resultNoReason.setResultNoCode(1);
        resultNoReason.setResultNoName("原因1");
        resultNoReason.setIsUsing(0);
        resultNoReason.setSortOrder(1);
        resultNoReasonService.insertResultNoReason(resultNoReason);
    }

    @Test
    @Rollback(false)
    public void updateResultNoReasonTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        inMap.put("resultNoCode", "1");
        ResultNoReason resultNoReason=resultNoReasonService.findResultNoReasonById(inMap);
        resultNoReason.setResultNoName("reason1");
        resultNoReasonService.updateResultNoReason(resultNoReason);
    }

    @Test
    @Rollback(false)
    public void deleteResultNoReasonTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        inMap.put("resultNoCode", "2");
        resultNoReasonService.deleteResultNoReason(inMap);
    }

    @Test
    @Rollback(false)
    public void findResultNoReasonByIdTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        inMap.put("resultNoCode", "1");
        ResultNoReason resultNoReason=resultNoReasonService.findResultNoReasonById(inMap);
        System.out.println(resultNoReason);
    }

    @Test
    @Rollback(false)
    public void findResultNoReasonTest() throws Exception {
        Map<String, Object> inMap=new HashMap<String, Object>();
        inMap.put("isUsing", "0");
        List resultNoReasons = resultNoReasonService.findResultNoReason(inMap);
        System.out.println(resultNoReasons.size());
    }

    @Test
    @Rollback(false)
    public void findResultNoReasonByPageTest() throws Exception {
        Page page = resultNoReasonService.findResultNoReasonByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        System.out.println(page.getDataList().size());
    }
}
