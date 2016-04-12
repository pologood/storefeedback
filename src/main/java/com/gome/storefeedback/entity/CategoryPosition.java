package com.gome.storefeedback.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 十大品类和职务对应关系
 * @author Zhang.Mingji
 * @date 2015年7月17日下午2:05:51
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class CategoryPosition implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7141499382479232318L;

	private String id;

    private String positionCode;

    private String positionDesc;

    private Date createTime;

    private String goodsCategoryId;
    
    private String categoryCode;
    
    

    public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc == null ? null : positionDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(String goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId == null ? null : goodsCategoryId.trim();
    }
}