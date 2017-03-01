package com.cisdi.ecm.web.service.data;

import java.util.List;
import java.util.Map;

import com.cisdi.ecm.web.model.data.FileInfo;
import com.cisdi.ecm.web.model.data.LinkInfo;

public interface FileInfoService {

	public List<Map<String, Object>> getFolderFilesByUserId(String userId,
			String folderGuid, String order, String orderby, String status);

	public Map<String, Object> delete2trashcan(long fileId, String userName);

	public Map<String, Object> shareFileById(long fileId);

	public Map<String, Object> deleteFileById(long fileId);

	public LinkInfo getLinkInfoById(long fileId);

	public FileInfo getFileInfoByDocId(long docId);

	public Object searchFolderFiles(String userName, String folderGuid,
			String order, String orderby, String status);

}
