package com.gome.storefeedback.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 十大品类配置
 * @author Zhang.Mingji
 * @date 2015年7月17日下午2:09:18
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class GoodsCategoryConfig implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 267074091881941067L;

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
}