package com.gs.dao;

import com.gs.bean.Permission;
import com.gs.bean.Role;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:10:46
*@des  角色DAO
*/
@Repository
public interface RoleDAO extends BaseDAO<String, Role>{
    public List<Role> queryAll(Map paramMap);

    public List<Role> queryByPager(@Param("roleStatus")String roleStatus, @Param("pager")Pager pager);

    public int count(@Param("roleStatus") String roleStatus);

    public int updateStatus (Role role);

    public Role queryByName(String roleName);

}