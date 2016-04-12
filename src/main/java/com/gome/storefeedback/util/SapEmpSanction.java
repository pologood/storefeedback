package com.gome.storefeedback.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.gome.storefeedback.entity.CheckSapFeedbackEmpCount;

/**
 * 数据回写实体(缺断货考核员工处罚实体)
 * @author zhaozhenxiu
 * 
 */
public class SapEmpSanction implements Serializable{

	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	final String headerInterfaceId = "HCM055";
    private String headerMessageId;//任意32位
    final String headerSender = "GSM";
    final String headerReceiver = "HCM";
    //时间
    private String headerDtsend;
    
    private List<CheckSapFeedbackEmpCount> xmlData;

    public SapEmpSanction(List<CheckSapFeedbackEmpCount> xmlData){
    	this.xmlData = xmlData;
    	this.headerMessageId = UUIDUtil.getUUID();
        this.headerDtsend = format.format(new Date());
    }
    
	public SapEmpSanction() {
		this.headerMessageId = UUIDUtil.getUUID();
        this.headerDtsend = format.format(new Date());
	}

	public String getHeaderMessageId() {
		return headerMessageId;
	}

	public void setHeaderMessageId(String headerMessageId) {
		this.headerMessageId = headerMessageId;
	}

	public String getHeaderDtsend() {
		return headerDtsend;
	}

	public void setHeaderDtsend(String headerDtsend) {
		this.headerDtsend = headerDtsend;
	}

	public List<CheckSapFeedbackEmpCount> getXmlData() {
		return xmlData;
	}

	public void setXmlData(List<CheckSapFeedbackEmpCount> xmlData) {
		this.xmlData = xmlData;
	}

	public String getHeaderInterfaceId() {
		return headerInterfaceId;
	}

	public String getHeaderSender() {
		return headerSender;
	}

	public String getHeaderReceiver() {
		return headerReceiver;
	}
    
    
}
