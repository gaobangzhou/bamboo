package com.cisdi.ecm.web.service.data;

import java.util.List;
import java.util.Map;

import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.condition.FolderCondition;
import com.cisdi.ecm.web.model.data.Folder;

public interface FolderService {

	public Folder getUserRootFolder(String userName);

	public Folder getFolderById(String guid) throws Exception;

	public Folder getFolderByProjectNum(String projectNum);

	public List<Map<String, Object>> getFolderPathList(String userName,
			String rootFolderGuid);

	public List<Folder> getFoldersByParentId(String guid, int ouid)
			throws Exception;

	public String getFoldersJsonByParentId(String guid, int ouid)
			throws Exception;

	public List<Folder> getSubFoldersByFolder(Folder folder) throws Exception;

	public List<Folder> getSubFoldersByFolderId(String guid, int ouid)
			throws Exception;

	public List<Folder> queryFoldersByCondtion(FolderCondition folderCondition);

	public int queryFoldersCountByCondition(FolderCondition folderCondition);

	public void updateFolder(Folder folder) throws Exception;

	public Boolean createFolder(String folderName, String parentGuid,
			String userName) throws Exception;

	public Boolean deleteFolder2Trashcan(String folderGuid, String userName)
			throws Exception;

	public void deletFolderById(String guid) throws Exception;

	public void deletFolder(Folder folder) throws Exception;

	public void moveFolders(String targetFolderGuid, List<Folder> selectFolders);

	public void spreadACL2AllFolders(String rootFolderGuid, String userACL,
			String aliasACL);

	public void spreadACL2AllDocs(String rootFolderGuid, String userACL,
			String aliasACL);

	public Map<String, Object> getTreeRootFolders(String userName);

	public String getTreeSubFolders(String parentId, String userName);

	public int moveFiles(String folderGuid, List<FileChecked> listFiles)
			throws Exception;

	public List<Map<String, Object>> getAllSubFolders(String guid);

}
