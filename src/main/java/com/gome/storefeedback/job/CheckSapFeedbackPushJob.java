package com.gome.storefeedback.job;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.CheckFeedbackPushService;
import com.gome.storefeedback.service.FeedbackPushService;
import com.gome.storefeedback.util.DateTimeUtil;
/**
 * 每天给相关员工推送考核通知
 * 时间(每天执行一次 —— )
 * @author zhaozhenxiu
 *
 */
public class CheckSapFeedbackPushJob {

    @Resource
    private CheckFeedbackPushService fheckFeedbackPushService;

    public void execute() throws Exception {
        try {
            fheckFeedbackPushService.pushCheckFeedback(DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(today())
                    + " 00:00:00"));
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(e);
        }
    }

    private static Date today() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(c.DATE, 0);
        Date temp_date = c.getTime();
        return temp_date;
    }
}
