package com.cisdi.ecm.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.condition.FolderCondition;
import com.cisdi.ecm.web.model.data.Folder;

public interface FolderMapper {

	public Folder selectUserRootFolder(String userName);

	public Map<String, Object> selectUserFolder(
			@Param("userName") String userName,
			@Param("folderGuid") String folderGuid);

	public List<Folder> getFoldersByParentId(@Param("guid") String guid,
			@Param("ouid") int ouid) throws Exception;

	public List<Folder> getSubFoldersByFolder(Folder folder) throws Exception;

	public List<Map<String, Object>> getSubFolderMaps(@Param("guid") String guid);

	public List<Folder> getSubFoldersByFolderId(@Param("guid") String guid,
			@Param("ouid") int ouid) throws Exception;

	public List<Folder> queryFoldersByCondtion(FolderCondition folderCondition);

	public Folder selectFolderByParentGuidAndName(
			@Param("parentGuid") String parentGuid,
			@Param("folderName") String folderName,
			@Param("userName") String userName);

	public Map<String, Object> selectFolderRegExp(
			@Param("parentGuid") String parentGuid,
			@Param("folderName") String folderName,
			@Param("userName") String userName);

	public int queryFoldersCountByCondition(FolderCondition folderCondition);

	public void updateFolder(Folder folder) throws Exception;

	public void createFolder(Folder folder) throws Exception;

	public void createFolderWithGuid(Folder folder) throws Exception;

	public int createNewFolder(@Param("parentGuid") String parentGuid,
			@Param("folderName") String folderName,
			@Param("userName") String userName);

	public int updateFolderInTrashcan(@Param("folderGuid") String folderGuid,
			@Param("userName") String userName,
			@Param("inTrashcan") String inTrashcan);

	public void createUserRootFolder(String userName) throws Exception;

	public void deleteFolderById(String guid) throws Exception;

	public void deleteFolder(Folder folder) throws Exception;

	public Folder getFolderById(String guid) throws Exception;

	public void moveFolders(@Param("targetFolderGuid") String targetFolderGuid,
			@Param("selectFolders") List<Folder> selectFolders);

	public void spreadACL2AllFolders(
			@Param("rootFolderGuid") String rootFolderGuid,
			@Param("userACL") String userACL, @Param("aliasACL") String aliasACL);

	public void spreadACL2AllDocs(
			@Param("rootFolderGuid") String rootFolderGuid,
			@Param("userACL") String userACL, @Param("aliasACL") String aliasACL);

	public Map<String, Object> getUserTreeRoot(String userName);

	public List<Map<String, Object>> getTreeSubFoldersByParentId(
			@Param("parentId") String parentId,
			@Param("userName") String userName);

	public int moveFiles2Folder(@Param("folderGuid") String folderGuid,
			@Param("list") List<FileChecked> files) throws Exception;

	public int deleteFolders2Transhcan(
			@Param("list") List<Map<String, Object>> allFolders)
			throws Exception;
}
