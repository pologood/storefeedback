package com.gome.storefeedback.entity;

/**
 * @author Ma.Mingyang
 * @date 2015年2月2日下午1:09:49
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class SecondDivision {
    /** 二级分部 */
    private String second_division_code;

    /** 二级分部描述 */
    private String second_division_des;

    /** 一级分部编码 */
    private String first_division_code;

    /** 更新标识 */
    private String update_flag;

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

    public String getFirst_division_code() {
        return first_division_code;
    }

    public void setFirst_division_code(String first_division_code) {
        this.first_division_code = first_division_code == null ? null : first_division_code.trim();
    }

	public String getUpdate_flag() {
		return update_flag;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

	@Override
	public String toString() {
		return "SecondDivision [second_division_code=" + second_division_code
				+ ", second_division_des=" + second_division_des
				+ ", first_division_code=" + first_division_code
				+ ", update_flag=" + update_flag + "]";
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first_division_code == null) ? 0 : first_division_code.hashCode());
        result = prime * result + ((second_division_code == null) ? 0 : second_division_code.hashCode());
        result = prime * result + ((second_division_des == null) ? 0 : second_division_des.hashCode());
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
        SecondDivision other = (SecondDivision) obj;
        if (first_division_code == null) {
            if (other.first_division_code != null)
                return false;
        } else if (!first_division_code.equals(other.first_division_code))
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
        if (update_flag == null) {
            if (other.update_flag != null)
                return false;
        } else if (!update_flag.equals(other.update_flag))
            return false;
        return true;
    }

}