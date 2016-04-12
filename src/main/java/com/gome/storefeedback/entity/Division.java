package com.gome.storefeedback.entity;

import java.io.Serializable;

/**
 * 分部实体
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:05:39
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Division implements Serializable{
	
    /**  */
	private static final long serialVersionUID = 1L;

	/** 分部编码 */
    private String division_code;

    /** 分布名称 */
    private String division_desc;

    /** 大区编码 */
    private String region_code;

    /** 更新标识 */
    private String update_flag;

    public String getDivision_code() {
        return division_code;
    }

    public void setDivision_code(String division_code) {
        this.division_code = division_code == null ? null : division_code.trim();
    }

    public String getDivision_desc() {
        return division_desc;
    }

    public void setDivision_desc(String division_desc) {
        this.division_desc = division_desc == null ? null : division_desc.trim();
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code == null ? null : region_code.trim();
    }

    
	public String getUpdate_flag() {
		return update_flag;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

	@Override
	public String toString() {
		return "Division [division_code=" + division_code + ", division_desc="
				+ division_desc + ", region_code=" + region_code
				+ ", update_flag=" + update_flag + "]";
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((division_code == null) ? 0 : division_code.hashCode());
        result = prime * result + ((division_desc == null) ? 0 : division_desc.hashCode());
        result = prime * result + ((region_code == null) ? 0 : region_code.hashCode());
        result = prime * result + ((update_flag == null) ? 0 : update_flag.hashCode());
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
        Division other = (Division) obj;
        if (division_code == null) {
            if (other.division_code != null)
                return false;
        } else if (!division_code.equals(other.division_code))
            return false;
        if (division_desc == null) {
            if (other.division_desc != null)
                return false;
        } else if (!division_desc.equals(other.division_desc))
            return false;
        if (region_code == null) {
            if (other.region_code != null)
                return false;
        } else if (!region_code.equals(other.region_code))
            return false;
        if (update_flag == null) {
            if (other.update_flag != null)
                return false;
        } else if (!update_flag.equals(other.update_flag))
            return false;
        return true;
    }

    
}