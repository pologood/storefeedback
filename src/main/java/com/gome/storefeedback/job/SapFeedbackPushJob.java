package com.gome.storefeedback.job;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.FeedbackPushService;
import com.gome.storefeedback.util.DateTimeUtil;

public class SapFeedbackPushJob {

    @Resource
    private FeedbackPushService feedbackPushService;

    public void execute() throws Exception {
        try {
            feedbackPushService.pushByPosition(DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(today())
                    + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(e);
        }
    }

    private static Date today() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(c.DATE, -1);
        Date temp_date = c.getTime();
        return temp_date;
    }
}
