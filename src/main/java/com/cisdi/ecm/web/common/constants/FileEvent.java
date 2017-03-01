package com.cisdi.ecm.web.common.constants;

public enum FileEvent {

	DOWNLOAD("downlaod"), VIEW("view"), FAVOUR("favour"), SHARE("share");

	FileEvent(String code) {
		this.code = code;
	}

	private String code = "";

	public String getCode() {
		return this.code;
	}

	public static FileEvent getEnum(String code) {
		FileEvent returnEnum = null;
		if ("downlaod".equalsIgnoreCase(code)) {
			returnEnum = FileEvent.DOWNLOAD;
		} else if ("view".equalsIgnoreCase(code)) {
			returnEnum = FileEvent.VIEW;
		} else if ("favour".equalsIgnoreCase(code)) {
			returnEnum = FileEvent.FAVOUR;
		} else if ("share".equalsIgnoreCase(code)) {
			returnEnum = FileEvent.SHARE;
		}
		return returnEnum;
	}
}
