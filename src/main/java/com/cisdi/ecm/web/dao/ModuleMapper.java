package com.cisdi.ecm.web.dao;

import java.util.List;

import com.cisdi.ecm.web.model.gen.Module;
import com.cisdi.ecm.web.model.gen.Role;


/**
 * 权限 Dao 接口
 * 
 * @author gbz
 * @since 
 **/
public interface ModuleMapper  {
	
	public int insertModule(Module module);
	
	public List<Module> getModulesByRoles(List<Role> roles);
	
	public Module getModulesById(int id);
	
	public List<Module> getChildrenModues(int id);

}
