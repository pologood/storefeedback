package com.gome.storefeedback.job;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.CheckFeedbackPushService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToDisplayService;
import com.gome.storefeedback.util.DateTimeUtil;

public class SapFeedbackCheckPushDisplayJob {
	
	@Resource
    private SapFeedbackCheckEmpToDisplayService sapFeedbackCheckEmpToDisplayService;
	/**
	 * 用于关联BW推送的考核数据，关联相关员工
	 * 时间(每次BW考核表更新之后执行 —— )
	 * @throws Exception
	 */
    public void execute() throws Exception {
        try {
        	sapFeedbackCheckEmpToDisplayService.pushToDisplay(DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(today())
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
