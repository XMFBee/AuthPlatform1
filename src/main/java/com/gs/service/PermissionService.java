package com.gs.service;

import com.gs.bean.Permission;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:10:26
*@des 权限Service
*/
public interface PermissionService extends BaseService<String, Permission>{

    public List<Permission> queryAll();
    public List<Permission> queryByPager(String status, Pager pager) ;
    public int count(String status);
    public int count();

    public int updateStatus(List permissionIds,String status );

    public int countByPerName(String permissionZhname, String permissionId);

}