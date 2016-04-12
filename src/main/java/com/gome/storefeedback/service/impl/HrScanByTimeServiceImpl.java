package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;
import com.gome.storefeedback.service.HrScanByTimeService;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToHrService;
import com.gome.storefeedback.util.SapEmpSanction;
import com.gome.storefeedback.util.SapEmpSanctionUtil;
@Component("hrScanByTimeService")
public class HrScanByTimeServiceImpl implements HrScanByTimeService {

	@Resource
	private SapFeedbackCheckEmpToHrService sapFeedbackCheckEmpToHrService;
	@Override
	public void HrScanByTime() throws Exception{

		//hr_scan_bytime 表格中所有 flag = 0 未推送的人员列表
		List<CheckSapFeedbackEmpCount> empByHr = sapFeedbackCheckEmpToHrService.getEmpByHr();
		if(empByHr != null && empByHr.size() > 0){
			for (CheckSapFeedbackEmpCount checkSapFeedbackEmpCount : empByHr) {
//				Calendar cal = Calendar.getInstance();
//				Date date = new Date();
//				cal.setTime(date);
				String sanctionMonth = checkSapFeedbackEmpCount.getSanctionMonth();
				int year = Integer.parseInt(sanctionMonth.substring(0, 3));
				int month = Integer.parseInt(sanctionMonth.substring(4, 5));
//				if (month == 12) {
//		            year = cal.get(Calendar.YEAR)-1 ;
//		        } else {
//		            year = cal.get(Calendar.YEAR);
//		        }
				
				String startDay = year + "-" + month + "-" + "01";
				String endDay = year + "-" + month + "-" + "31";
				Map<String,Object> inMap = new HashMap<String, Object>();
				inMap.put("empNumber", checkSapFeedbackEmpCount.getEmpNumber());
				inMap.put("startDay", startDay);
				inMap.put("endDay", endDay);
				inMap.put("month", sanctionMonth);
				
				CheckSapFeedbackEmpCount checkSapFeedbackEmpCount1 = sapFeedbackCheckEmpToHrService.getEmpByEmpNumber(inMap);
				checkSapFeedbackEmpCount.setSanctionMoney(checkSapFeedbackEmpCount1.getSanctionMoney());
				checkSapFeedbackEmpCount.setSanctionPoints(checkSapFeedbackEmpCount1.getSanctionPoints());
			}
			//封装实体——注意能否封装进去
			SapEmpSanction sapEmpSanction = new SapEmpSanction(empByHr);
			List<CheckSapFeedbackEmpCount> response2xml = SapEmpSanctionUtil.response2XML(sapEmpSanction);
			if(response2xml != null && response2xml.size() >0){
				//如果还有数据未被 HR 接收，从 hr_scan_bytime 里删除已经被接受的数据
				ArrayList<String> empNumbers = new ArrayList<String>();
				for (CheckSapFeedbackEmpCount checkSapFeedbackEmpCount : response2xml) {
					empNumbers.add(checkSapFeedbackEmpCount.getEmpNumber());
				}
				sapFeedbackCheckEmpToHrService.deleteByEmpNumber(empNumbers);
			}
		}
	}

}
