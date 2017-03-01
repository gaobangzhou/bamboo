package com.cisdi.ecm.web.model.data;

import java.sql.Date;

public class ShareSetting {

	private long id;
	private long docId;
	private long timeCode;
	private String shareCode;
	private String needSign;
	private String canDownload;
	private String canReview;
	private String needPassword;
	private String password;
	private Date expirationDate;
	private String status;
	private String aliasIds;
	private Date createDate;
	private String createdBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDocId() {
		return docId;
	}

	public void setDocId(long docId) {
		this.docId = docId;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getNeedSign() {
		return needSign;
	}

	public void setNeedSign(String needSign) {
		this.needSign = needSign;
	}

	public String getCanDownload() {
		return canDownload;
	}

	public void setCanDownload(String canDownload) {
		this.canDownload = canDownload;
	}

	public String getCanReview() {
		return canReview;
	}

	public void setCanReview(String canReview) {
		this.canReview = canReview;
	}

	public String getNeedPassword() {
		return needPassword;
	}

	public void setNeedPassword(String needPassword) {
		this.needPassword = needPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAliasIds() {
		return aliasIds;
	}

	public void setAliasIds(String aliasIds) {
		this.aliasIds = aliasIds;
	}

	public long getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(long timeCode) {
		this.timeCode = timeCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
