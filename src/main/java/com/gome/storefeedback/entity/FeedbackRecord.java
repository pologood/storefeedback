package com.gome.storefeedback.entity;

import java.util.Date;
/**
 * 反馈信息记录
 * @author Zhang.Jingang
 * @date 2015年1月22日下午2:41:01
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackRecord {
    //主键
    private String id;
    //反馈人员工编号
    private String feedbackPersonEmployeeId;
    //反馈人姓名
    private String feedbackPersonEmployeeName;
    //反馈人ID
    private String feedbackPersonId;
    //反馈内容
    private String feedbackContent;
    //反馈时间
    private Date feedbackTime;
    //缺货记录ID
    private String feedbackId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFeedbackPersonEmployeeId() {
        return feedbackPersonEmployeeId;
    }

    public void setFeedbackPersonEmployeeId(String feedbackPersonEmployeeId) {
        this.feedbackPersonEmployeeId = feedbackPersonEmployeeId == null ? null : feedbackPersonEmployeeId.trim();
    }

    public String getFeedbackPersonEmployeeName() {
        return feedbackPersonEmployeeName;
    }

    public void setFeedbackPersonEmployeeName(String feedbackPersonEmployeeName) {
        this.feedbackPersonEmployeeName = feedbackPersonEmployeeName == null ? null : feedbackPersonEmployeeName.trim();
    }

    public String getFeedbackPersonId() {
        return feedbackPersonId;
    }

    public void setFeedbackPersonId(String feedbackPersonId) {
        this.feedbackPersonId = feedbackPersonId == null ? null : feedbackPersonId.trim();
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId == null ? null : feedbackId.trim();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((feedbackContent == null) ? 0 : feedbackContent.hashCode());
        result = prime * result + ((feedbackId == null) ? 0 : feedbackId.hashCode());
        result = prime * result + ((feedbackPersonEmployeeId == null) ? 0 : feedbackPersonEmployeeId.hashCode());
        result = prime * result + ((feedbackPersonEmployeeName == null) ? 0 : feedbackPersonEmployeeName.hashCode());
        result = prime * result + ((feedbackPersonId == null) ? 0 : feedbackPersonId.hashCode());
        result = prime * result + ((feedbackTime == null) ? 0 : feedbackTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeedbackRecord other = (FeedbackRecord) obj;
        if (feedbackContent == null) {
            if (other.feedbackContent != null)
                return false;
        } else if (!feedbackContent.equals(other.feedbackContent))
            return false;
        if (feedbackId == null) {
            if (other.feedbackId != null)
                return false;
        } else if (!feedbackId.equals(other.feedbackId))
            return false;
        if (feedbackPersonEmployeeId == null) {
            if (other.feedbackPersonEmployeeId != null)
                return false;
        } else if (!feedbackPersonEmployeeId.equals(other.feedbackPersonEmployeeId))
            return false;
        if (feedbackPersonEmployeeName == null) {
            if (other.feedbackPersonEmployeeName != null)
                return false;
        } else if (!feedbackPersonEmployeeName.equals(other.feedbackPersonEmployeeName))
            return false;
        if (feedbackPersonId == null) {
            if (other.feedbackPersonId != null)
                return false;
        } else if (!feedbackPersonId.equals(other.feedbackPersonId))
            return false;
        if (feedbackTime == null) {
            if (other.feedbackTime != null)
                return false;
        } else if (!feedbackTime.equals(other.feedbackTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
}