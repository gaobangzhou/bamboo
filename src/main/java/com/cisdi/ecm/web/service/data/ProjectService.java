package com.cisdi.ecm.web.service.data;

import com.cisdi.ecm.web.model.data.Project;
import com.cisdi.ecm.web.model.data.ProjectTemplate;

public interface ProjectService {

	public Project getProjectByNum(String projectNum);
	
	public Project getProjectByFolderId(Long folderId);
	
	public ProjectTemplate getProjectTemplate(String ProjectType,String ouId);
}
