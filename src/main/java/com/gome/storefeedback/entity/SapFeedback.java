package com.gome.storefeedback.entity;

import java.util.Date;

/**
 * @author Gong.ZhiBin
 * @date 2015年7月23日上午9:28:59
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class SapFeedback {

    /** 主键 */
    private String request;
    private Double datapakid;
    private Integer record;

    /** 一二级分部 */
    private String secondDivisionCode;// SALES_GRP;
    /** 分部 */
    private String divisionCode;// ZDEPTMNT;
    /** 大区 */
    private String regionCode;// ZREGION;
    /** 二级品类 */
    private String category2Code;// RPA_WGH2;
    /** 十大品类 */
    private String categoryCode;// ZMAT_CAT;
    /** 品牌 */
    private String brandCode;// PROD_HIER;
    /** 商品 */
    private String goodsCode;// ZARTICLE;
    /** 日历天 */
    private Date dataDate;// CALDAY;
    /** 库存数量 */
    private Double stock;// ZINV_QTY;
    /** 计量的单位 */
    private String unit;// UNIT;
    /** 22.考核销售收入 */
    private Double saleIncome;// ZKI01148;
    /** 23.销售数量 */
    private Double saleNum;// ZKI01153;
    /** 采购类型(集采/地采) */
    private String buyType;// ZPUR_TYPE;
    /** 缺货率 */
    private Double feedbackRate;// ZRATE;
    /** 是否缺货标识（Q 缺 / D 断） */
    private String feedbackStatus;// FLAG;
    /** 含税库存金额 */
    private Double stockAmountWithTax;// ZINV_AMB;
    /** 货币名称代码 */
    private String currency;// CURRENCY;

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

    public String getSecondDivisionCode() {
        return secondDivisionCode;
    }

    public void setSecondDivisionCode(String secondDivisionCode) {
        this.secondDivisionCode = secondDivisionCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCategory2Code() {
        return category2Code;
    }

    public void setCategory2Code(String category2Code) {
        this.category2Code = category2Code;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getSaleIncome() {
        return saleIncome;
    }

    public void setSaleIncome(Double saleIncome) {
        this.saleIncome = saleIncome;
    }

    public Double getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Double saleNum) {
        this.saleNum = saleNum;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public Double getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(Double feedbackRate) {
        this.feedbackRate = feedbackRate;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Double getStockAmountWithTax() {
        return stockAmountWithTax;
    }

    public void setStockAmountWithTax(Double stockAmountWithTax) {
        this.stockAmountWithTax = stockAmountWithTax;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((datapakid == null) ? 0 : datapakid.hashCode());
        result = prime * result + ((record == null) ? 0 : record.hashCode());
        result = prime * result + ((request == null) ? 0 : request.hashCode());
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
        SapFeedback other = (SapFeedback) obj;
        if (datapakid == null) {
            if (other.datapakid != null)
                return false;
        } else if (!datapakid.equals(other.datapakid))
            return false;
        if (record == null) {
            if (other.record != null)
                return false;
        } else if (!record.equals(other.record))
            return false;
        if (request == null) {
            if (other.request != null)
                return false;
        } else if (!request.equals(other.request))
            return false;
        return true;
    }

}
