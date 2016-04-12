package com.gome.storefeedback.job;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToHrService;
import com.gome.storefeedback.util.DateTimeUtil;
/**
 * 考核结果数据推送HR
 * 每月推送 HR 一次业务人员考核结果
 * 时间()
 * @author zhaozhenxiu
 *
 */
public class SapFeedbackCheckPushToHrJob {
	
	@Resource
    private SapFeedbackCheckEmpToHrService sapFeedbackCheckEmpToHrService;

	public void execute() throws Exception {
        try {
        	sapFeedbackCheckEmpToHrService.pushToHr(DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(today())
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
