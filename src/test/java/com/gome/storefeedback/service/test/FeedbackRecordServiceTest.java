package com.gome.storefeedback.service.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.entity.FeedbackRecord;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackRecordService;
import com.gome.storefeedback.service.PersonService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.web.page.page.Page;
import com.gome.storefeedback.web.page.page.PaginationParameters;


/**
 * @author Zhang.Jingang
 * @date 2015年1月22日下午3:03:36
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackRecordServiceTest extends AbstractTransactionalSpringContextTestCase{

	@Test
	@Rollback(false)
	public void insertFeedbackRecordTest() throws Exception{
		FeedbackRecordService feedbackRecordService = (FeedbackRecordService) this.getBeanByName("feedbackRecordService");
		FeedbackRecord feedbackRecord = new FeedbackRecord();
		feedbackRecord.setId("1");
		feedbackRecord.setFeedbackPersonEmployeeName("张金阳");
		
		feedbackRecordService.insertFeedbackRecord(feedbackRecord);
	}
	
	@Test
	public void findByIdTest() throws Exception{
	    FeedbackRecordService feedbackRecordService = (FeedbackRecordService) this.getBeanByName("feedbackRecordService");
	    Map<String,Object> inMap = new HashMap<String, Object>();
	    inMap.put("id", "1");
	    FeedbackRecord fb = feedbackRecordService.findFeedbackRecordById(inMap);
	    System.out.println(fb.getFeedbackPersonEmployeeName());
	}
	@Test
	public void findFeedbackRecordTest() throws Exception{
	       FeedbackRecordService feedbackRecordService = (FeedbackRecordService) this.getBeanByName("feedbackRecordService");
	       List<FeedbackRecord> list = feedbackRecordService.findFeedbackRecord(null);
	       for(FeedbackRecord f : list){
	           System.out.println(f.getFeedbackPersonEmployeeName());
	       }
	}
	@Test
	@Rollback(false)
	public void updateTest() throws Exception{
        FeedbackRecordService feedbackRecordService = (FeedbackRecordService) this.getBeanByName("feedbackRecordService");
        Map<String,Object> inMap = new HashMap<String, Object>();
        inMap.put("id", "1");
        FeedbackRecord fb = feedbackRecordService.findFeedbackRecordById(inMap);
        fb.setFeedbackPersonEmployeeName("张小阳");
        feedbackRecordService.updateFeedbackRecord(fb);
	}
	@Test
	@Rollback(false)
    public void deleteFeedbackRecordTest() throws Exception{
        FeedbackRecordService feedbackRecordService = (FeedbackRecordService) this.getBeanByName("feedbackRecordService");
        Map<String,Object> inMap = new HashMap<String,Object>();
        inMap.put("id", "1");
        feedbackRecordService.deleteFeedbackRecord(inMap);
    }
	@Test
	public void findFeedbackRecordByPage() throws Exception{
        FeedbackRecordService ps = (FeedbackRecordService) this.getBeanByName("feedbackRecordService");
        Page page = ps.findFeedbackRecordByPage(null, new PaginationParameters());
        System.out.println(page.getTotalSize());
        List<FeedbackRecord> list = page.getDataList();
        for(FeedbackRecord p : list){
            System.out.println(p.getFeedbackPersonEmployeeName());
        }
    }
}
