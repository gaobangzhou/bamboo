package com.cisdi.ecm.web.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.data.ShareSetting;

public interface ShareInfoMapper {

	public Map<String, Object> selectUserShareSetting(String userName);

	public int updateShare(@Param("status") String status,
			@Param("docId") long docId);

	public int insertShareInfo(@Param("docId") long docId,
			@Param("userName") String userName,
			@Param("timeCode") long timeCode,
			@Param("shareCode") String shareCode);

	public int insertDefSetting(@Param("userName") String userName);

	public ShareSetting selectShareSettingByCode(
			@Param("code") String shareCode, @Param("time") long time);

	public ShareSetting selectShareSettingById(long id);

	public Map<String, Object> selectShareMapById(long docId);

	public int updateShareSetting(Map<String, Object> params);
}
