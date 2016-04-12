package com.gome.storefeedback.entity;

import java.util.Date;

public class FeedbackAppeal {
    private String id;

    private String request;

    private Long datapakid;

    private Integer record;

    private Integer isPush;

    private Date pushTime;

    private Integer isHandler;

    private Integer handlerResult;

    private String handlerNumber;

    private String handlerName;

    private Date handlerTime;

    private String appealnum;

    private String appealName;

    private String appealDept;

    private String appealReason;

    private Date appealTime;
    
    private String appealModel;
    
    private String  appealAcccount;
    
    private String calday;

    public String getCalday() {
		return calday;
	}

	public void setCalday(String calday) {
		this.calday = calday;
	}
    

    public String getAppealAcccount() {
		return appealAcccount;
	}

	public void setAppealAcccount(String appealAcccount) {
		this.appealAcccount = appealAcccount;
	}

	public String getAppealModel() {
		return appealModel;
	}

	public void setAppealModel(String appealModel) {
		this.appealModel = appealModel;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    public Long getDatapakid() {
        return datapakid;
    }

    public void setDatapakid(Long datapakid) {
        this.datapakid = datapakid;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getIsHandler() {
        return isHandler;
    }

    public void setIsHandler(Integer isHandler) {
        this.isHandler = isHandler;
    }

    public Integer getHandlerResult() {
        return handlerResult;
    }

    public void setHandlerResult(Integer handlerResult) {
        this.handlerResult = handlerResult;
    }

    public String getHandlerNumber() {
        return handlerNumber;
    }

    public void setHandlerNumber(String handlerNumber) {
        this.handlerNumber = handlerNumber == null ? null : handlerNumber.trim();
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName == null ? null : handlerName.trim();
    }

    public Date getHandlerTime() {
        return handlerTime;
    }

    public void setHandlerTime(Date handlerTime) {
        this.handlerTime = handlerTime;
    }

    public String getAppealnum() {
        return appealnum;
    }

    public void setAppealnum(String appealnum) {
        this.appealnum = appealnum == null ? null : appealnum.trim();
    }

    public String getAppealName() {
        return appealName;
    }

    public void setAppealName(String appealName) {
        this.appealName = appealName == null ? null : appealName.trim();
    }

    public String getAppealDept() {
        return appealDept;
    }

    public void setAppealDept(String appealDept) {
        this.appealDept = appealDept == null ? null : appealDept.trim();
    }

    public String getAppealReason() {
        return appealReason;
    }

    public void setAppealReason(String appealReason) {
        this.appealReason = appealReason == null ? null : appealReason.trim();
    }

    public Date getAppealTime() {
        return appealTime;
    }

    public void setAppealTime(Date appealTime) {
        this.appealTime = appealTime;
    }
}