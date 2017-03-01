package com.cisdi.ecm.web.model.data;

import java.util.Date;

public class FileRow {

	private int id;
	private String type;
	private String name;
	private long size;
	private String sizeStr;
	private Date time;
	private String share;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getSizeStr() {

		return getFileSizeStr(this.size);
	}

	public void setSizeStr(String sizeStr) {
		this.sizeStr = sizeStr;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	private static String getFileSizeStr(long fileSize) {

		String sFileSize = "";
		if (fileSize > 0) {
			double dFileSize = (double) fileSize;

			double kiloByte = dFileSize / 1024;
			if (kiloByte < 1) {
				return sFileSize + "Byte(s)";
			}
			double megaByte = kiloByte / 1024;
			if (megaByte < 1) {
				sFileSize = String.format("%.2f", kiloByte);
				return sFileSize + "KB";
			}

			double gigaByte = megaByte / 1024;
			if (gigaByte < 1) {
				sFileSize = String.format("%.2f", megaByte);
				return sFileSize + "MB";
			}

			double teraByte = gigaByte / 1024;
			if (teraByte < 1) {
				sFileSize = String.format("%.2f", gigaByte);
				return sFileSize + "GB";
			}

			sFileSize = String.format("%.2f", teraByte);
			return sFileSize + "TB";
		}
		return sFileSize;
	}
	
	public String toString(){
		String jString = "{\"id\":\"" + this.id + "\",\"name\":\""
				+ this.name + "\",\"type\":\""
				+ this.type + "\",\"size\":\"" + this.getSizeStr()
				+ "\",\"time\":\"" + time + "\",\"share\":"
				+ this.share + "}";
		return jString;
	}
}
