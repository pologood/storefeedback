package com.gome.storefeedback.entity;

/**
 * 缺货类别
 * @author Zhang.Jingang
 * @date 2015年1月22日下午5:39:09
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class LackCategory {
    //类别代码
    private String categoryCode;
    //类别名称
    private String categoryName;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryCode == null) ? 0 : categoryCode.hashCode());
        result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
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
        LackCategory other = (LackCategory) obj;
        if (categoryCode == null) {
            if (other.categoryCode != null)
                return false;
        } else if (!categoryCode.equals(other.categoryCode))
            return false;
        if (categoryName == null) {
            if (other.categoryName != null)
                return false;
        } else if (!categoryName.equals(other.categoryName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LackCategory [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
    }
    
    
}