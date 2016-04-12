package com.gome.gsm.entity.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工扩展
 * 
 * @author Zhang.Mingji
 * @date 2014年9月2日下午2:00:30
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class EmployeeExt implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8971061130180386234L;
    /**
     * 主键
     */
    private String id;
    /**
     * 员工编号
     */
    private String employeeId;
    /**
     * 序列号
     */
    private String serialNumber;
    /**
     * 员工姓名
     */
    private String employeeName;
    /**
     * AD域账号
     */
    private String adAcount;
    /**
     * AD域密码
     */
    private String adAcountPwd;
    /**
     * 应用系统账号
     */
    private String appAcount;
    /**
     * 应用系统密码
     */
    private String appAcountPwd;
    /**
     * 公司代码
     */
    private String companyCode;
    /**
     * 移动手机号码
     */
    private String mobile;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 员工状态
     */
    private int status;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司Id
     */
    private String companyId;
    /**
     * 审核状态
     */
    private int verifyStatus;
    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 门店名称
     */
    private String storeName;
    
    
    
    public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		if(null != storeCode && !"".equals(storeCode)){
			this.storeCode = storeCode.substring(0, 4);
		}
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
     * 职位
     */
    private List<Position> positions = new ArrayList<Position>();
    
    public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public String getId() {
    	return id;
    }
    
    public int getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(int verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public void setId(String id) {
    	this.id = id;
    }

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getAdAcount() {
        return adAcount;
    }

    public void setAdAcount(String adAcount) {
        this.adAcount = adAcount == null ? null : adAcount.trim();
    }

    public String getAdAcountPwd() {
        return adAcountPwd;
    }

    public void setAdAcountPwd(String adAcountPwd) {
        this.adAcountPwd = adAcountPwd == null ? null : adAcountPwd.trim();
    }

    public String getAppAcount() {
        return appAcount;
    }

    public void setAppAcount(String appAcount) {
        this.appAcount = appAcount == null ? null : appAcount.trim();
    }

    public String getAppAcountPwd() {
        return appAcountPwd;
    }

    public void setAppAcountPwd(String appAcountPwd) {
        this.appAcountPwd = appAcountPwd == null ? null : appAcountPwd.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

/*	public List<AppInfo> getAppInfoList() {
        return appInfoList;
    }

    public void setAppInfoList(List<AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }
*/
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
    
    
}