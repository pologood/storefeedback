package com.gome.storefeedback.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 系统用户实体
 * @author Ma.Mingyang
 * @date 2015年1月22日下午2:09:18
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class User implements Serializable{
	/**  */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 是否是管理员（int，1：管理员；0：不是）
     */
    private int isAdmin;
    /**
     * 用户状态
     */
    private int startus;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
	 * 获取isAdmin
	 * @return isAdmin
	 */
	public int getIsAdmin() {
		return isAdmin;
	}

	/**
	 * 设置isAdmin
	 * @param isAdmin isAdmin
	 */
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * 获取startus
	 * @return startus
	 */
	public int getStartus() {
		return startus;
	}

	/**
	 * 设置startus
	 * @param startus startus
	 */
	public void setStartus(int startus) {
		this.startus = startus;
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
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", isAdmin=" + isAdmin
				+ ", startus=" + startus + ", createTime=" + createTime + "]";
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + isAdmin;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + startus;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isAdmin != other.isAdmin)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (startus != other.startus)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
    
	
}