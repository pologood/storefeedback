package com.gome.storefeedback.entity;

import java.util.Date;

/**
 * SAP缺断货信息关系 实体
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月30日上午9:12:04
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class SapFeedbackCheckPush {

    /** 主键 */
    private String id;
    /** 推送数据日期 */
    private Date pushDataDate;
    /** 推送的品类 */
    private String pushCategory;
    /** 推送的部门 */
    private String pushOrg;
    /** 推送的职位 */
    private String pushPosition;
    /** 推送的内容 */
    private String pushContent;
    /** 推送人ID */
    private String pushEmpId;
    /** 推送人员工编号 */
    private String pushEmpNumber;
    /** 推送人姓名 */
    private String pushEmpName;
    /** 推送时间 */
    private Date pushTime;
    /** 角色 */
    private Integer roleId;

  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPushDataDate() {
        return pushDataDate;
    }

    public void setPushDataDate(Date pushDataDate) {
        this.pushDataDate = pushDataDate;
    }

    public String getPushCategory() {
        return pushCategory;
    }

    public void setPushCategory(String pushCategory) {
        this.pushCategory = pushCategory;
    }

    public String getPushOrg() {
        return pushOrg;
    }

    public void setPushOrg(String pushOrg) {
        this.pushOrg = pushOrg;
    }

    public String getPushPosition() {
        return pushPosition;
    }

    public void setPushPosition(String pushPosition) {
        this.pushPosition = pushPosition;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public String getPushEmpId() {
        return pushEmpId;
    }

    public void setPushEmpId(String pushEmpId) {
        this.pushEmpId = pushEmpId;
    }

    public String getPushEmpNumber() {
        return pushEmpNumber;
    }

    public void setPushEmpNumber(String pushEmpNumber) {
        this.pushEmpNumber = pushEmpNumber;
    }

    public String getPushEmpName() {
        return pushEmpName;
    }

    public void setPushEmpName(String pushEmpName) {
        this.pushEmpName = pushEmpName;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
        SapFeedbackCheckPush other = (SapFeedbackCheckPush) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
