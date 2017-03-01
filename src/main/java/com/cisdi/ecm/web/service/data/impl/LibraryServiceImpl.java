package com.cisdi.ecm.web.service.data.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import com.cisdi.ecm.web.common.constants.ServerConstants;
import com.cisdi.ecm.web.dao.FolderMapper;
import com.cisdi.ecm.web.model.data.Folder;
import com.cisdi.ecm.web.service.data.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{
	@Resource
	private FolderMapper folderMapper;

	@Override
	public String initTreeDataByOU(int ouId) throws Exception {
		List<Folder> folders =folderMapper.getSubFoldersByFolderId(ServerConstants.TREE_ROOT_GUID,ouId);
		
		return ListFolders2TreeJson(folders,"root");
	}
	
	public String ListFolders2TreeJson(List<Folder> folders,String type) {
		JSONArray jsonArr = new JSONArray();
		for (Folder folder : folders) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", folder.getFolderGuid());
			jsonObject.put("name", folder.getFolderName());
			jsonObject.put("open", false);
			jsonObject.put("isParent", true);
			jsonObject.put("type", type);
			jsonObject.put("pid", folder.getParentGuid());
			jsonArr.add(jsonObject);
		}
		return jsonArr.toString();
	}
}
