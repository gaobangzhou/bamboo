package com.cisdi.ecm.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cisdi.ecm.web.model.gen.Permission;



/**
 * 权限 Dao 接口
 * 
 * @author StarZou
 * @since 2014年7月5日 上午11:59:03
 **/
public interface PermissionMapper {
	/**
	 * 增加权限
	 * @param permission
	 * @return
	 */
	int insertPermission(Permission permission);
	
	/**
	 * 通过id删除权限
	 * @param id
	 * @return
	 */
	int deletePermissionById(long id);
    /**
     * 搜索角色拥有的权限
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);
    /**
     * 角色添加权限
     * @param map
     * @return
     */
    int insertRolePermission(@Param("roleId") long roleId,@Param("permissionId") long permissionId);
    /**
     * 所有的权限
     * @return
     */
    List<Permission> selectAllPermissions();
    /**
     *删除某个角色的某条权限
     * @param map
     * @return
     */
    int deleteRolePermission(@Param("roleId") long roleId,@Param("permissionId") long permissionId);
    
    
}