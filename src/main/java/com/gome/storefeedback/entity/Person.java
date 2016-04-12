package com.gome.storefeedback.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 该类只作为模板参考，不参与具体的业务
 * @author Zhang.Mingji
 * @date 2014年8月7日上午10:55:07
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Person implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5977069940841914900L;

	private Integer id;
	/**
	 * 
	 */
    private String name;

    private Integer age;

    private String address;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}