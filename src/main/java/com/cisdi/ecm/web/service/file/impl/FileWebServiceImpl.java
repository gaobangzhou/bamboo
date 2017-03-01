package com.cisdi.ecm.web.service.file.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ecm.web.dao.DocInfoMapper;
import com.cisdi.ecm.web.dao.FileMapper;
import com.cisdi.ecm.web.dao.UserMapper;
import com.cisdi.ecm.web.model.data.FileInfo;
import com.cisdi.ecm.web.service.data.ShareInfoService;
import com.cisdi.ecm.web.service.file.FileWebService;

@Service
public class FileWebServiceImpl implements FileWebService {

	@Resource
	private FileMapper fileMapper;

	@Resource
	private DocInfoMapper documentMapper;

	@Resource
	private UserMapper userMapper;

	@Resource
	private ShareInfoService shareInfoService;

	@Override
	public FileInfo getFileByFileId(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public long initUploadFile(FileInfo fileInfo, String userName,
			String folderGuid) throws Exception {
		long resultDocId = 0;
		int result = fileMapper.insertFile(fileInfo);
		if (result != 0) {
			int docResult = documentMapper.insertDefDocInfo(0,
					fileInfo.getId(), userName);
			if (docResult == 0) {
				throw new Exception("插入文档信息失败");
			}
			Map<String, Object> docInfo = documentMapper
					.getDocInfoByFileId(fileInfo.getId());
			long docId = Long.parseLong(docInfo.get("id").toString());
			int re = documentMapper.insertFolderDocRt(docId, folderGuid);
			int re1 = userMapper.addUserTotalFiles(userName, 1,
					fileInfo.getFileSize());
			if (re == 0 || re1 == 0) {
				throw new Exception("插入文件夹失败");
			}
			if (shareInfoService.canShareDirect(userName)) {

				if (docInfo != null) {

					if (shareInfoService.insertFileShareInfo(docId, userName) != 1) {
						throw new Exception("插入分享数据失败");
					}
				}
			}

			resultDocId = docId;
		}

		return resultDocId;
	}

	@Override
	public String getUserFilePath(String userId) {

		return userId;
	}

	@Override
	public InputStream getFileInputStreamByDocId(long docId) throws Exception {
		FileInfo fileInfo = fileMapper.getFileInfoByDocId(docId);
		if (fileInfo != null) {
			InputStream inputStream = new FileInputStream(new File(
					fileInfo.getFilePath() + fileInfo.getFileName() + "."
							+ fileInfo.getFileSuffix()));
			return inputStream;
		} else {
			throw new Exception("文档信息不存在");
		}

	}
}
