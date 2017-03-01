package com.cisdi.ecm.web.bean.knowledge;

import java.util.List;
import java.util.Map;

import com.cisdi.ecm.web.common.utils.FileSizeUtils;
import com.cisdi.ecm.web.model.data.DocInfo;
import com.cisdi.ecm.web.model.data.ShareSetting;
import com.cisdi.ecm.web.model.gen.User;
import com.cisdi.ecm.web.service.data.DocInfoService;
import com.cisdi.ecm.web.service.permission.UserService;
import com.cisdi.ecm.web.service.utils.SpringFactoryService;

public class ShareInfoPageBean {

	private Boolean canFollow;
	private Boolean canFavour;

	private String fileSize;

	private List<Map<String, Object>> top5FilesList;
	private DocInfo docInfo;
	private User docAuthor;
	private ShareSetting setting;

	private DocInfoService docInfoService = SpringFactoryService
			.getBean("docInfoServiceImpl");
	private UserService userService = SpringFactoryService
			.getBean("userServiceImpl");

	public ShareInfoPageBean(long docId, String userName) {

		docInfo = docInfoService.getDocInfoByDocId(docId);
		String authorName = docInfo.getDocAuthor();
		canFollow = docInfoService.canFollow(userName, authorName);
		if (userName.equals("")) {
			canFavour = false;
		} else {
			canFavour = docInfoService.canFavourite(docId, userName);
		}

		fileSize = FileSizeUtils.getInstance().getFileSizeStr(
				docInfo.getFileSize());
		docAuthor = userService.selectByUsername(authorName);
	}

	public DocInfo getDocInfo() {
		return docInfo;
	}

	public void setDocInfo(DocInfo docInfo) {
		this.docInfo = docInfo;
	}

	public ShareSetting getSetting() {
		return setting;
	}

	public void setSetting(ShareSetting setting) {
		this.setting = setting;
	}

	public List<Map<String, Object>> getTop5FilesList() {
		return top5FilesList;
	}

	public void setTop5FilesList(List<Map<String, Object>> top5FilesList) {
		this.top5FilesList = top5FilesList;
	}

	public Boolean getCanFollow() {
		return canFollow;
	}

	public void setCanFollow(Boolean canFollow) {
		this.canFollow = canFollow;
	}

	public Boolean getCanFavour() {
		return canFavour;
	}

	public void setCanFavour(Boolean canFavour) {
		this.canFavour = canFavour;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public User getDocAuthor() {
		return docAuthor;
	}

	public void setDocAuthor(User docAuthor) {
		this.docAuthor = docAuthor;
	}

}
