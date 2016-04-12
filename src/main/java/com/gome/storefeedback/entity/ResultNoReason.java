package com.gome.storefeedback.entity;

/**
 * 不补货原因字典 实体
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月22日下午4:20:56
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class ResultNoReason {
    /** 不补货原因编码 */
    private Integer resultNoCode;
    /** 不补货原因描述 */
    private String resultNoName;
    /** 启用|停用 */
    private int isUsing;
    /** 排序 */
    private int sortOrder;

    public Integer getResultNoCode() {
        return resultNoCode;
    }

    public void setResultNoCode(Integer resultNoCode) {
        this.resultNoCode = resultNoCode;
    }

    public String getResultNoName() {
        return resultNoName;
    }

    public void setResultNoName(String resultNoName) {
        this.resultNoName = resultNoName;
    }

    public int getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(int isUsing) {
        this.isUsing = isUsing;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resultNoCode == null) ? 0 : resultNoCode.hashCode());
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
        ResultNoReason other = (ResultNoReason) obj;
        if (resultNoCode == null) {
            if (other.resultNoCode != null)
                return false;
        } else if (!resultNoCode.equals(other.resultNoCode))
            return false;
        return true;
    }
}
