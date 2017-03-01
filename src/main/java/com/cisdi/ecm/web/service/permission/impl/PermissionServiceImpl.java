package com.cisdi.ecm.web.service.permission.impl;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cisdi.ecm.web.dao.PermissionMapper;
import com.cisdi.ecm.web.model.gen.Permission;
import com.cisdi.ecm.web.service.permission.PermissionService;


/**
 * 权限Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午12:05:03
 */
@Service
public class PermissionServiceImpl  implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

	@Override
	public List<Permission> selectPermissionsByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}
	
	@Override
	public List<Permission> selectAllPermissions() {
		// TODO Auto-generated method stub
		return permissionMapper.selectAllPermissions();
	}

	@Override
	public int insertPermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.insertPermission(permission);
	}

	@Override
	public int deletePermissionByid(int id) {
		// TODO Auto-generated method stub
		return permissionMapper.deletePermissionById(id);
	}

}
