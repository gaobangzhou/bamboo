package com.cisdi.ecm.web.service.permission;

import java.util.List;
import java.util.Map;

import com.cisdi.ecm.web.model.gen.Permission;
import com.github.pagehelper.PageInfo;

/**
 * 权限 业务接口
 * 
 * @author StarZou
 * @since 2014年6月10日 下午12:02:39
 **/
public interface PermissionService {

    /**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);
    
    int insertPermission(Permission permission);
    
    int deletePermissionByid(int id);
    
    List<Permission> selectAllPermissions();
}
