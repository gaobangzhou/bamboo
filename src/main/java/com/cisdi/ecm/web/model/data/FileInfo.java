package com.cisdi.ecm.web.model.data;

public class FileInfo {
	private int id;
	private String fileName;
	private String originalName;
	private String filePrefix = "";
	private String fileSuffix = "";
	private long fileSize;
	private String filePath;
	private String code;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String toJosnString() {

		String jString = "{\"code\":\"" + this.code + "\",\"fileId\":\""
				+ this.id + "\",\"originalFilename\":\""
				+ this.originalName + "\",\"filePrefix\":\"" + this.filePrefix
				+ "\",\"fileSuffix\":\"" + fileSuffix + "\",\"fileSize\":"
				+ this.fileSize + "}";

		return jString;
	}
}
