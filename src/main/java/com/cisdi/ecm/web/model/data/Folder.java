package com.cisdi.ecm.web.model.data;

import java.sql.Date;

public class Folder {
	
	private String folderGuid;//文件夹guid
	private String parentGuid;//文件夹父guid
	private String folderName;//文件夹名称
	private String folderCreator;//创建人
	private Date createDate;//创建时间
	private String lastModifier;//最后更新人
	private Date lastModifiedDate;//最后更新时间
	private String folderGroup;//文件夹所属组
	private String folderUserACL;//ACL用户
	private String folderAliasACL;//ACL别名组
	private int folderOU;//所属OU
	private String isTemplate;//是否为模版文件
	private String isSecurity;//是否为保密文加件
	public String getFolderGuid() {
		return folderGuid;
	}
	public void setFolderGuid(String folderGuid) {
		this.folderGuid = folderGuid;
	}
	public String getParentGuid() {
		return parentGuid;
	}
	public void setParentGuid(String parentGuid) {
		this.parentGuid = parentGuid;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getFolderCreator() {
		return folderCreator;
	}
	public void setFolderCreator(String folderCreator) {
		this.folderCreator = folderCreator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLastModifier() {
		return lastModifier;
	}
	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getFolderGroup() {
		return folderGroup;
	}
	public void setFolderGroup(String folderGroup) {
		this.folderGroup = folderGroup;
	}
	public String getFolderUserACL() {
		return folderUserACL;
	}
	public void setFolderUserACL(String folderUserACL) {
		this.folderUserACL = folderUserACL;
	}
	public String getFolderAliasACL() {
		return folderAliasACL;
	}
	public void setFolderAliasACL(String folderAliasACL) {
		this.folderAliasACL = folderAliasACL;
	}

	public int getFolderOU() {
		return folderOU;
	}
	public void setFolderOU(int folderOU) {
		this.folderOU = folderOU;
	}
	public String getIsTemplate() {
		return isTemplate;
	}
	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}
	public String getIsSecurity() {
		return isSecurity;
	}
	public void setIsSecurity(String isSecurity) {
		this.isSecurity = isSecurity;
	}
	

}
