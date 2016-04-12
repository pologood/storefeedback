package com.gome.storefeedback.entity;

import java.util.Date;

/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午2:48:23
 * @Copyright(c) gome inc Gome Co.,LTD
 */
/**
 * @author Gong.ZhiBin
 * @date 2015年1月22日下午2:52:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Goods {
    /** 商品编码 */
    private String goodsCode;

    /** 商品条码 */
    private String goodsBarcode;

    /** 商品名称 */
    private String goodsName;

    /** 规格型号 */
    private String specificationsModel;

    /** 商品分类 */
    private String goodsCategory;

    /** 品牌 */
    private String goodsBrand;

    /** 延保价格上限 */
    private String extendedWarrantyPriceFloor;

    /** 延保价格下限 */
    private String extendedWarrantyPriceCap;

    /** 销项税率 */
    private String outputTaxRate;

    /** 计量单位 */
    private String unitsOfMeasurement;

    /** 度量单位文本 */
    private String unitOfMeasureText;

    /** 是否经营赠品 */
    private Integer whetherBusinessGifts;

    /** 批次标识 */
    private String lotId;

    /** 商品属性 */
    private String productAttributes;

    /** 商品类型 */
    private String goodsClass;

    /** 产地 */
    private String placeOfOrigin;

    /** 重量（含包装) */
    private String goodsWeight;

    /** 高（mm） */
    private String goodsHeight;

    /** 一级分类 */
    private String categoryLevel1;

    /** 二级分类 */
    private String categoryLevel2;

    /** 三级分类 */
    private String categoryLevel3;

    /** 产品卖点 */
    private String selling;

    /** 更新标志 */
    private String updateFlag;

    /** 创建时间 */
    private Date createTime;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsBarcode() {
        return goodsBarcode;
    }

    public void setGoodsBarcode(String goodsBarcode) {
        this.goodsBarcode = goodsBarcode == null ? null : goodsBarcode.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getSpecificationsModel() {
        return specificationsModel;
    }

    public void setSpecificationsModel(String specificationsModel) {
        this.specificationsModel = specificationsModel == null ? null : specificationsModel.trim();
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory == null ? null : goodsCategory.trim();
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand == null ? null : goodsBrand.trim();
    }

    public String getExtendedWarrantyPriceFloor() {
        return extendedWarrantyPriceFloor;
    }

    public void setExtendedWarrantyPriceFloor(String extendedWarrantyPriceFloor) {
        this.extendedWarrantyPriceFloor = extendedWarrantyPriceFloor == null ? null : extendedWarrantyPriceFloor.trim();
    }

    public String getExtendedWarrantyPriceCap() {
        return extendedWarrantyPriceCap;
    }

    public void setExtendedWarrantyPriceCap(String extendedWarrantyPriceCap) {
        this.extendedWarrantyPriceCap = extendedWarrantyPriceCap == null ? null : extendedWarrantyPriceCap.trim();
    }

    public String getOutputTaxRate() {
        return outputTaxRate;
    }

    public void setOutputTaxRate(String outputTaxRate) {
        this.outputTaxRate = outputTaxRate == null ? null : outputTaxRate.trim();
    }

    public String getUnitsOfMeasurement() {
        return unitsOfMeasurement;
    }

    public void setUnitsOfMeasurement(String unitsOfMeasurement) {
        this.unitsOfMeasurement = unitsOfMeasurement == null ? null : unitsOfMeasurement.trim();
    }

    public String getUnitOfMeasureText() {
        return unitOfMeasureText;
    }

    public void setUnitOfMeasureText(String unitOfMeasureText) {
        this.unitOfMeasureText = unitOfMeasureText == null ? null : unitOfMeasureText.trim();
    }

    public Integer getWhetherBusinessGifts() {
        return whetherBusinessGifts;
    }

    public void setWhetherBusinessGifts(Integer whetherBusinessGifts) {
        this.whetherBusinessGifts = whetherBusinessGifts;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId == null ? null : lotId.trim();
    }

    public String getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(String productAttributes) {
        this.productAttributes = productAttributes == null ? null : productAttributes.trim();
    }

    public String getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(String goodsClass) {
        this.goodsClass = goodsClass == null ? null : goodsClass.trim();
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin == null ? null : placeOfOrigin.trim();
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight == null ? null : goodsWeight.trim();
    }

    public String getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(String goodsHeight) {
        this.goodsHeight = goodsHeight == null ? null : goodsHeight.trim();
    }

    public String getCategoryLevel1() {
        return categoryLevel1;
    }

    public void setCategoryLevel1(String categoryLevel1) {
        this.categoryLevel1 = categoryLevel1 == null ? null : categoryLevel1.trim();
    }

    public String getCategoryLevel2() {
        return categoryLevel2;
    }

    public void setCategoryLevel2(String categoryLevel2) {
        this.categoryLevel2 = categoryLevel2 == null ? null : categoryLevel2.trim();
    }

    public String getCategoryLevel3() {
        return categoryLevel3;
    }

    public void setCategoryLevel3(String categoryLevel3) {
        this.categoryLevel3 = categoryLevel3 == null ? null : categoryLevel3.trim();
    }

    public String getSelling() {
        return selling;
    }

    public void setSelling(String selling) {
        this.selling = selling == null ? null : selling.trim();
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((goodsCode == null) ? 0 : goodsCode.hashCode());
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
        Goods other = (Goods) obj;
        if (goodsCode == null) {
            if (other.goodsCode != null)
                return false;
        } else if (!goodsCode.equals(other.goodsCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Goods [goodsCode=" + goodsCode + ", goodsBarcode=" + goodsBarcode + ", goodsName=" + goodsName
                + ", specificationsModel=" + specificationsModel + ", goodsCategory=" + goodsCategory + ", goodsBrand="
                + goodsBrand + ", extendedWarrantyPriceFloor=" + extendedWarrantyPriceFloor
                + ", extendedWarrantyPriceCap=" + extendedWarrantyPriceCap + ", outputTaxRate=" + outputTaxRate
                + ", unitsOfMeasurement=" + unitsOfMeasurement + ", unitOfMeasureText=" + unitOfMeasureText
                + ", whetherBusinessGifts=" + whetherBusinessGifts + ", lotId=" + lotId + ", productAttributes="
                + productAttributes + ", goodsClass=" + goodsClass + ", placeOfOrigin=" + placeOfOrigin
                + ", goodsWeight=" + goodsWeight + ", goodsHeight=" + goodsHeight + ", categoryLevel1="
                + categoryLevel1 + ", categoryLevel2=" + categoryLevel2 + ", categoryLevel3=" + categoryLevel3
                + ", selling=" + selling + ", updateFlag=" + updateFlag + ", createTime=" + createTime + "]";
    }
    
    
}