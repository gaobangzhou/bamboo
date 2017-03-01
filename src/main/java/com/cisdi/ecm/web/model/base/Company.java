package com.cisdi.ecm.web.model.base;

import java.sql.Date;

public class Company {
	private  int ouId;
	private String ouName;
	private String ouFullName;
	private Date startDate;
	private Date endDate;
	private String status;
	public int getOuId() {
		return ouId;
	}
	public void setOuId(int ouId) {
		this.ouId = ouId;
	}
	public String getOuName() {
		return ouName;
	}
	public void setOuName(String ouName) {
		this.ouName = ouName;
	}
	public String getOuFullName() {
		return ouFullName;
	}
	public void setOuFullName(String ouFullName) {
		this.ouFullName = ouFullName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
