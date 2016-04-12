package com.gome.storefeedback.entity;
/**
 * 缺断货考核各员工奖惩实体(推送HR)
 * @author zhaozhenxiu
 *
 */
public class CheckSapFeedbackEmpCount {
	
	private String empNumber;
    private String sanctionMonth;
    private String sanctionMoney;
    private String sanctionPoints;
	public String getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	public String getSanctionMonth() {
		return sanctionMonth;
	}
	public void setSanctionMonth(String sanctionMonth) {
		this.sanctionMonth = sanctionMonth;
	}
	public String getSanctionMoney() {
		return sanctionMoney;
	}
	public void setSanctionMoney(String sanctionMoney) {
		this.sanctionMoney = sanctionMoney;
	}
	public String getSanctionPoints() {
		return sanctionPoints;
	}
	public void setSanctionPoints(String sanctionPoints) {
		this.sanctionPoints = sanctionPoints;
	}
    
    
}
