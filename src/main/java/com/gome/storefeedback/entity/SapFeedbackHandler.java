package com.gome.storefeedback.entity;

import java.util.Date;

/**
 * 
 * SAP商品缺断货处理
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月23日下午6:43:27
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class SapFeedbackHandler {

    private String id;
    private String request;
    private Double datapakid;
    private Integer record;
    private Integer isPush;
    private Date pushTime;
    private Integer isHandler;
    private Integer handlerResult;
    private Integer resultNoCode;
    private String resultNoName;
    private Integer resultYesOrderNum;
    private String handlerEmpId;
    private String handlerEmpNumber;
    private String handlerEmpName;
    private Date handlerTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Double getDatapakid() {
        return datapakid;
    }

    public void setDatapakid(Double datapakid) {
        this.datapakid = datapakid;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getIsHandler() {
        return isHandler;
    }

    public void setIsHandler(Integer isHandler) {
        this.isHandler = isHandler;
    }

    public Integer getHandlerResult() {
        return handlerResult;
    }

    public void setHandlerResult(Integer handlerResult) {
        this.handlerResult = handlerResult;
    }

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

    public Integer getResultYesOrderNum() {
        return resultYesOrderNum;
    }

    public void setResultYesOrderNum(Integer resultYesOrderNum) {
        this.resultYesOrderNum = resultYesOrderNum;
    }

    public String getHandlerEmpId() {
        return handlerEmpId;
    }

    public void setHandlerEmpId(String handlerEmpId) {
        this.handlerEmpId = handlerEmpId;
    }

    public String getHandlerEmpNumber() {
        return handlerEmpNumber;
    }

    public void setHandlerEmpNumber(String handlerEmpNumber) {
        this.handlerEmpNumber = handlerEmpNumber;
    }

    public String getHandlerEmpName() {
        return handlerEmpName;
    }

    public void setHandlerEmpName(String handlerEmpName) {
        this.handlerEmpName = handlerEmpName;
    }

    public Date getHandlerTime() {
        return handlerTime;
    }

    public void setHandlerTime(Date handlerTime) {
        this.handlerTime = handlerTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        SapFeedbackHandler other = (SapFeedbackHandler) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String buildPK() {
        this.id = this.request + "#" + this.datapakid + "#" + this.record;
        return this.id;
    }
}
