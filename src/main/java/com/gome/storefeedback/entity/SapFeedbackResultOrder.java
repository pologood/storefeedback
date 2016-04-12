package com.gome.storefeedback.entity;

/**
 * 处理和采购订单号的关系
 * 
 * @author Gong.ZhiBin
 * @date 2015年7月23日下午7:09:36
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class SapFeedbackResultOrder {
    /** 缺断货信息外键 */
    private String id;
    /** 采购订单号 */
    private String orderId;
    /** 采购订单 商品编码 */
    private Long sapOrderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getSapOrderId() {
        return sapOrderId;
    }

    public void setSapOrderId(Long sapOrderId) {
        this.sapOrderId = sapOrderId;
    }
}
