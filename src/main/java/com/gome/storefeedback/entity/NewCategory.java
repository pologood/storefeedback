package com.gome.storefeedback.entity;

import java.util.Date;

public class NewCategory {

	private String id;

    private String reportEmployeeId;

    private String reportCompanyId;

    private String reportEmployeeName;

    private String categoryCode;

    private String categoryDesc;

    private String modelCode;

    private String modelDesc;

    private String mainDesc;

    private String dealer;

    private Date createTime;

    private String isPush;

    private String isHandle;

    private String isImport;

    private String notImportDesc;

    private String notImportCode;

    private String googsCode;

    private String handleEmployeeId;

    private String handleEmployeeName;

    private String handleCompanyId;

    private Date handleTime;

    private Date importTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReportEmployeeId() {
        return reportEmployeeId;
    }

    public void setReportEmployeeId(String reportEmployeeId) {
        this.reportEmployeeId = reportEmployeeId == null ? null : reportEmployeeId.trim();
    }

    public String getReportCompanyId() {
        return reportCompanyId;
    }

    public void setReportCompanyId(String reportCompanyId) {
        this.reportCompanyId = reportCompanyId == null ? null : reportCompanyId.trim();
    }

    public String getReportEmployeeName() {
        return reportEmployeeName;
    }

    public void setReportEmployeeName(String reportEmployeeName) {
        this.reportEmployeeName = reportEmployeeName == null ? null : reportEmployeeName.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode == null ? null : modelCode.trim();
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc == null ? null : modelDesc.trim();
    }

    public String getMainDesc() {
        return mainDesc;
    }

    public void setMainDesc(String mainDesc) {
        this.mainDesc = mainDesc == null ? null : mainDesc.trim();
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer == null ? null : dealer.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsPush() {
        return isPush;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush == null ? null : isPush.trim();
    }

    public String getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(String isHandle) {
        this.isHandle = isHandle == null ? null : isHandle.trim();
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport == null ? null : isImport.trim();
    }

    public String getNotImportDesc() {
        return notImportDesc;
    }

    public void setNotImportDesc(String notImportDesc) {
        this.notImportDesc = notImportDesc == null ? null : notImportDesc.trim();
    }

    public String getNotImportCode() {
        return notImportCode;
    }

    public void setNotImportCode(String notImportCode) {
        this.notImportCode = notImportCode == null ? null : notImportCode.trim();
    }

    public String getGoogsCode() {
        return googsCode;
    }

    public void setGoogsCode(String googsCode) {
        this.googsCode = googsCode == null ? null : googsCode.trim();
    }

    public String getHandleEmployeeId() {
        return handleEmployeeId;
    }

    public void setHandleEmployeeId(String handleEmployeeId) {
        this.handleEmployeeId = handleEmployeeId == null ? null : handleEmployeeId.trim();
    }

    public String getHandleEmployeeName() {
        return handleEmployeeName;
    }

    public void setHandleEmployeeName(String handleEmployeeName) {
        this.handleEmployeeName = handleEmployeeName == null ? null : handleEmployeeName.trim();
    }

    public String getHandleCompanyId() {
        return handleCompanyId;
    }

    public void setHandleCompanyId(String handleCompanyId) {
        this.handleCompanyId = handleCompanyId == null ? null : handleCompanyId.trim();
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }
}