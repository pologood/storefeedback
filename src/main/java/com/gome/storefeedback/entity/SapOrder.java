package com.gome.storefeedback.entity;

import java.util.Date;

public class SapOrder {
    
    /** 主键 */
    private Long id;
    /** <EBELN>采购凭证 string 10</EBELN> */
    private String orderId;
    /** <BEDAT>凭证日期 string 8</BEDAT> */
    private String orderDate;
    /** <EBELP>行项目 string 5</EBELP> */
    private String orderContent;
    /** <MATNR>商品 string 18</MATNR> */
    private String goodsCode;
    /** <TXZ01>商品描述 string 40</TXZ01> */
    private String goodsCnText;
    /** <MENGE>数量（采购订单数量） integer 13</MENGE> */
    private Long orderNum;
    /** <DABMG>到货数量（已收货数量） integer 13</DABMG> */
    private Long orderToNum;
    /** <BUDAT>最近收货日期 string 8</BUDAT> */
    private String lastReceiveDate;
    /** <EINDT>计划交货日期 string 8</EINDT> */
    private String planDate;
    /** <ZDABMG>在途数量 integer 13</ZDABMG> */
    private Long onTheRoadNum;
    /** <ELIKZ>交货标识 string 1</ELIKZ> */
    private String orderFlag;
    /** <BSART>凭证类型 string 4</BSART> */
    private String orderType;
    /** <WERKS>地点 string 4</WERKS> */
    private String placeId;
    /** <NAME1>地点描述 string 30</NAME1> */
    private String placeName;
    /** <LGORT>库位 string 4</LGORT> */
    private String stockTypeId;
    /** <LGOBE>库位描述 string 16</LGOBE> */
    private String stockTypeName;
    
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** <ZCQ>string 1</ZCQ> */
    private String zcqDes;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getOrderContent() {
        return orderContent;
    }
    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }
    public String getGoodsCode() {
        return goodsCode;
    }
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
    public String getGoodsCnText() {
        return goodsCnText;
    }
    public void setGoodsCnText(String goodsCnText) {
        this.goodsCnText = goodsCnText;
    }
    public Long getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }
    public Long getOrderToNum() {
        return orderToNum;
    }
    public void setOrderToNum(Long orderToNum) {
        this.orderToNum = orderToNum;
    }
    public String getLastReceiveDate() {
        return lastReceiveDate;
    }
    public void setLastReceiveDate(String lastReceiveDate) {
        this.lastReceiveDate = lastReceiveDate;
    }
    public String getPlanDate() {
        return planDate;
    }
    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }
    public Long getOnTheRoadNum() {
        return onTheRoadNum;
    }
    public void setOnTheRoadNum(Long onTheRoadNum) {
        this.onTheRoadNum = onTheRoadNum;
    }
    public String getOrderFlag() {
        return orderFlag;
    }
    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getPlaceId() {
        return placeId;
    }
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public String getStockTypeId() {
        return stockTypeId;
    }
    public void setStockTypeId(String stockTypeId) {
        this.stockTypeId = stockTypeId;
    }
    public String getStockTypeName() {
        return stockTypeName;
    }
    public void setStockTypeName(String stockTypeName) {
        this.stockTypeName = stockTypeName;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
	public String getZcqDes() {
		return zcqDes;
	}
	public void setZcqDes(String zcqDes) {
		this.zcqDes = zcqDes;
	}

}
