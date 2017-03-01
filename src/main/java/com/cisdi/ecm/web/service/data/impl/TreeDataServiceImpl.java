package com.cisdi.ecm.web.service.data.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.cisdi.ecm.web.dao.BaseDataMapper;
import com.cisdi.ecm.web.dao.FolderMapper;
import com.cisdi.ecm.web.dao.ProjectMapper;
import com.cisdi.ecm.web.model.base.Company;
import com.cisdi.ecm.web.model.data.Folder;
import com.cisdi.ecm.web.model.data.Project;
import com.cisdi.ecm.web.service.data.ProjectService;
import com.cisdi.ecm.web.service.data.TreeDataService;

@Service
public class TreeDataServiceImpl implements TreeDataService {

	@Resource
	private BaseDataMapper baseDataMapper;
	@Resource
	private FolderMapper folderMapper;
	
	@Resource
	private ProjectMapper projectMapper;

	
	@Override
	public String getOuJson(String parentId) {
	List<Company> companies =	baseDataMapper.getAllCompany();
	JSONArray jsonArr = new JSONArray();
	for (Company company : companies) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", company.getOuId());
		jsonObject.put("name", company.getOuName());
		jsonObject.put("open", false);
		jsonObject.put("isParent", true);
		jsonObject.put("type", "company");
		jsonObject.put("pid", parentId);
		jsonArr.add(jsonObject);
	}
	return jsonArr.toString();
	}

	@Override
	public String getProjectTypeJson(String parentId) {
		List<Map<String, Object>> lMaps =baseDataMapper.getProjectTypeByOu(Integer.parseInt(parentId));
		JSONArray jsonArr = new JSONArray();
		for (Map<String, Object> map : lMaps) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", map.get("CODE"));
			jsonObject.put("name", map.get("NAME"));
			jsonObject.put("open", false);
			jsonObject.put("isParent", true);
			jsonObject.put("type", "TYPE");
			jsonObject.put("pid", parentId);
			jsonArr.add(jsonObject);
		}
		return jsonArr.toString();
	}

	@Override
	public String getProjectJson(int ou, String type) {
		List<Project> projects =projectMapper.getAllProjects(type, ou);
		JSONArray jsonArr = new JSONArray();
		for (Project project : projects) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", project.getProjectFolderGuid());
			jsonObject.put("name", project.getProjectName());
			jsonObject.put("open", false);
			jsonObject.put("isParent", true);
			jsonObject.put("type", "TYPE");
			jsonObject.put("pid", ou);
			jsonArr.add(jsonObject);
		}
		return jsonArr.toString();
	}

	@Override
	public String getFolderJson(String parentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
