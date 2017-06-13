package com.gs.dao;

import com.gs.bean.Permission;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:10:25
*@des 权限DAO
*/
@Repository
public interface PermissionDAO extends BaseDAO<String, Permission>{
    public List<Permission> queryAll();

    public List<Permission> queryByPager(Map paramsMap);
    public int count(Map paramsMap);

    public int updateStatus(Map paramsMap);

    public int countByPerName(@Param("permissionZhname")String permissionZhname,@Param("permissionId")String permissionId);

     /**
     * 模糊查询
     */
    public List<Permission> blurredQuery(@Param("pager")Pager pager, @Param("permission")Permission checkin);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("permission")Permission permission, @Param("user") User user);
}