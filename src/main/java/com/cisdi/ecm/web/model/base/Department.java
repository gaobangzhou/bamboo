package com.cisdi.ecm.web.model.base;

public class Department {
	private int id;
	private int parentDeptId;
	private int deptId;
	private String deptName;
	private String deptCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentDeptId() {
		return parentDeptId;
	}
	public void setParentDeptId(int parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	
}
