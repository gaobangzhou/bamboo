package com.cisdi.ecm.web.service.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cisdi.ecm.web.dao.ModuleMapper;
import com.cisdi.ecm.web.model.gen.Module;
import com.cisdi.ecm.web.model.gen.Role;
import com.cisdi.ecm.web.service.permission.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Resource
	private ModuleMapper moduleMapper;

	@Override
	public int insertModule(Module module) {
		return moduleMapper.insertModule(module);
	}

	@Override
	public List<Module> getModulesByRoles(List<Role> roles) {
		return moduleMapper.getModulesByRoles(roles);
	}

	@Override
	public Module getModulesById(int id) {
		return moduleMapper.getModulesById(id);
	}

	@Override
	public List<Module> getChildrenModues(int id) {

		return moduleMapper.getChildrenModues(id);
	}

}
