package com.cisdi.ecm.web.model.data;

public class DocType {
	private int id;
	private String docTypeCode;
	private String parantTypeCode;
	private String docTypeName;
	private String isMainType;
	private String docTypeDescription;
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getParantTypeCode() {
		return parantTypeCode;
	}
	public void setParantTypeCode(String parantTypeCode) {
		this.parantTypeCode = parantTypeCode;
	}
	public String getDocTypeName() {
		return docTypeName;
	}
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsMainType() {
		return isMainType;
	}
	public void setIsMainType(String isMainType) {
		this.isMainType = isMainType;
	}
	public String getDocTypeDescription() {
		return docTypeDescription;
	}
	public void setDocTypeDescription(String docTypeDescription) {
		this.docTypeDescription = docTypeDescription;
	}
	
}
