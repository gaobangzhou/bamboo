package com.cisdi.ecm.web.service.data;

import java.util.List;

import com.cisdi.ecm.web.model.bind.FileChecked;
import com.cisdi.ecm.web.model.data.DocInfo;

public interface DocInfoService {

	public DocInfo getDocInfoByDocId(long docId);

	public int addDownloadNum(long docId, String userName) throws Exception;

	public int addViewNum(long docId, String userName) throws Exception;

	public int addFavouriteNum(long docId, String userName) throws Exception;

	public Boolean canFavourite(long docId, String userName);

	public Boolean canFollow(String userName, String followName);

	public int deleteFiles(List<FileChecked> listFiles) throws Exception;

}
