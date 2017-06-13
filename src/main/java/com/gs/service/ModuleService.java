package com.gs.service;

import com.gs.bean.Module;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:09:51
*@des 模块Service
*/
public interface ModuleService extends BaseService<String, Module>{

    public int updPermissions(String oldModuleId, String newModuleId);

    public int updPermission(String permissionId, String moduleId);

    public int countByModuleName(String moduleName, String moduleId);
}