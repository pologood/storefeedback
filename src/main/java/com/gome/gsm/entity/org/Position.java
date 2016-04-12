package com.gome.gsm.entity.org;

import java.io.Serializable;
/**
 * 职位
 * @author Zhang.Mingji
 * @date 2014年9月2日下午2:00:54
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class Position implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5536590257091163762L;
	/**
	 * 岗位ID
	 */
	private String positionId;
	/**
	 * 总部代码
	 */
    private String headquatersCode;
    /**
     * 主副岗
     */
    private Byte primaryPosition;
    /**
     * 总部名称
     */
    private String headquatersName;
    /**
     * 大区代码
     */
    private String regionCode;
    /**
     * 大区名称
     */
    private String regionName;
    /**
     * 一级分部代码
     */
    private String firstSubsectionCode;
    /**
     * 一级分部名称
     */
    private String firstSubsectionName;
    /**
     * 二级分部代码
     */
    private String secondSubsectionCode;
    /**
     * 二级分部名称
     */
    private String secondSubsectionName;
    /**
     * 门店代码
     */
    private String storeCode;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 部门代码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 岗位编码
     */
    private String positionCode;
    /**
     * 岗位描述
     */
    private String positionDesc;
    /**
     * 组织层级
     */
    private String level;
    /**
     * 组织层级细分
     */
    private String levelDetail;
    /**
     * 公司ID
     */
    private String companyId;
    /**
     * 职务Id
     */
    private String ministrationId;
    
    
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 职务描述
     */
    private String ministrationDesc;
    /**
     * 职务状态
     */
    private String status;
    
    public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMinistrationDesc() {
		return ministrationDesc;
	}

	public void setMinistrationDesc(String ministrationDesc) {
		this.ministrationDesc = ministrationDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMinistrationId() {
		return ministrationId;
	}

	public void setMinistrationId(String ministrationId) {
		this.ministrationId = ministrationId;
	}

	public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    public String getHeadquatersCode() {
        return headquatersCode;
    }

    public void setHeadquatersCode(String headquatersCode) {
        this.headquatersCode = headquatersCode == null ? null : headquatersCode.trim();
    }

    public Byte getPrimaryPosition() {
        return primaryPosition;
    }

    public void setPrimaryPosition(Byte primaryPosition) {
        this.primaryPosition = primaryPosition;
    }

    public String getHeadquatersName() {
        return headquatersName;
    }

    public void setHeadquatersName(String headquatersName) {
        this.headquatersName = headquatersName == null ? null : headquatersName.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getFirstSubsectionCode() {
        return firstSubsectionCode;
    }

    public void setFirstSubsectionCode(String firstSubsectionCode) {
        this.firstSubsectionCode = firstSubsectionCode == null ? null : firstSubsectionCode.trim();
    }

    public String getFirstSubsectionName() {
        return firstSubsectionName;
    }

    public void setFirstSubsectionName(String firstSubsectionName) {
        this.firstSubsectionName = firstSubsectionName == null ? null : firstSubsectionName.trim();
    }

    public String getSecondSubsectionCode() {
        return secondSubsectionCode;
    }

    public void setSecondSubsectionCode(String secondSubsectionCode) {
        this.secondSubsectionCode = secondSubsectionCode == null ? null : secondSubsectionCode.trim();
    }

    public String getSecondSubsectionName() {
        return secondSubsectionName;
    }

    public void setSecondSubsectionName(String secondSubsectionName) {
        this.secondSubsectionName = secondSubsectionName == null ? null : secondSubsectionName.trim();
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc == null ? null : positionDesc.trim();
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getLevelDetail() {
        return levelDetail;
    }

    public void setLevelDetail(String levelDetail) {
        this.levelDetail = levelDetail == null ? null : levelDetail.trim();
    }

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
    
    

}