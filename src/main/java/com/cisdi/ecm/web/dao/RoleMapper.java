package com.cisdi.ecm.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.gen.Role;


/**
 * 
 *<p>Title:RoleMapper</p>
 *<p>Description:</p>
 *<p>Company:cisdi-info</p>
 * @author gao
 * @data 2016-8-8 下午9:46:18
 */
public interface RoleMapper  {
   
	/**
	 * 增加角色
	 * @param role
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:46:44
	 */
	int insertRole(Role role);
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:47:47
	 */
	int updateRole(Role role);
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:50:02
	 */
	int deleteRoleById(int id);
	
	/**
	 * 添加用户角色
	 * @param param
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:50:16
	 */
	int insertUserRole(@Param("userId") long userId,@Param("roleId") long roleId);
	
	/**
	 * 通过userId删除用户所有的角色
	 * @param id
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:52:16
	 */
	int deleteUserRoleById(Long userId);
    
	/**
	 * 通过roleId,userId删除用户的指定角色
	 * @param param
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:53:37
	 */
	int deleteUserRole(@Param("userId") long userId,@Param("roleId") long roleId);

	/**
	 * 通过userId获取用户拥有的角色
	 * @param userId
	 * @return
	 * @author gao
	 * @data 2016-8-8 下午9:54:55
	 */
    List<Role> selectRolesByUserId(Long userId);
    
    /**
     * 通过userName获取用户拥有的角色
     * @param userName
     * @return
     * @author gao
     * @data 2016-8-8 下午9:55:21
     */
    List<Role> selectRolesByUserName(String userName);
}