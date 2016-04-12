package com.gome.storefeedback.job;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackWithEmpService;
import com.gome.storefeedback.util.DateTimeUtil;
/**
 * 时间(每天跟随BW表的更新 执行—— )
 * @author zhaozhenxiu
 *
 */
public class SapFeedbackWithEmpJob {

	@Resource
    private SapFeedbackWithEmpService sapFeedbackWithEmpService;
	/**
	 * 用于关联BW推送的缺断货数据，关联相关员工，显示员工对应的缺断货信息
	 * @throws Exception
	 */
    public void execute() throws Exception {
        try {
        	sapFeedbackWithEmpService.pushWithEmp(DateTimeUtil.getCurrentDateTime(DateTimeUtil.formatDate(today())
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
