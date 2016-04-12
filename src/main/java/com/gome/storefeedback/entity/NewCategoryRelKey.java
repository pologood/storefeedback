package com.gome.storefeedback.entity;

public class NewCategoryRelKey {
    private String id;

    private String handleEmpId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHandleEmpId() {
        return handleEmpId;
    }

    public void setHandleEmpId(String handleEmpId) {
        this.handleEmpId = handleEmpId == null ? null : handleEmpId.trim();
    }
}