package com.cisdi.ecm.web.service.data.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ecm.web.common.constants.ServerConstants;
import com.cisdi.ecm.web.common.constants.VerifyCodeUtils;
import com.cisdi.ecm.web.dao.ShareInfoMapper;
import com.cisdi.ecm.web.model.data.ShareSetting;
import com.cisdi.ecm.web.service.data.ShareInfoService;

@Service
public class ShareInfoServiceImpl implements ShareInfoService {

	@Resource
	private ShareInfoMapper shareInfoMapper;

	@Override
	public Boolean canShareDirect(String userName) throws Exception {
		Map<String, Object> map = shareInfoMapper
				.selectUserShareSetting(userName);
		if (map != null && !map.isEmpty()) {
			String directCode = (String) map.get("share_direct");
			if (directCode.equals("Y")) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new Exception("查询用户默认分享设置失败");
		}
	}

	@Override
	@Transactional
	public int insertFileShareInfo(long docId, String userName)
			throws Exception {
		long timeCode = (new Date()).getTime();
		String shareCode = VerifyCodeUtils.generateVerifyCode(6);
		return shareInfoMapper.insertShareInfo(docId, userName, timeCode,
				shareCode);
	}

	@Override
	public ShareSetting getFileShareSettingById(long id) throws Exception {

		return shareInfoMapper.selectShareSettingById(id);
	}

	@Override
	@Transactional
	public Boolean updateFileShareSetting(Map<String, Object> setting)
			throws Exception {
		if (shareInfoMapper.updateShareSetting(setting) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getFileShareMapById(long docId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = shareInfoMapper.selectShareMapById(docId);
		String idCode = map.get("id").toString();
		String shareCode = map.get("share_code").toString();
		String linkAddress = ServerConstants.SHARE_LINK_PATH + idCode + "/"
				+ shareCode;
		map.put("linkAddress", linkAddress);
		return map;
	}

	@Override
	@Transactional
	public int shareFileByDocId(long docId, String userName) {
		try {
			if (this.insertFileShareInfo(docId, userName) == 1) {
				if (shareInfoMapper.updateShare("Y", docId) == 1) {
					return 1;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
