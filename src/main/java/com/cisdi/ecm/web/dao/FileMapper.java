package com.cisdi.ecm.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.data.FileInfo;

public interface FileMapper {

	public int insertFile(FileInfo FileInfo);

	public List<Map<String, Object>> selectFolderFiles(
			@Param("userId") String userId,
			@Param("folderGuid") String folderGuid,
			@Param("order") String order, @Param("orderby") String orderby,
			@Param("status") String status);

	public int updateInTrashcan(@Param("fileId") long fileId,
			@Param("inTrashcan") String inTrashcan);

	public FileInfo getFileInfoByDocId(Long docId);

	public FileInfo getFileInfoByfileId(long fileId);

	public List<Map<String, Object>> searchFolderFiles(
			@Param("userName") String userName, @Param("order") String order,
			@Param("orderby") String orderby, @Param("status") String status,
			@Param("keyword") String keyword);

}
