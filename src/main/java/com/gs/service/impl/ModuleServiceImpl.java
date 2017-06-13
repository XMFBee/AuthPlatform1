package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.Module;
import com.gs.bean.User;
import com.gs.dao.ModuleDAO;
import com.gs.service.ModuleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.gs.common.bean.Pager;
/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:09:52
*@des 模块表Service实现
*/
@Service
public class ModuleServiceImpl implements ModuleService {

	@Resource
	private ModuleDAO moduleDAO;

	public int insert(Module module) { return moduleDAO.insert(module); }
	public int batchInsert(List<Module> list) { return moduleDAO.batchInsert(list); }
	public int delete(Module module) { return moduleDAO.delete(module); }
	public int deleteById(String id) {
        return moduleDAO.deleteById(id);
    }
	public int batchDelete(List<Module> list) { return moduleDAO.batchDelete(list); }
	public int update(Module module) { return moduleDAO.update(module); }
	public int batchUpdate(List<Module> list) { return moduleDAO.batchUpdate(list); }
	public List<Module> queryAll(User user) { return moduleDAO.queryAll(user); }

	@Override
	public List<Module> queryAll(String status) {
		return moduleDAO.queryAll(status);
	}

	public List<Module> queryByStatus(String status) { return moduleDAO.queryAll(status); }
	public Module query(Module module) { return moduleDAO.query(module); }
	public Module queryById(String id) { return moduleDAO.queryById(id); }
	public List<Module> queryByPager(Pager pager) { return moduleDAO.queryByPager(pager); }
	public int count(User user) { return moduleDAO.count(user); }
	public int inactive(String id) { return moduleDAO.inactive(id); }
	public int active(String id) { return moduleDAO.active(id); }

	public List<Module> queryByPagerDisable(Pager pager) {
		return moduleDAO.queryByPagerDisable(pager);
	}

	public int countByDisable(User user) {
		return moduleDAO.countByDisable(user);
	}

	public List<Module> blurredQuery(Pager pager, Module module) {
		return null;
	}

	@Override
	public int countByBlurred(Module module, User user) {
		return 0;
	}

	public int countByBlurred(Module module) {
		return 0;
	}

	@Override
	public int updPermissions(String oldModuleId, String newModuleId){
		return moduleDAO.updPermissions(oldModuleId, newModuleId);
	}

	@Override
	public int updPermission(String permissionId, String moduleId) {
		return moduleDAO.updPermission(permissionId, moduleId);
	}

	@Override
	public int countByModuleName(String moduleName, String moduleId) {
		return moduleDAO.countByModuleName(moduleName, moduleId);
	}

}