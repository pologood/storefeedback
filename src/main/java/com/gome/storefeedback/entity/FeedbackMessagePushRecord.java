package com.gome.storefeedback.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 反馈通知消息推送记录 (实体)
 * 
 * @author Ma.Mingyang
 * @date 2015年1月22日下午2:11:06
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackMessagePushRecord implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 通知消息标题
     */
    private String title;

    /**
     * 通知消息内容
     */
    private String content;

    /**
     * 通知类型
     */
    private int type;

    /**
     * 通知接收人的id
     */
    private String notificationId;

    /**
     * 通知接收人的员工编号
     */
    private String notificationEmployeeId;

    /**
     * 通知接收人的姓名
     */
    private String notificationEmployeeName;

    /**
     * 通知的时间
     */
    private Date notificationTime;

    /** 发送人主键id */
    private String sendId;

    /** 发送人员工编号 */
    private String sendEmployeeId;

    /** 发送人员工姓名 */
    private String sendEmployeeName;

    /** 缺货记录ID */
    private String feedbackId;

    /**
     * 获取feedbackId
     * 
     * @return feedbackId
     */
    public String getFeedbackId() {
        return feedbackId;
    }

    /**
     * 设置feedbackId
     * 
     * @param feedbackId feedbackId
     */
    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getSendEmployeeId() {
        return sendEmployeeId;
    }

    public void setSendEmployeeId(String sendEmployeeId) {
        this.sendEmployeeId = sendEmployeeId;
    }

    public String getSendEmployeeName() {
        return sendEmployeeName;
    }

    public void setSendEmployeeName(String sendEmployeeName) {
        this.sendEmployeeName = sendEmployeeName;
    }

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

    /**
     * 获取type
     * 
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * 设置type
     * 
     * @param type type
     */
    public void setType(int type) {
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

    /**
     * {@inheritDoc}
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeedbackMessagePushRecord other = (FeedbackMessagePushRecord) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String toString() {
        return "FeedbackMessagePushRecord [id=" + id + ", title=" + title + ", content=" + content + ", type=" + type
                + ", notificationId=" + notificationId + ", notificationEmployeeId=" + notificationEmployeeId
                + ", notificationEmployeeName=" + notificationEmployeeName + ", notificationTime=" + notificationTime
                + ", sendId=" + sendId + ", sendEmployeeId=" + sendEmployeeId + ", sendEmployeeName="
                + sendEmployeeName + ", feedbackId=" + feedbackId + "]";
    }
}