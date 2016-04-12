package com.gome.storefeedback.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 缺断货推送 品类和职务对应关系
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月28日下午3:22:12
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class FeedbackPushCategoryPositionConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String categoryCode;
    private String categoryName;
    
    /** 缺断货部门 */
    private Integer srcOrgType;
    private String srcOrgNumber;
    private String srcOrgName;

    /** 推送部门 */
    private Integer isGMZB;
    private String orgNumber;
    private String orgName;
    private String positionCode;
    private String positionDesc;
    private String position;

    private Integer roleId;

    public Integer getSrcOrgType() {
        return srcOrgType;
    }

    public void setSrcOrgType(Integer srcOrgType) {
        this.srcOrgType = srcOrgType;
    }

    public String getSrcOrgNumber() {
        return srcOrgNumber;
    }

    public void setSrcOrgNumber(String srcOrgNumber) {
        this.srcOrgNumber = srcOrgNumber;
    }

    public String getSrcOrgName() {
        return srcOrgName;
    }

    public void setSrcOrgName(String srcOrgName) {
        this.srcOrgName = srcOrgName;
    }

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getIsGMZB() {
        return isGMZB;
    }

    public void setIsGMZB(Integer isGMZB) {
        this.isGMZB = isGMZB;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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
        FeedbackPushCategoryPositionConfig other = (FeedbackPushCategoryPositionConfig) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}