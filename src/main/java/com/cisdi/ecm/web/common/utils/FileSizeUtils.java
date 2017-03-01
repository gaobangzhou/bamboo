package com.cisdi.ecm.web.common.utils;

public class FileSizeUtils {

	private static class SingletonHolder {
		static final FileSizeUtils instance = new FileSizeUtils();
	}

	/**
	 * 获取Singleton实例
	 * 
	 * @author hyzh
	 * @version 1.0, 2011-04-05
	 * @return StrUtil Singleton实例
	 */
	public static FileSizeUtils getInstance() {
		return SingletonHolder.instance;
	}

	public String getFileSizeStr(long fileSize) {
		
		String sFileSize = "";
		if (fileSize > 0) {
			double dFileSize = (double) fileSize;

			double kiloByte = dFileSize / 1024;
			if (kiloByte < 1) {
				return sFileSize + " Byte(s)";
			}
			double megaByte = kiloByte / 1024;
			if (megaByte < 1) {
				sFileSize = String.format("%.2f", kiloByte);
				return sFileSize + " KB";
			}

			double gigaByte = megaByte / 1024;
			if (gigaByte < 1) {
				sFileSize = String.format("%.2f", megaByte);
				return sFileSize + " MB";
			}

			double teraByte = gigaByte / 1024;
			if (teraByte < 1) {
				sFileSize = String.format("%.2f", gigaByte);
				return sFileSize + "GB";
			}

			sFileSize = String.format("%.2f", teraByte);
			return sFileSize + " TB";
		}
		return sFileSize;
	}
}
