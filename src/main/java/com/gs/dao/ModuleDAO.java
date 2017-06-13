package com.gs.dao;

import com.gs.bean.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:09:51
*@des 模块DAO
*/
@Repository
public interface ModuleDAO extends BaseDAO<String, Module>{
    public List<Module> queryAll(@Param("moduleStatus")String moduleStatus);

    public int updPermissions(@Param("oldModuleId")String oldModuleId, @Param("newModuleId")String newModuleId);

    public int updPermission(@Param("permissionId")String permissionId, @Param("moduleId")String moduleId);

    public int countByModuleName(@Param("moduleName")String moduleName, @Param("moduleId")String moduleId);
}