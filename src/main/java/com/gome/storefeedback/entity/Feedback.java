package com.gome.storefeedback.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 缺货记录反馈
 * 
 * @author Zhang.Jingang
 * @date 2015年1月22日下午2:40:09
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Feedback {
    // 主键
    private String id;
    // 发起人ID
    private String sponsorId;
    // 发起人员工编号
    private String sponsorEmployeeId;
    // 发起人员工姓名
    private String sponsorEmployeeName;
    // 发起人公司ID
    private String sponsorCompanyId;
    // 一级品类代码
    private String firstCategory;
    // 二级品类代码
    private String secondCategory;
    // 品牌
    private String brandCode;
    // 商品编码
    private String goodsCode;
    // 缺货类别
    private String lackCategory;
    // 预计售完日期
    private String anticipatedDatesSoldOut;
    // 售完天数
    private Integer saleOutDate;
    // 数量
    private Integer quantity;
    // 创建时间
    private Date createTime;
    // 所属门店编码
    private String storeCode;

    /** 是否回执{0:没有回执，>0 :有回执 }*/
    private int hasReturn;

    public int getHasReturn() {
        return hasReturn;
    }

    public void setHasReturn(int hasReturn) {
        this.hasReturn = hasReturn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId == null ? null : sponsorId.trim();
    }

    public String getSponsorEmployeeId() {
        return sponsorEmployeeId;
    }

    public void setSponsorEmployeeId(String sponsorEmployeeId) {
        this.sponsorEmployeeId = sponsorEmployeeId == null ? null : sponsorEmployeeId.trim();
    }

    public String getSponsorEmployeeName() {
        return sponsorEmployeeName;
    }

    public void setSponsorEmployeeName(String sponsorEmployeeName) {
        this.sponsorEmployeeName = sponsorEmployeeName == null ? null : sponsorEmployeeName.trim();
    }

    public String getSponsorCompanyId() {
        return sponsorCompanyId;
    }

    public void setSponsorCompanyId(String sponsorCompanyId) {
        this.sponsorCompanyId = sponsorCompanyId == null ? null : sponsorCompanyId.trim();
    }

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory == null ? null : firstCategory.trim();
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory == null ? null : secondCategory.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getLackCategory() {
        return lackCategory;
    }

    public void setLackCategory(String lackCategory) {
        this.lackCategory = lackCategory == null ? null : lackCategory.trim();
    }

    public String getAnticipatedDatesSoldOut() {
        return anticipatedDatesSoldOut;
    }

    public void setAnticipatedDatesSoldOut(String anticipatedDatesSoldOut) {
        this.anticipatedDatesSoldOut = anticipatedDatesSoldOut == null ? null : anticipatedDatesSoldOut.trim();
    }

    public Integer getSaleOutDate() {
        return saleOutDate;
    }

    public void setSaleOutDate(Integer saleOutDate) {
        this.saleOutDate = saleOutDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((anticipatedDatesSoldOut == null) ? 0 : anticipatedDatesSoldOut.hashCode());
        result = prime * result + ((brandCode == null) ? 0 : brandCode.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((firstCategory == null) ? 0 : firstCategory.hashCode());
        result = prime * result + ((goodsCode == null) ? 0 : goodsCode.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lackCategory == null) ? 0 : lackCategory.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((saleOutDate == null) ? 0 : saleOutDate.hashCode());
        result = prime * result + ((secondCategory == null) ? 0 : secondCategory.hashCode());
        result = prime * result + ((sponsorCompanyId == null) ? 0 : sponsorCompanyId.hashCode());
        result = prime * result + ((sponsorEmployeeId == null) ? 0 : sponsorEmployeeId.hashCode());
        result = prime * result + ((sponsorEmployeeName == null) ? 0 : sponsorEmployeeName.hashCode());
        result = prime * result + ((sponsorId == null) ? 0 : sponsorId.hashCode());
        result = prime * result + ((storeCode == null) ? 0 : storeCode.hashCode());
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
        Feedback other = (Feedback) obj;
        if (anticipatedDatesSoldOut == null) {
            if (other.anticipatedDatesSoldOut != null)
                return false;
        } else if (!anticipatedDatesSoldOut.equals(other.anticipatedDatesSoldOut))
            return false;
        if (brandCode == null) {
            if (other.brandCode != null)
                return false;
        } else if (!brandCode.equals(other.brandCode))
            return false;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (firstCategory == null) {
            if (other.firstCategory != null)
                return false;
        } else if (!firstCategory.equals(other.firstCategory))
            return false;
        if (goodsCode == null) {
            if (other.goodsCode != null)
                return false;
        } else if (!goodsCode.equals(other.goodsCode))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lackCategory == null) {
            if (other.lackCategory != null)
                return false;
        } else if (!lackCategory.equals(other.lackCategory))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (saleOutDate == null) {
            if (other.saleOutDate != null)
                return false;
        } else if (!saleOutDate.equals(other.saleOutDate))
            return false;
        if (secondCategory == null) {
            if (other.secondCategory != null)
                return false;
        } else if (!secondCategory.equals(other.secondCategory))
            return false;
        if (sponsorCompanyId == null) {
            if (other.sponsorCompanyId != null)
                return false;
        } else if (!sponsorCompanyId.equals(other.sponsorCompanyId))
            return false;
        if (sponsorEmployeeId == null) {
            if (other.sponsorEmployeeId != null)
                return false;
        } else if (!sponsorEmployeeId.equals(other.sponsorEmployeeId))
            return false;
        if (sponsorEmployeeName == null) {
            if (other.sponsorEmployeeName != null)
                return false;
        } else if (!sponsorEmployeeName.equals(other.sponsorEmployeeName))
            return false;
        if (sponsorId == null) {
            if (other.sponsorId != null)
                return false;
        } else if (!sponsorId.equals(other.sponsorId))
            return false;
        if (storeCode == null) {
            if (other.storeCode != null)
                return false;
        } else if (!storeCode.equals(other.storeCode))
            return false;
        return true;
    }

}