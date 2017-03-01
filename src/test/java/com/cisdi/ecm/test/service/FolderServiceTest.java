package com.cisdi.ecm.test.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.cisdi.ecm.web.dao.DocInfoMapper;
import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.data.Folder;
import com.cisdi.ecm.web.service.data.FolderService;

public class FolderServiceTest extends TestSupport {
	@Resource
	private FolderService folderService;
	@Resource
	private DocInfoMapper docInfoMapper;

	@Test
	public void test_createFolder() throws Exception {
		Folder folder = new Folder();
		folder.setFolderName("文档库");
		folder.setParentGuid("FLD_ROOT");
		folder.setFolderCreator("systemAdmin");
		folder.setLastModifier("systemAdmin");
		folder.setFolderGroup("root");
		folder.setFolderUserACL("");
		folder.setFolderAliasACL("");
		folder.setFolderOU(1000);
		folder.setIsTemplate("Y");
		folder.setIsSecurity("N");

		// folderService.createFolder(folder);
		System.out.println(folder.getFolderGuid());
	}

	@Test
	public void test_updateFolder() throws Exception {
		Folder folder = new Folder();
		folder.setFolderGuid("657ea860-44cf-11e6-a184-00ff525eaa84");
		folder.setFolderUserACL("123123");
		folder.setFolderAliasACL("123123");
		folderService.updateFolder(folder);

	}

	@Test
	public void test_getFolderPathList() {
		List<Map<String, Object>> resList = folderService.getFolderPathList(
				"011099", "9d3cb45a-5d39-11e6-ab91-00ff7629d4b2");
		if (resList.size() > 0) {
			System.err.println(resList.get(0).get("foldername").toString());
		}
	}

	@Test
	public void test_getCreateNewFolder() throws Exception {
		Boolean resList = folderService.createFolder("测试文件夹",
				"dfe2eb88-5d37-11e6-ab91-00ff7629d4b2", "011099");
		if (resList) {
			System.err.println("新建文件夹成功！");
		}
	}

	@Test
	public void Test_getAllSubFiles() {
		List<Map<String, Object>> listMaps = folderService
				.getAllSubFolders("fb3fdfc5-4722-11e6-89b7-00ff525eaa84");
		List<FileChecked> list = docInfoMapper.getAllSubFileCheckeds(listMaps);
		if (list != null && list.size() > 0) {
			System.err.println("---" + list.get(0).getId());

		}
	}
}
