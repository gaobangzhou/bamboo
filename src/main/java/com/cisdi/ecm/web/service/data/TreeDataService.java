package com.cisdi.ecm.web.service.data;

public interface TreeDataService {

	public String getOuJson(String parentId);
	
	public String getProjectTypeJson(String parentId);
	
	public String getProjectJson(int ou,String type);
	
	public String getFolderJson(String parentId);
}
