package com.gome.storefeedback.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据回写实体
 * @author Ma.Mingyang
 * @date 2015年2月10日上午11:23:45
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class DataBack implements Serializable {
	
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    
    /** 表示成功的常量 */
    public static final String DETAILRESULT_SUCCESS="S";
    /** 表示失败的常量  */
    public static final String DETAILRESULT_FAILURE="F";
    
    private String headerInterfaceId = "COMMON";
    private String headerMessageId;//任意32位
    private String headerSender = "GSM";
    private String headerReceiver = "ECC";
    private String headerDtsend;

    /** 接口号，需要设置值 */
    private String detailInterfaceO;
    /** 消息id，需要设置值 */
    private String detailMessageO;
    /** 成功/失败,需要设置值 */
    private String detailResult;
    
    /** 结果信息,需要设置值 */
    private Map<String, String> details = new HashMap<String, String>();

    public DataBack(String detailInterfaceO) {
        this.detailInterfaceO = detailInterfaceO;
        this.headerMessageId = UUIDUtil.getUUID();
        this.headerDtsend = format.format(new Date());
    }

    public DataBack() {
        this.headerMessageId = UUIDUtil.getUUID();
        this.headerDtsend = format.format(new Date());
    }

   

    /**
     * 获取headerInterfaceId
     * 
     * @return headerInterfaceId
     */
    public String getHeaderInterfaceId() {
        return headerInterfaceId;
    }

    /**
     * 设置headerInterfaceId
     * 
     * @param headerInterfaceId headerInterfaceId
     */
    public void setHeaderInterfaceId(String headerInterfaceId) {
        this.headerInterfaceId = headerInterfaceId;
    }

    /**
     * 获取headerMessageId
     * 
     * @return headerMessageId
     */
    public String getHeaderMessageId() {
        return headerMessageId;
    }

    /**
     * 设置headerMessageId
     * 
     * @param headerMessageId headerMessageId
     */
    public void setHeaderMessageId(String headerMessageId) {
        this.headerMessageId = headerMessageId;
    }

    /**
     * 获取headerSender
     * 
     * @return headerSender
     */
    public String getHeaderSender() {
        return headerSender;
    }

    /**
     * 设置headerSender
     * 
     * @param headerSender headerSender
     */
    public void setHeaderSender(String headerSender) {
        this.headerSender = headerSender;
    }

    /**
     * 获取headerReceiver
     * 
     * @return headerReceiver
     */
    public String getHeaderReceiver() {
        return headerReceiver;
    }

    /**
     * 设置headerReceiver
     * 
     * @param headerReceiver headerReceiver
     */
    public void setHeaderReceiver(String headerReceiver) {
        this.headerReceiver = headerReceiver;
    }

    /**
     * 获取headerDtsend
     * 
     * @return headerDtsend
     */
    public String getHeaderDtsend() {
        return headerDtsend;
    }

    /**
     * 设置headerDtsend
     * 
     * @param headerDtsend headerDtsend
     */
    public void setHeaderDtsend(String headerDtsend) {
        this.headerDtsend = headerDtsend;
    }

    /**
     * 获取detailInterfaceO
     * 
     * @return detailInterfaceO
     */
    public String getDetailInterfaceO() {
        return detailInterfaceO;
    }

    /**
     * 设置detailInterfaceO
     * 
     * @param detailInterfaceO detailInterfaceO
     */
    public void setDetailInterfaceO(String detailInterfaceO) {
        this.detailInterfaceO = detailInterfaceO;
    }

    /**
     * 获取detailMessageO
     * 
     * @return detailMessageO
     */
    public String getDetailMessageO() {
        return detailMessageO;
    }

    /**
     * 设置detailMessageO
     * 
     * @param detailMessageO detailMessageO
     */
    public void setDetailMessageO(String detailMessageO) {
        this.detailMessageO = detailMessageO;
    }

    /**
     * 获取detailResult
     * 
     * @return detailResult
     */
    public String getDetailResult() {
        return detailResult;
    }

    /**
     * 设置detailResult
     * 
     * @param detailResult detailResult
     */
    public void setDetailResult(String detailResult) {
        this.detailResult = detailResult;
    }

    /**
     * 获取details
     * 
     * @return details
     */
    public Map<String, String> getDetails() {
        return details;
    }

    /**
     * 设置details
     * 
     * @param details details
     */
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    /**
     * 添加一条 Detail Datails 消息
     * 
     * @param key
     * @param value
     * @return
     */
    public DataBack addDetalisItem(String key, String value) {
        details.put(key, value);
        return this;
    }

    /**
     * 根据key删除一条 Detail Datails 消息
     * 
     * @param key
     * @return
     */
    public DataBack removeDetalisItem(String key) {
        if (details.containsKey(key)) {
            details.remove(key);
        }
        return this;
    }
    
    /**
     * {@inheritDoc}
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((headerDtsend == null) ? 0 : headerDtsend.hashCode());
        result = prime * result + ((headerInterfaceId == null) ? 0 : headerInterfaceId.hashCode());
        result = prime * result + ((headerMessageId == null) ? 0 : headerMessageId.hashCode());
        result = prime * result + ((headerReceiver == null) ? 0 : headerReceiver.hashCode());
        result = prime * result + ((headerSender == null) ? 0 : headerSender.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DataBack other = (DataBack) obj;
        if (headerDtsend == null) {
            if (other.headerDtsend != null)
                return false;
        } else if (!headerDtsend.equals(other.headerDtsend))
            return false;
        if (headerInterfaceId == null) {
            if (other.headerInterfaceId != null)
                return false;
        } else if (!headerInterfaceId.equals(other.headerInterfaceId))
            return false;
        if (headerMessageId == null) {
            if (other.headerMessageId != null)
                return false;
        } else if (!headerMessageId.equals(other.headerMessageId))
            return false;
        if (headerReceiver == null) {
            if (other.headerReceiver != null)
                return false;
        } else if (!headerReceiver.equals(other.headerReceiver))
            return false;
        if (headerSender == null) {
            if (other.headerSender != null)
                return false;
        } else if (!headerSender.equals(other.headerSender))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "DataBack [headerInterfaceId="
				+ headerInterfaceId + ", headerMessageId=" + headerMessageId
				+ ", headerSender=" + headerSender + ", headerReceiver="
				+ headerReceiver + ", headerDtsend=" + headerDtsend
				+ ", detailInterfaceO=" + detailInterfaceO
				+ ", detailMessageO=" + detailMessageO + ", detailResult="
				+ detailResult + ", details=" + details + "]";
	}
    
}
