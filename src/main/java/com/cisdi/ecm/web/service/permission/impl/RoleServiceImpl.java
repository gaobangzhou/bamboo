package com.cisdi.ecm.web.service.permission.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cisdi.ecm.web.dao.RoleMapper;
import com.cisdi.ecm.web.model.gen.Role;
import com.cisdi.ecm.web.service.permission.RoleService;


/**
 * 角色Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl  implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

	@Override
	public List<Role> selectRolesByUserName(String userName) {
		return roleMapper.selectRolesByUserName(userName);
	}

	@Override
	public int insertRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.insertRole(role);
	}


	@Override
	public int deleteUserRoleById(Long id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteUserRoleById(id);
	}

	

}
