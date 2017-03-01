package com.cisdi.ecm.web.service.permission;

import java.util.List;

import com.cisdi.ecm.web.model.gen.Module;
import com.cisdi.ecm.web.model.gen.Role;

public interface ModuleService {

	int insertModule(Module module);

	List<Module> getModulesByRoles(List<Role> roles);

	Module getModulesById(int id);

	List<Module> getChildrenModues(int id);
}
