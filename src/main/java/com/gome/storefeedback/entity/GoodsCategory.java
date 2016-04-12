package com.gome.storefeedback.entity;

import java.util.Date;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午3:04:59
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class GoodsCategory {
    /** 分类代码 */
    private String classCode;

    /** 分类名称 */
    private String className;

    /** 分类级别 */
    private String classLevel;

    /** 上级分类代码 */
    private String parentClassCode;

    /** 是否末级 */
    private Integer isLeaf;

    /** 事业部代码 */
    private String divisionCode;

    /** 事业部名称 */
    private String divisionName;

    /** 更新标志 */
    private String updateFlag;

    /** 创建时间 */
    private Date createTime;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel == null ? null : classLevel.trim();
    }

    public String getParentClassCode() {
        return parentClassCode;
    }

    public void setParentClassCode(String parentClassCode) {
        this.parentClassCode = parentClassCode == null ? null : parentClassCode.trim();
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode == null ? null : divisionCode.trim();
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName == null ? null : divisionName.trim();
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag == null ? null : updateFlag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classCode == null) ? 0 : classCode.hashCode());
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
        GoodsCategory other = (GoodsCategory) obj;
        if (classCode == null) {
            if (other.classCode != null)
                return false;
        } else if (!classCode.equals(other.classCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GoodsCategory [classCode=" + classCode + ", className=" + className + ", classLevel=" + classLevel
                + ", parentClassCode=" + parentClassCode + ", isLeaf=" + isLeaf + ", divisionCode=" + divisionCode
                + ", divisionName=" + divisionName + ", updateFlag=" + updateFlag + ", createTime=" + createTime + "]";
    }

    
}