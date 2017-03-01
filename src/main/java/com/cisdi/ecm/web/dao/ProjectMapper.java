package com.cisdi.ecm.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.data.Project;
import com.cisdi.ecm.web.model.data.ProjectTemplate;
import com.cisdi.ecm.web.model.gen.User;

public interface ProjectMapper {

	public Project getProjectByNum(String projectNum);

	public Project getProjectByFolderId(Long folderId);

	//public ProjectTemplate getProjectTemplate(@Param("projectType") String ProjectType, @Param("ouId")String ouId);

//	public void copyTemplateFolder(@Param("tFolderId")String tFolderId, @Param("nFolderId")String nFolderId,
//			@Param("nParentFolderId")String nParentFolderId, @Param("userACL")String userACL, @Param("aliasACL")String aliasACL)
//			throws Exception;
	
//	public List<User> getProjectMembers(String projectNumber);
	
	public List<Project> getAllProjects(@Param("projectType") String ProjectType, @Param("ouId")int ouId);
}
