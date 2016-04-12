package com.gome.storefeedback.entity;

public class NewImg {
    private String id;

    private String categoryModelId;

    private String type;

    private String imgDesc;

    private String sts;

    private String imgInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryModelId() {
        return categoryModelId;
    }

    public void setCategoryModelId(String categoryModelId) {
        this.categoryModelId = categoryModelId == null ? null : categoryModelId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc == null ? null : imgDesc.trim();
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts == null ? null : sts.trim();
    }

    public  String getImgInfo() {
        return imgInfo;
    }

    public void setImgInfo(String imgInfo) {
        this.imgInfo = imgInfo;
    }
}