package com.gome.storefeedback.entity;

import java.util.Date;

public class FeedbackToOa {
    private String id;

    private String request;

    private Long datapakid;

    private Integer record;

    private String pushSts;

    private Date pushTime;

    private String dept;

    private String empnum;

    private String name;

    private String sanctionMoney;

    private String sanctionPoints;

    private String sanctionMonth;

    private Date insertTime;
    private String calday;

    public String getCalday() {
		return calday;
	}

	public void setCalday(String calday) {
		this.calday = calday;
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

    public String getPushSts() {
        return pushSts;
    }

    public void setPushSts(String pushSts) {
        this.pushSts = pushSts == null ? null : pushSts.trim();
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getEmpnum() {
        return empnum;
    }

    public void setEmpnum(String empnum) {
        this.empnum = empnum == null ? null : empnum.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSanctionMoney() {
        return sanctionMoney;
    }

    public void setSanctionMoney(String sanctionMoney) {
        this.sanctionMoney = sanctionMoney == null ? null : sanctionMoney.trim();
    }

    public String getSanctionPoints() {
        return sanctionPoints;
    }

    public void setSanctionPoints(String sanctionPoints) {
        this.sanctionPoints = sanctionPoints == null ? null : sanctionPoints.trim();
    }

    public String getSanctionMonth() {
        return sanctionMonth;
    }

    public void setSanctionMonth(String sanctionMonth) {
        this.sanctionMonth = sanctionMonth == null ? null : sanctionMonth.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}