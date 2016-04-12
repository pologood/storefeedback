package com.gome.storefeedback.service.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.gome.storefeedback.service.CheckFeedbackPushService;
import com.gome.storefeedback.service.FeedbackPushService;
import com.gome.storefeedback.service.HrScanByTimeService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToDisplayService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToHrService;
import com.gome.storefeedback.service.SapFeedbackWithEmpService;
import com.gome.storefeedback.service.common.AbstractTransactionalSpringContextTestCase;
import com.gome.storefeedback.util.DateTimeUtil;



/**
 * 
 * SapFeedbackPushService 测试
 * 
 * @author
 * @date 2015年07月30日 09时07分50秒
 * @Copyright(c) gome inc Gome Co.,LTD
 */

public class FeedbackPushServiceTest extends AbstractTransactionalSpringContextTestCase {

    private FeedbackPushService feedbackPushService = null;
    private CheckFeedbackPushService fheckFeedbackPushService = null;//
    private SapFeedbackCheckEmpToDisplayService sapFeedbackCheckEmpToDisplayService = null;//
    private SapFeedbackCheckEmpToHrService sapFeedbackCheckEmpToHrService = null;
    private SapFeedbackWithEmpService sapFeedbackWithEmpService = null;
    private HrScanByTimeService hrScanByTimeService = null;

    

    @Before
    public void init() {
        feedbackPushService = (FeedbackPushService) this.getBeanByName("feedbackPushService");
        fheckFeedbackPushService = (CheckFeedbackPushService) this.getBeanByName("checkFeedbackPushService");//
        sapFeedbackCheckEmpToDisplayService = (SapFeedbackCheckEmpToDisplayService) this.getBeanByName("sapFeedbackCheckEmpToDisplayService");//
        sapFeedbackCheckEmpToHrService = (SapFeedbackCheckEmpToHrService) this.getBeanByName("sapFeedbackCheckEmpToHrService");//
        sapFeedbackWithEmpService = (SapFeedbackWithEmpService) this.getBeanByName("sapFeedbackWithEmpService");//
        hrScanByTimeService = (HrScanByTimeService) this.getBeanByName("hrScanByTimeService");
    }

    @Test
    @Rollback(false)
    public void pushByPositionTest() throws Exception {
        feedbackPushService.pushByPosition(DateTimeUtil.getCurrentDateTime("2015-11-29 00:00:00"));
    }

    @Test
    @Rollback(false)
    public void pushByPositionTest1() throws Exception {
    	fheckFeedbackPushService.pushCheckFeedback(DateTimeUtil.getCurrentDateTime("2015-12-03 00:00:00"));
    }
    @Test
    @Rollback(false)
    public void pushToDisplayTest() throws Exception {
    	sapFeedbackCheckEmpToDisplayService.pushToDisplay(DateTimeUtil.getCurrentDateTime("2016-02-26 00:00:00"));
    }
    @Test
    @Rollback(false)
    public void pushToHrTest() throws Exception {
    	sapFeedbackCheckEmpToHrService.pushToHr(DateTimeUtil.getCurrentDateTime("2015-11-30 00:00:00"));
    }
    
    @Test
    @Rollback(false)
    public void pushWithEmpTest() throws Exception {
    	sapFeedbackWithEmpService.pushWithEmp(DateTimeUtil.getCurrentDateTime("2016-02-25 00:00:00"));
    }
    
    @Test
    @Rollback(false)
    public void hrScanByTimeTest() throws Exception {
    	hrScanByTimeService.HrScanByTime();
    }
}
