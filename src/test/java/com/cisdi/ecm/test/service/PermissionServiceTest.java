package com.cisdi.ecm.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;


import com.cisdi.ecm.web.dao.DocInfoMapper;
import com.cisdi.ecm.web.dao.FileMapper;
import com.cisdi.ecm.web.model.data.FileInfo;
import com.cisdi.ecm.web.model.gen.Permission;
import com.cisdi.ecm.web.service.permission.PermissionService;


public class PermissionServiceTest extends TestSupport {
	@Resource
	private PermissionService permissionService;

	@Resource
	private DocInfoMapper documentMapper;

	@Resource
	FileMapper fileMapper;

	@Test
	public void getAllPermissionTest() {
		// PageInfo<Permission> pages =
		// permissionService.selectPagePermissions(1, 15);
		// for(Permission permission : pages.getList()){
		// System.err.println(permission.getPermissionName());
		// }

		List<Permission> permissions = permissionService.selectAllPermissions();
		if (permissions.size() > 0) {
			System.err.println(permissions.get(0).getDescription());
		}
	}
	
	@Test
	public void getAllPermission(){
			List<Permission> permission=permissionService.selectAllPermissions();
			for(int i=0;i<permission.size();i++)
			{
				System.out.println(permission.get(i).getPermissionName());
			}
	}
	
	@Test
	public void Test_InsertDefaultDoc(){
		int result = documentMapper.insertDefDocInfo(0, 123, "011914");
		if (result != 0) {
			System.err.println(result);
		}
	}

	@Test
	public void Test_insertFileInfo() {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName("123123435");
		fileInfo.setFilePath("123123324");
		fileInfo.setFilePrefix("123123");
		fileInfo.setFileSize(13213245);
		fileInfo.setFileSuffix("txt");
		fileInfo.setOriginalName("6666");

		int reuslt = fileMapper.insertFile(fileInfo);
		if (reuslt != 0) {
			System.err.println(fileInfo.getId());
		}

	}

	@Test
	public void Test_selectFolderFiles() {

//		List<Map<String, Object>> lsMaps = fileMapper.selectFolderFiles(
//				"011099", "fb3fdfc5-4722-11e6-89b7-00ff525eaa84", "none","none");
//		if (lsMaps != null && lsMaps.size() > 0) {
//			System.err.println(lsMaps.get(0).get("NAME"));
//		}
	}



	@Test
	public void insertRolePermission()
	{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("role_id", 2);
		map.put("permission_id", 1);
	//	permissionService.insertRolePermission(map);
	
	}
	
	
	
	

}
