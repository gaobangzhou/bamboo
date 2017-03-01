package com.cisdi.ecm.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;


import com.cisdi.ecm.web.model.gen.Module;
import com.cisdi.ecm.web.model.gen.Permission;
import com.cisdi.ecm.web.model.gen.Role;
import com.cisdi.ecm.web.service.permission.ModuleService;
import com.cisdi.ecm.web.service.permission.PermissionService;
import com.cisdi.ecm.web.service.permission.RoleService;

public class ModuleServiceTest extends TestSupport {

	@Resource
	private ModuleService moduleService;
	
	@Resource	
	private PermissionService permissionService;
	
	@Resource
	private RoleService roleService;

	@Test
	public void test_insertModule() {
		Module module = new Module();
		module.setId(2);
		module.setModuleName("library");
		module.setModuleTitle("文档库");
		module.setModuleType("module");
		module.setModuleIcon("");
		module.setModuleIndex(0);
		module.setModuleTarget("");

		moduleService.insertModule(module);
	}

	@Test
	public void test_getAllModules() {
		List<Role> roles = roleService.selectRolesByUserId(1L);
		List<Module> lb = moduleService.getModulesByRoles(roles);
		if (lb != null) {
			for(Module m : lb){
				System.out.println(m.getChildrenModules().size());
			}
		}
	}

}
