package com.gome.storefeedback.entity;

import java.util.Date;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午3:00:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class GoodsBrand {

    /** 品牌编码 */
    private String brandCode;

    /** 中文描述 */
    private String cnText;

    /** 英文描述 */
    private String enText;

    /** 品牌类型 */
    private String brandClass;

    /** 更新标志 */
    private String updateFlag;

    /** 创建时间 */
    private Date createTime;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getCnText() {
        return cnText;
    }

    public void setCnText(String cnText) {
        this.cnText = cnText == null ? null : cnText.trim();
    }

    public String getEnText() {
        return enText;
    }

    public void setEnText(String enText) {
        this.enText = enText == null ? null : enText.trim();
    }

    public String getBrandClass() {
        return brandClass;
    }

    public void setBrandClass(String brandClass) {
        this.brandClass = brandClass == null ? null : brandClass.trim();
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
        result = prime * result + ((brandCode == null) ? 0 : brandCode.hashCode());
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
        GoodsBrand other = (GoodsBrand) obj;
        if (brandCode == null) {
            if (other.brandCode != null)
                return false;
        } else if (!brandCode.equals(other.brandCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GoodsBrand [brandCode=" + brandCode + ", cnText=" + cnText + ", enText=" + enText + ", brandClass="
                + brandClass + ", updateFlag=" + updateFlag + ", createTime=" + createTime + "]";
    }

    
}