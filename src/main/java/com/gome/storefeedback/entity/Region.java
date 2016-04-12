package com.gome.storefeedback.entity;

import java.io.Serializable;

/**
 * 大区实体
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:07:49
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Region implements Serializable{
    /**  */
	private static final long serialVersionUID = 1L;

	/** 大区编码 */
    private String region_code;

    /** 大区描述 */
    private String region_des;

    /** 下发标识 */
    private String update_flag;

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code == null ? null : region_code.trim();
    }

    public String getRegion_des() {
        return region_des;
    }

    public void setRegion_des(String region_des) {
        this.region_des = region_des == null ? null : region_des.trim();
    }

	public String getUpdate_flag() {
		return update_flag;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

	@Override
	public String toString() {
		return "Region [region_code=" + region_code + ", region_des="
				+ region_des + ", update_flag=" + update_flag + "]";
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((region_code == null) ? 0 : region_code.hashCode());
        result = prime * result + ((region_des == null) ? 0 : region_des.hashCode());
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
        Region other = (Region) obj;
        if (region_code == null) {
            if (other.region_code != null)
                return false;
        } else if (!region_code.equals(other.region_code))
            return false;
        if (region_des == null) {
            if (other.region_des != null)
                return false;
        } else if (!region_des.equals(other.region_des))
            return false;
        if (update_flag == null) {
            if (other.update_flag != null)
                return false;
        } else if (!update_flag.equals(other.update_flag))
            return false;
        return true;
    }

    
}