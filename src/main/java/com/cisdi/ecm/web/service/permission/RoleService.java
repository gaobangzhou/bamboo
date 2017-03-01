package com.cisdi.ecm.web.service.permission;

import java.util.List;
import java.util.Map;

import com.cisdi.ecm.web.model.gen.Role;


/**
 * 角色 业务接口
 * 
 * @author StarZou
 * @since 2014年6月10日 下午4:15:01
 **/
public interface RoleService {
	
	/**
	 * 添加新角色
	 * @param role
	 * @return
	 * @author gao
	 * @data 2016-7-6 下午5:45:19
	 */
	int insertRole(Role role);
	

	
	/**
	 * 通过id 删除用户角色授权
	 * @param id
	 * @return
	 * @author gao
	 * @data 2016-7-6 下午5:46:45
	 */
	int deleteUserRoleById(Long id);
	

    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
    
    /**
     * 通过userName获取用户拥有的角色
     * @param userName
     * @return
     * @author gao
     * @data 2016-7-6 下午5:47:30
     */
    List<Role> selectRolesByUserName(String userName);
    
    
    
}
