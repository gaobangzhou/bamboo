package com.cisdi.ecm.web.service.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ecm.web.common.utils.FileSizeUtils;
import com.cisdi.ecm.web.dao.DocInfoMapper;
import com.cisdi.ecm.web.dao.FileMapper;
import com.cisdi.ecm.web.dao.UserMapper;
import com.cisdi.ecm.web.model.data.FileInfo;
import com.cisdi.ecm.web.model.data.LinkInfo;
import com.cisdi.ecm.web.service.data.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Resource
	private FileMapper fileMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private DocInfoMapper docInfoMapper;

	@Override
	public List<Map<String, Object>> getFolderFilesByUserId(String userId,
			String folderGuid, String order, String orderby, String status) {

		List<Map<String, Object>> lsMaps = fileMapper.selectFolderFiles(userId,
				folderGuid, order, orderby, status);

		return formatData(lsMaps);
	}

	@Override
	public Map<String, Object> shareFileById(long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deleteFileById(long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkInfo getLinkInfoById(long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Map<String, Object>> formatData(List<Map<String, Object>> data) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get("type").equals("file")) {
				long fileSize = Long.parseLong(data.get(i).get("size")
						.toString());
				data.get(i).put("size",
						FileSizeUtils.getInstance().getFileSizeStr(fileSize));
			}
		}

		return data;
	}

	@Override
	@Transactional
	public Map<String, Object> delete2trashcan(long fileId, String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (fileMapper.updateInTrashcan(fileId, "Y") == 1) {
			FileInfo fileInfo = fileMapper.getFileInfoByDocId(fileId);
			int re = userMapper.deleteUserTotalFiles(userName, 1,
					fileInfo.getFileSize());
			if (re == 1) {
				result.put("success", true);

			} else {
				result.put("success", false);
				result.put("message", "更新文件存储信息失败");
			}
		} else {
			result.put("success", false);
			result.put("message", "删除文件失败");
		}
		return result;
	}

	@Override
	public FileInfo getFileInfoByDocId(long docId) {
		return fileMapper.getFileInfoByDocId(docId);
	}

	@Override
	public Object searchFolderFiles(String userName, String order,
			String orderby, String status, String keyword) {
		List<Map<String, Object>> lsMaps = fileMapper.searchFolderFiles(
				userName, order, orderby, status, "%" + keyword + "%");

		return formatData(lsMaps);
	}

}
