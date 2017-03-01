package com.cisdi.ecm.web.service.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ecm.web.dao.DocInfoMapper;
import com.cisdi.ecm.web.dao.FolderMapper;
import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.condition.FolderCondition;
import com.cisdi.ecm.web.model.data.Folder;
import com.cisdi.ecm.web.service.data.FolderService;

@Service
public class FolderServiceImpl implements FolderService {

	@Resource
	private FolderMapper folderMapper;
	@Resource
	private DocInfoMapper docInfoMapper;

	@Override
	public Folder getFolderById(String guid) throws Exception {
		// TODO Auto-generated method stub
		return folderMapper.getFolderById(guid);
	}

	@Override
	public Folder getFolderByProjectNum(String projectNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Folder> getFoldersByParentId(String guid, int ouid)
			throws Exception {
		// TODO Auto-generated method stub
		return folderMapper.getFoldersByParentId(guid, ouid);
	}

	@Override
	public List<Folder> getSubFoldersByFolder(Folder folder) throws Exception {
		// TODO Auto-generated method stub
		return folderMapper.getSubFoldersByFolder(folder);
	}

	@Override
	public List<Folder> getSubFoldersByFolderId(String guid, int ouid)
			throws Exception {
		// TODO Auto-generated method stub
		return folderMapper.getSubFoldersByFolderId(guid, ouid);
	}

	@Override
	public List<Folder> queryFoldersByCondtion(FolderCondition folderCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryFoldersCountByCondition(FolderCondition folderCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateFolder(Folder folder) throws Exception {
		// TODO Auto-generated method stub
		folderMapper.updateFolder(folder);
	}

	@Override
	public void deletFolderById(String guid) throws Exception {
		folderMapper.deleteFolderById(guid);

	}

	@Override
	public void deletFolder(Folder folder) throws Exception {
		folderMapper.deleteFolder(folder);
	}

	@Override
	public void moveFolders(String targetFolderGuid, List<Folder> selectFolders) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spreadACL2AllFolders(String rootFolderGuid, String userACL,
			String aliasACL) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spreadACL2AllDocs(String rootFolderGuid, String userACL,
			String aliasACL) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFoldersJsonByParentId(String guid, int ouid)
			throws Exception {
		List<Folder> folders = folderMapper.getFoldersByParentId(guid, ouid);
		JSONArray jsonArr = new JSONArray();
		for (Folder folder : folders) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", folder.getFolderGuid());
			jsonObject.put("name", folder.getFolderName());
			jsonObject.put("open", false);
			jsonObject.put("isParent", true);
			jsonObject.put("type", "TYPE");
			jsonObject.put("pid", guid);
			jsonArr.add(jsonObject);
		}
		return jsonArr.toString();
	}

	@Override
	public Folder getUserRootFolder(String userName) {

		return folderMapper.selectUserRootFolder(userName);
	}

	/**
	 * 根据rootGuid获取所有的树路径上的节点直到"/"
	 */
	@Override
	public List<Map<String, Object>> getFolderPathList(String userName,
			String rootFolderGuid) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		return getFolderPathList(userName, rootFolderGuid, result);
	}

	private List<Map<String, Object>> getFolderPathList(String userName,
			String rootFolderGuid, List<Map<String, Object>> lMaps) {
		Map<String, Object> map = folderMapper.selectUserFolder(userName,
				rootFolderGuid);
		lMaps.add(map);
		if (map.get("parentguid").equals("CLD_ROOT")) {
			return lMaps;
		} else {
			return getFolderPathList(userName, (String) map.get("parentguid"),
					lMaps);
		}

	}

	@Override
	@Transactional
	public Boolean createFolder(String folderName, String parentGuid,
			String userName) throws Exception {
		Folder folder = folderMapper.selectFolderByParentGuidAndName(
				parentGuid, folderName, userName);
		if (folder != null) {
			// 搜索是否有xxx(N)的文件夹名称
			Map<String, Object> map = folderMapper.selectFolderRegExp(
					parentGuid, "^" + folderName + "\\([0-9]{1,3}\\)$",
					userName);
			int tNum = 1;
			if (map != null && !map.isEmpty()) {// 获取最新建的xxx(N)的文件夹，信息
				String maxNameString = map.get("folder_name").toString();
				String num = maxNameString.substring(
						maxNameString.indexOf("(") + 1,
						maxNameString.indexOf(")"));

				if (num != "") {
					tNum = Integer.parseInt(num) + 1;// 新建xxx(N+1)的文件夹
				}
			}

			int result = folderMapper.createNewFolder(parentGuid, folderName
					+ "(" + tNum + ")", userName);
			if (result != 1) {
				return false;
			}
		} else {
			int result = folderMapper.createNewFolder(parentGuid, folderName,
					userName);
			if (result != 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	@Transactional
	public Boolean deleteFolder2Trashcan(String folderGuid, String userName)
			throws Exception {
		List<FileChecked> allFiles = new ArrayList<FileChecked>();
		List<Map<String, Object>> allFolders = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> subFoldersList = this
				.getAllSubFolders(folderGuid);
		List<FileChecked> subCheckeds = docInfoMapper
				.getAllSubFileCheckeds(subFoldersList);
		allFolders.addAll(subFoldersList);
		allFiles.addAll(subCheckeds);
		if (allFiles.size() > 0) {
			int resDoc = docInfoMapper.deleteFiles2Trashcan(allFiles);
			if (resDoc == 0) {
				return false;
			}
		}
		if (allFolders.size() > 0) {
			int resFolder = folderMapper.deleteFolders2Transhcan(allFolders);
			if (resFolder == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Map<String, Object> getTreeRootFolders(String userName) {

		Map<String, Object> rootFolder = folderMapper.getUserTreeRoot(userName);
		List<Map<String, Object>> folders = folderMapper
				.getTreeSubFoldersByParentId(rootFolder.get("id").toString(),
						userName);
		if (folders != null && !folders.isEmpty()) {
			rootFolder.put("children", folders);
		}

		return rootFolder;

	}

	@Override
	public String getTreeSubFolders(String parentId, String userName) {
		List<Map<String, Object>> FolderList = folderMapper
				.getTreeSubFoldersByParentId(parentId, userName);
		JSONArray jsonArr = new JSONArray();
		for (Map<String, Object> map : FolderList) {
			JSONObject jsonObject = new JSONObject();
			for (String key : map.keySet()) {
				jsonObject.put(key, map.get(key));
			}
			jsonArr.add(jsonObject);
		}
		return jsonArr.toString();
	}

	@Override
	@Transactional
	public int moveFiles(String folderGuid, List<FileChecked> listFiles)
			throws Exception {

		return folderMapper.moveFiles2Folder(folderGuid, listFiles);

	}

	@Override
	public List<Map<String, Object>> getAllSubFolders(String guid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("folder_guid", guid);
		map.put("type", "folder");
		list.add(map);
		list = getSubFolderMaps(guid, list);
		return list;
	}

	private List<Map<String, Object>> getSubFolderMaps(String guid,
			List<Map<String, Object>> allMaps) {
		List<Map<String, Object>> subMaps = folderMapper.getSubFolderMaps(guid);
		if (subMaps != null && subMaps.size() > 0) {
			allMaps.addAll(subMaps);
			for (Map<String, Object> map : subMaps) {
				allMaps = getSubFolderMaps(map.get("folder_guid").toString(),
						allMaps);
			}
		}
		return allMaps;
	}
}
