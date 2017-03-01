package com.cisdi.ecm.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.data.DocInfo;

public interface DocInfoMapper {

	public int insertDocInfo(DocInfo docInfo);

	public int insertDefDocInfo(@Param("id") int id,
			@Param("fileId") int fileId, @Param("userName") String userName);

	public int insertFolderDocRt(@Param("id") long DocId,
			@Param("folderGuid") String folderGuid);

	public Map<String, Object> getDocInfoByFileId(long fileId);

	public DocInfo getDocInfoByDocId(long docId);

	public int addDocDownloadNum(@Param("id") long docId,
			@Param("count") int count);

	public int addDocViewNum(@Param("id") long docId, @Param("count") int count);

	public int addDocFavouriteNum(@Param("id") long docId,
			@Param("count") int count);

	public int insertUserEvent(@Param("docId") long docId,
			@Param("userName") String userName, @Param("event") String event);

	public Map<String, Object> selectEventDoc(@Param("docId") long docId,
			@Param("userName") String userName, @Param("event") String event);

	public int deleteFiles2Trashcan(@Param("list") List<FileChecked> listFiles);

	public List<FileChecked> getAllSubFileCheckeds(
			@Param("list") List<Map<String, Object>> listMaps);
}
