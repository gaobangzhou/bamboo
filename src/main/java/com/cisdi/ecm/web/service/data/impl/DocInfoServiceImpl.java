package com.cisdi.ecm.web.service.data.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ecm.web.common.constants.FileEvent;
import com.cisdi.ecm.web.dao.DocInfoMapper;
import com.cisdi.ecm.web.dao.FolderMapper;
import com.cisdi.ecm.web.dao.UserMapper;
import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.data.DocInfo;
import com.cisdi.ecm.web.service.data.DocInfoService;
import com.cisdi.ecm.web.service.data.FolderService;

@Service
public class DocInfoServiceImpl implements DocInfoService {
	@Resource
	private DocInfoMapper docInfoMapper;

	@Resource
	private FolderMapper folderMapper;
	@Resource
	private FolderService folderService;

	@Resource
	private UserMapper userMapper;

	@Override
	public DocInfo getDocInfoByDocId(long docId) {

		return docInfoMapper.getDocInfoByDocId(docId);
	}

	@Override
	@Transactional
	public int addDownloadNum(long docId, String userName) throws Exception {

		if (docInfoMapper.addDocDownloadNum(docId, 1) != 1) {
			throw new Exception("增加文档下载总数失败");
		}
		if (userMapper.addUserDownloadNum(userName, 1) != 1) {
			throw new Exception("增加用户下载总数失败");
		}
		if (docInfoMapper.insertUserEvent(docId, userName, "download") != 1) {
			throw new Exception("插入事件数据失败");
		}

		return 1;
	}

	@Override
	@Transactional
	public int addViewNum(long docId, String userName) throws Exception {

		if (docInfoMapper.addDocViewNum(docId, 1) != 1) {
			throw new Exception("增加文档下载总数失败");
		}
		if (userName == null || userName == "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String str = sdf.format(date);
			userName = str + "tempUser";
		}
		if (docInfoMapper.insertUserEvent(docId, userName, "view") != 1) {
			throw new Exception("插入事件数据失败");
		}

		return 1;
	}

	@Override
	public int addFavouriteNum(long docId, String userName) throws Exception {

		if (docInfoMapper.addDocViewNum(docId, 1) != 1) {
			throw new Exception("增加文档下载总数失败");
		}
		if (userMapper.addUserFavouritNum(userName, 1) != 1) {
			throw new Exception("增加用户下载总数失败");
		}
		if (docInfoMapper.insertUserEvent(docId, userName, "favour") != 1) {
			throw new Exception("插入事件数据失败");
		}
		return 1;
	}

	@Override
	public Boolean canFavourite(long docId, String userName) {
		if (userName == null || userName == "") {
			return false;
		}
		Map<String, Object> map = docInfoMapper.selectEventDoc(docId, userName,
				FileEvent.FAVOUR.getCode());
		if (map != null && !map.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean canFollow(String userName, String followName) {
		if (userName == null || userName == "") {
			return false;
		}
		Map<String, Object> map = userMapper.selectUserFollower(userName,
				followName);
		if (map != null && !map.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public int deleteFiles(List<FileChecked> listFiles) throws Exception {
		List<FileChecked> allFiles = new ArrayList<FileChecked>();
		List<Map<String, Object>> allFolders = new ArrayList<Map<String, Object>>();
		for (FileChecked fileChecked : listFiles) {
			if (fileChecked.getType().equals("folder")) {
				List<Map<String, Object>> subFoldersList = folderService
						.getAllSubFolders(fileChecked.getId());
				List<FileChecked> subCheckeds = docInfoMapper
						.getAllSubFileCheckeds(subFoldersList);
				allFolders.addAll(subFoldersList);
				allFiles.addAll(subCheckeds);
			} else {
				allFiles.add(fileChecked);
			}

		}
		if (allFiles.size() > 0) {
			int resDoc = docInfoMapper.deleteFiles2Trashcan(allFiles);
			if (resDoc == 0) {
				throw new Exception("批量删除文件失败");
			}

		}
		if (allFolders.size() > 0) {
			int resFolder = folderMapper.deleteFolders2Transhcan(allFolders);
			if (resFolder == 0) {
				throw new Exception("批量删除文件夹失败");
			}
		}

		return 1;
	}
}
