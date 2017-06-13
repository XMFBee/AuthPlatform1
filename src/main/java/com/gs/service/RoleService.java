package com.gs.service;

import com.gs.bean.Permission;
import com.gs.bean.Role;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:10:47
*@des 角色Service
*/
public interface RoleService extends BaseService<String, Role>{
    public List<Role> queryAll(String rolestatus);
    public List<Role> queryByPager(String roleStatus, Pager pager);

    public List<Role> querySysManager(String rolestatus);
    public List<Role> queryCompManager(String rolestatus);

    public int count(String roleStatus);

    public int updateStatus (Role role);

    public Role queryByName(String roleName);


}