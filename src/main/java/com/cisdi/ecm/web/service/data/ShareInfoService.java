package com.cisdi.ecm.web.service.data;

import java.util.Map;

import com.cisdi.ecm.web.model.data.ShareSetting;

public interface ShareInfoService {

	public Boolean canShareDirect(String userName) throws Exception;

	public int insertFileShareInfo(long docId, String userName)
			throws Exception;

	public int shareFileByDocId(long docId, String userName);

	public ShareSetting getFileShareSettingById(long Id) throws Exception;

	public Map<String, Object> getFileShareMapById(long docId);

	public Boolean updateFileShareSetting(Map<String, Object> setting)
			throws Exception;
}
