package com.gs.service.impl;

import com.gs.dao.RolePermissionDAO;
import com.gs.service.RolePermissionService;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *由CSWangBin技术支持
 *
 *@author 程燕
 *@since 2017-04-17 16:10:47
 *@des 角色权限Service实现
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	@Resource
	private RolePermissionDAO rolePermissionDAO;

	@Override
	public Collection<Permission> queryAllPermissionByRoleName(String roleName) {
		List<String> p = rolePermissionDAO.queryAllPermissionByRoleName(roleName);
		List<Permission> permissions = new ArrayList<Permission>();
		for (String s : p) {
			Permission per = new WildcardPermission(s);
			permissions.add(per);
		}
		return permissions;
	}

	@Override
	public List<com.gs.bean.Permission> queryPermissionById(Integer id) {
		return rolePermissionDAO.queryPermissionById(id);
	}

	@Override
	public int addPermission(String roleId, String permissionId) {
		return rolePermissionDAO.addPermission(roleId,permissionId);
	}

	@Override
	public int removePermission(String roleId, String permissionId) {
		return rolePermissionDAO.removePermission(roleId,permissionId);
	}

	@Override
	public List<com.gs.bean.Permission> queryPermissions(String roleId, String roleStatus) {
		return rolePermissionDAO.queryPermissions(roleId,roleStatus);
	}

	@Override
	public int insertList(String roleId, List<String> permissionIds) {
		return rolePermissionDAO.insertList(roleId, permissionIds);
	}

}
