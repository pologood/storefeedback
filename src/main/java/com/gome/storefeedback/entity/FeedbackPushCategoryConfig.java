package com.gome.storefeedback.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 缺断货推送 品类 配置
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午3:20:57
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackPushCategoryConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String categoryCode;

    private String categoryName;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        FeedbackPushCategoryConfig other = (FeedbackPushCategoryConfig) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}