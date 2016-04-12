package com.gome.storefeedback.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Gong.ZhiBin
 * @date 2015年4月1日下午3:45:36
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class MQErrorInfo implements Serializable {
    private String id;
    private String type;
    private String content;
    private String error;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
