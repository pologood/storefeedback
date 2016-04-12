package com.gome.storefeedback.entity;

import java.util.Date;

public class NewMessagePushRecord {
    private String id;

    private String title;

    private String content;

    private String type;

    private String notificationId;

    private String notificationEmployeeId;

    private String notificationEmployeeName;

    private Date notificationTime;

    private String reportId;

    private String reportEmpId;

    private String reportEmpName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId == null ? null : notificationId.trim();
    }

    public String getNotificationEmployeeId() {
        return notificationEmployeeId;
    }

    public void setNotificationEmployeeId(String notificationEmployeeId) {
        this.notificationEmployeeId = notificationEmployeeId == null ? null : notificationEmployeeId.trim();
    }

    public String getNotificationEmployeeName() {
        return notificationEmployeeName;
    }

    public void setNotificationEmployeeName(String notificationEmployeeName) {
        this.notificationEmployeeName = notificationEmployeeName == null ? null : notificationEmployeeName.trim();
    }

    public Date getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Date notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getReportEmpId() {
        return reportEmpId;
    }

    public void setReportEmpId(String reportEmpId) {
        this.reportEmpId = reportEmpId == null ? null : reportEmpId.trim();
    }

    public String getReportEmpName() {
        return reportEmpName;
    }

    public void setReportEmpName(String reportEmpName) {
        this.reportEmpName = reportEmpName == null ? null : reportEmpName.trim();
    }
}