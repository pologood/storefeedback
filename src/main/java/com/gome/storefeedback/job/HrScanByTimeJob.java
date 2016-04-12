package com.gome.storefeedback.job;

import javax.annotation.Resource;

import com.gome.storefeedback.service.HrScanByTimeService;

public class HrScanByTimeJob {
	@Resource
	private HrScanByTimeService hrScanByTimeService;
	
	public void execute() {
		try {
			hrScanByTimeService.HrScanByTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
