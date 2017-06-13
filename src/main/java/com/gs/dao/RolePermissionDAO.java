package com.gs.dao;

import com.gs.bean.Permission;
import com.gs.bean.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:11:01
*@des 角色权限DAO
*/
@Repository
public interface RolePermissionDAO extends BaseDAO<String, RolePermission>{
    /**
     * 根据角色名获取角色对应的权限信息
     * @param roleName
     * @return
     */
    public List<String> queryAllPermissionByRoleName(@Param("roleName")String roleName);

    public List<Permission> queryPermissionById(Integer id);
    public int addPermission(@Param("roleId")String roleId, @Param("permissionId")String permissionId);
    public int removePermission(@Param("roleId")String roleId, @Param("permissionId")String permissionId);

    /**
     *
     * 自己写的
     */
    public List<Permission> queryPermissions(@Param("roleId") String roleId, @Param("roleStatus") String roleStatus);

    public int insertList(@Param("roleId")String roleId, @Param("permissions")List<String> permissionIds);
}