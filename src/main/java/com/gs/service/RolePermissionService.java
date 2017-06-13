package com.gs.service;

import com.gs.bean.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *由CSWangBin技术支持
 *
 *@author 程燕
 *@since 2017-04-17 16:11:01
 *@des 角色权限Service
 */
public interface RolePermissionService{
    Collection<org.apache.shiro.authz.Permission> queryAllPermissionByRoleName(String roleName);

    public List<Permission> queryPermissionById(Integer id);
    public int addPermission(String  roleId, String  permissionId);
    public int removePermission(String roleId, String permissionId);

    public List<Permission> queryPermissions(String roleId, String roleStatus);
    public int insertList(String roleId, List<String> permissionIds);
}