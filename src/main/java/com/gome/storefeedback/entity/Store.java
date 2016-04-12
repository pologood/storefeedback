package com.gome.storefeedback.entity;

import java.io.Serializable;

/**
 * 门店实体
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:11:41
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Store implements Serializable{
    /**  */
	private static final long serialVersionUID = 1L;

	/** 门店代码 */
    private String store_code;

    /** 门店名称 */
    private String store_name;

    /** 销售组织 */
    private String sale_org;

    /** 公司代码 */
    private String company_code;

    /** 二级分部编码  */
    private String second_division_code;

    /** 二级分部描述 */
    private String second_division_des;

    /** 一级分部编码 */
    private String division_code;

    /** 一级分部描述 */
    private String division_des;

    /** 门店地址 */
    private String store_address;

    /** 门店电话 */
    private String store_tel;

    /** 更新标识 */
    private String update_flag;

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code == null ? null : store_code.trim();
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name == null ? null : store_name.trim();
    }

    public String getSale_org() {
        return sale_org;
    }

    public void setSale_org(String sale_org) {
        this.sale_org = sale_org == null ? null : sale_org.trim();
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code == null ? null : company_code.trim();
    }

    public String getSecond_division_code() {
        return second_division_code;
    }

    public void setSecond_division_code(String second_division_code) {
        this.second_division_code = second_division_code == null ? null : second_division_code.trim();
    }

    public String getSecond_division_des() {
        return second_division_des;
    }

    public void setSecond_division_des(String second_division_des) {
        this.second_division_des = second_division_des == null ? null : second_division_des.trim();
    }

    public String getDivision_code() {
        return division_code;
    }

    public void setDivision_code(String division_code) {
        this.division_code = division_code == null ? null : division_code.trim();
    }

    public String getDivision_des() {
        return division_des;
    }

    public void setDivision_des(String division_des) {
        this.division_des = division_des == null ? null : division_des.trim();
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address == null ? null : store_address.trim();
    }

    public String getStore_tel() {
        return store_tel;
    }

    public void setStore_tel(String store_tel) {
        this.store_tel = store_tel == null ? null : store_tel.trim();
    }

	public String getUpdate_flag() {
		return update_flag;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

	@Override
	public String toString() {
		return "Store [store_code=" + store_code + ", store_name=" + store_name
				+ ", sale_org=" + sale_org + ", company_code=" + company_code
				+ ", second_division_code=" + second_division_code
				+ ", second_division_des=" + second_division_des
				+ ", division_code=" + division_code + ", division_des="
				+ division_des + ", store_address=" + store_address
				+ ", store_tel=" + store_tel + ", update_flag=" + update_flag
				+ "]";
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company_code == null) ? 0 : company_code.hashCode());
        result = prime * result + ((division_code == null) ? 0 : division_code.hashCode());
        result = prime * result + ((division_des == null) ? 0 : division_des.hashCode());
        result = prime * result + ((sale_org == null) ? 0 : sale_org.hashCode());
        result = prime * result + ((second_division_code == null) ? 0 : second_division_code.hashCode());
        result = prime * result + ((second_division_des == null) ? 0 : second_division_des.hashCode());
        result = prime * result + ((store_address == null) ? 0 : store_address.hashCode());
        result = prime * result + ((store_code == null) ? 0 : store_code.hashCode());
        result = prime * result + ((store_name == null) ? 0 : store_name.hashCode());
        result = prime * result + ((store_tel == null) ? 0 : store_tel.hashCode());
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
        Store other = (Store) obj;
        if (company_code == null) {
            if (other.company_code != null)
                return false;
        } else if (!company_code.equals(other.company_code))
            return false;
        if (division_code == null) {
            if (other.division_code != null)
                return false;
        } else if (!division_code.equals(other.division_code))
            return false;
        if (division_des == null) {
            if (other.division_des != null)
                return false;
        } else if (!division_des.equals(other.division_des))
            return false;
        if (sale_org == null) {
            if (other.sale_org != null)
                return false;
        } else if (!sale_org.equals(other.sale_org))
            return false;
        if (second_division_code == null) {
            if (other.second_division_code != null)
                return false;
        } else if (!second_division_code.equals(other.second_division_code))
            return false;
        if (second_division_des == null) {
            if (other.second_division_des != null)
                return false;
        } else if (!second_division_des.equals(other.second_division_des))
            return false;
        if (store_address == null) {
            if (other.store_address != null)
                return false;
        } else if (!store_address.equals(other.store_address))
            return false;
        if (store_code == null) {
            if (other.store_code != null)
                return false;
        } else if (!store_code.equals(other.store_code))
            return false;
        if (store_name == null) {
            if (other.store_name != null)
                return false;
        } else if (!store_name.equals(other.store_name))
            return false;
        if (store_tel == null) {
            if (other.store_tel != null)
                return false;
        } else if (!store_tel.equals(other.store_tel))
            return false;
        if (update_flag == null) {
            if (other.update_flag != null)
                return false;
        } else if (!update_flag.equals(other.update_flag))
            return false;
        return true;
    }

    
}