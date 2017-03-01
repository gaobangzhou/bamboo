package com.cisdi.ecm.web.bean.knowledge;

import java.util.List;
import java.util.Map;

import com.cisdi.ecm.web.common.utils.FileSizeUtils;
import com.cisdi.ecm.web.model.data.Folder;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.data.FileInfoService;
import com.cisdi.ecm.web.service.data.FolderService;
import com.cisdi.ecm.web.service.utils.SpringFactoryService;

public class AllFilePageBean {
	private Boolean canUploadFiles = true;
	private Folder rootFolder;
	private List<Map<String, Object>> listData;
	private String capacity;
	private long fileTotalCount;
	private String fileTotalSize;

	private FolderService folerService = SpringFactoryService
			.getBean("folderServiceImpl");
	private FileInfoService fileInfoService = SpringFactoryService
			.getBean("fileInfoServiceImpl");

	public AllFilePageBean(User user) {
		super();
		if (user.getTotalSize() >= user.getCapacity()) {
			canUploadFiles = false;
		}
		rootFolder = folerService.getUserRootFolder(user.getUserName());
		capacity = FileSizeUtils.getInstance().getFileSizeStr(
				user.getCapacity());
		fileTotalSize = FileSizeUtils.getInstance().getFileSizeStr(
				user.getTotalSize());
		fileTotalCount = user.getTotalCount();
		listData = fileInfoService.getFolderFilesByUserId(user.getUserName(),
				rootFolder.getFolderGuid(), "none", "none", "N");
	}

	public Boolean getCanUploadFiles() {
		return canUploadFiles;
	}

	public void setCanUploadFiles(Boolean canUploadFiles) {
		this.canUploadFiles = canUploadFiles;
	}

	public Folder getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(Folder rootFolder) {
		this.rootFolder = rootFolder;
	}

	public List<Map<String, Object>> getListData() {
		return listData;
	}

	public void setListData(List<Map<String, Object>> listData) {
		this.listData = listData;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public long getFileTotalCount() {
		return fileTotalCount;
	}

	public void setFileTotalCount(long fileTotalCount) {
		this.fileTotalCount = fileTotalCount;
	}

	public String getFileTotalSize() {
		return fileTotalSize;
	}

	public void setFileTotalSize(String fileTotalSize) {
		this.fileTotalSize = fileTotalSize;
	}

}
