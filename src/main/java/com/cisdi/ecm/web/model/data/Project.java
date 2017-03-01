package com.cisdi.ecm.web.model.data;

import java.util.Date;

public class Project {
	private String projectNum;
	private String projectName;
	private String projectFullName;
	private String projectType;
	private String projectStatusCode;
	private String projectStatusName;
	private String projectManager;
	private Date startDate;
	private Date closeDate;
	private String description;
	private Date creationDate;
	private String created_by;
	private Date lastUpdateDate;
	private String lastUpdateBy;
	private int ou_id;
	private int carryOutCompanyId;
	private String carryOutCompanyName;
	private String projectFolderGuid;
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectFullName() {
		return projectFullName;
	}
	public void setProjectFullName(String projectFullName) {
		this.projectFullName = projectFullName;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProjectStatusCode() {
		return projectStatusCode;
	}
	public void setProjectStatusCode(String projectStatusCode) {
		this.projectStatusCode = projectStatusCode;
	}
	public String getProjectStatusName() {
		return projectStatusName;
	}
	public void setProjectStatusName(String projectStatusName) {
		this.projectStatusName = projectStatusName;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public int getOu_id() {
		return ou_id;
	}
	public void setOu_id(int ou_id) {
		this.ou_id = ou_id;
	}
	public int getCarryOutCompanyId() {
		return carryOutCompanyId;
	}
	public void setCarryOutCompanyId(int carryOutCompanyId) {
		this.carryOutCompanyId = carryOutCompanyId;
	}
	public String getCarryOutCompanyName() {
		return carryOutCompanyName;
	}
	public void setCarryOutCompanyName(String carryOutCompanyName) {
		this.carryOutCompanyName = carryOutCompanyName;
	}
	public String getProjectFolderGuid() {
		return projectFolderGuid;
	}
	public void setProjectFolderGuid(String projectFolderGuid) {
		this.projectFolderGuid = projectFolderGuid;
	}
	
	
}
