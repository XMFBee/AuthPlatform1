package com.gs.service;

import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:12:14
*@des 
*/
public interface UserRoleService extends BaseService<String, UserRole>{

    /**
     * 模糊查询
     */
    public List<UserRole> blurredQuery(@Param("pager")Pager pager, @Param("userRole")UserRole userRole);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("userRole")UserRole userRole, @Param("user")User user);

}