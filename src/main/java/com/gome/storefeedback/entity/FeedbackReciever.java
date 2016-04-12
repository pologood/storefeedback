package com.gome.storefeedback.entity;

/**
 * 缺货记录_接收人
 * 
 * @author Gong.ZhiBin
 * @date 2015年3月6日下午2:04:39
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackReciever {
    // 缺货记录ID
    private String feedbackId;
    // 接收人
    private String feedbackPersonId;

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

    /**
     * 获取feedbackPersonId
     * 
     * @return feedbackPersonId
     */
    public String getFeedbackPersonId() {
        return feedbackPersonId;
    }

    /**
     * 设置feedbackPersonId
     * 
     * @param feedbackPersonId feedbackPersonId
     */
    public void setFeedbackPersonId(String feedbackPersonId) {
        this.feedbackPersonId = feedbackPersonId;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((feedbackId == null) ? 0 : feedbackId.hashCode());
        result = prime * result + ((feedbackPersonId == null) ? 0 : feedbackPersonId.hashCode());
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
        FeedbackReciever other = (FeedbackReciever) obj;
        if (feedbackId == null) {
            if (other.feedbackId != null)
                return false;
        } else if (!feedbackId.equals(other.feedbackId))
            return false;
        if (feedbackPersonId == null) {
            if (other.feedbackPersonId != null)
                return false;
        } else if (!feedbackPersonId.equals(other.feedbackPersonId))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String toString() {
        return "FeedbackReciever [feedbackId=" + feedbackId + ", feedbackPersonId=" + feedbackPersonId + "]";
    }

}