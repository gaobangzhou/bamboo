package com.cisdi.ecm.web.model.gen;

import java.util.List;

public class Module {
	/**
	 * 导航
	 * 
	 * @author 高帮周
	 * @since 2016年5月16日 下午17:07:20
	 **/
	private int id;
	private int roleId;
	private String moduleName;//英文标签
	private String moduleTitle;//中文标签
	private String moduleType;//类型
	private String parentId;//父节点id
	private String moduleIcon;//图标
	private int moduleIndex;//排序
	private String moduleTarget;//导航对应的路径
	private List<Module> childrenModules;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleTitle() {
		return moduleTitle;
	}
	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getModuleIcon() {
		return moduleIcon;
	}
	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	public int getModuleIndex() {
		return moduleIndex;
	}
	public void setModuleIndex(int i) {
		this.moduleIndex = i;
	}
	public String getModuleTarget() {
		return moduleTarget;
	}
	public void setModuleTarget(String moduleTarget) {
		this.moduleTarget = moduleTarget;
	}
	public List<Module> getChildrenModules() {
		return childrenModules;
	}
	public void setChildrenModules(List<Module> childrenModules) {
		this.childrenModules = childrenModules;
	}

}
