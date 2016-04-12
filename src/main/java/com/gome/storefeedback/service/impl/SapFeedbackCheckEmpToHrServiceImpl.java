package com.gome.storefeedback.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.storefeedback.dao.SapFeedbackCheckEmpToHrDao;
import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;
import com.gome.storefeedback.exception.BaseException;
import com.gome.storefeedback.service.SapFeedbackCheckEmpToHrService;
import com.gome.storefeedback.util.SapEmpSanction;
import com.gome.storefeedback.util.SapEmpSanctionUtil;

@Component("sapFeedbackCheckEmpToHrService")
public class SapFeedbackCheckEmpToHrServiceImpl implements SapFeedbackCheckEmpToHrService{

	@Resource
	private SapFeedbackCheckEmpToHrDao sapFeedbackCheckEmpToHrDao;
	
	@Override
	public List<CheckSapFeedbackEmpCount> getEmpCheck(Date currentDateTime) throws BaseException{
		
		HashMap<String, Object> inMap = this.getMonthDay();
		
		return sapFeedbackCheckEmpToHrDao.getEmpCheck(inMap);
	}
	

	@Override
	public int insertToHrScan(List<CheckSapFeedbackEmpCount> checkSapFeedbackEmpCount) throws BaseException {
		return sapFeedbackCheckEmpToHrDao.insertToHrScan(checkSapFeedbackEmpCount);
	}

	@Override
	public List<CheckSapFeedbackEmpCount> getEmpByHr() throws BaseException {
		return sapFeedbackCheckEmpToHrDao.getEmpByHr();
	}

	@Override
	public void deleteFlag() throws BaseException {
		sapFeedbackCheckEmpToHrDao.deleteFlag();
	}

	@Override
	public int updateByEmpNumber(String empNumber) throws BaseException {
		return sapFeedbackCheckEmpToHrDao.updateByEmpNumber(empNumber);
	}
	

	@Override
	public CheckSapFeedbackEmpCount getEmpByEmpNumber(Map<String,Object> inMap)
			throws BaseException {
		return sapFeedbackCheckEmpToHrDao.getEmpByEmpNumber(inMap);
	}

	@Override
	public void deleteByEmpNumber(ArrayList<String> empNumbers)
			throws BaseException {
		sapFeedbackCheckEmpToHrDao.deleteByEmpNumber(empNumbers);
	}
	
	public void pushToHr(Date currentDateTime){
		try {
			List<CheckSapFeedbackEmpCount> xmlData = this.getEmpCheck(currentDateTime);
			SapEmpSanction sapEmpSanction = new SapEmpSanction(xmlData);
			SapEmpSanctionUtil.response2XML(sapEmpSanction);
			
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Object> getMonthDay() {
		HashMap<String, Object> inMap = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        // int day1 = cal.getActualMinimum(Calendar.DAY_OF_MONTH);//起始天数
        cal.add(Calendar.MONTH, -1);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天数
        if (month == 0) {
            year = cal.get(Calendar.YEAR) ;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }
        String startDay = year + "-" + month + "-" + "01";
        String endDay = year + "-" + month + "-" + day;
        if(month<10){
        	inMap.put("month", year+"0"+month);
        }else{
        	inMap.put("month", year+""+month);
        }
        
        inMap.put("startDay", startDay);
		inMap.put("endDay", endDay);
        return inMap;
    }
}
