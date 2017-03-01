package com.cisdi.ecm.web.service.file;

import java.io.InputStream;

import com.cisdi.ecm.web.model.data.FileInfo;

public interface FileWebService {

	public FileInfo getFileByFileId(Long fileId);

	public long initUploadFile(FileInfo fileInfo, String userName,
			String folderGuid) throws Exception;

	public String getUserFilePath(String userName);

	public InputStream getFileInputStreamByDocId(long docId) throws Exception;
}
