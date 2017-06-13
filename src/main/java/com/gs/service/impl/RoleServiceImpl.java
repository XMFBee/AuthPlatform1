package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.Permission;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.dao.RoleDAO;
import com.gs.service.RoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gs.common.bean.Pager;
/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:10:47
*@des 角色Service实现
*/
@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDAO roleDAO;

	public int insert(Role role) { return roleDAO.insert(role); }
	public int batchInsert(List<Role> list) { return roleDAO.batchInsert(list); }
	public int delete(Role role) { return roleDAO.delete(role); }
	public int deleteById(String id) {
        return roleDAO.deleteById(id);
    }
	public int batchDelete(List<Role> list) { return roleDAO.batchDelete(list); }
	public int update(Role role) { return roleDAO.update(role); }
	public int batchUpdate(List<Role> list) { return roleDAO.batchUpdate(list); }
	public List<Role> queryAll(User user) { return roleDAO.queryAll(user); }


	@Override
	public List<Role> queryByPager(String roleStatus, Pager pager) {
		return roleDAO.queryByPager(roleStatus, pager);
	}

	@Override
	public List<Role> queryAll(String status) {
		Map paramMap = new HashMap();
		paramMap.put("roleStatus",status);
		return roleDAO.queryAll(paramMap);
	}

	@Override
	public List<Role> querySysManager(String rolestatus) {
		Map paramMap = new HashMap();
		paramMap.put("roleStatus",rolestatus);
		paramMap.put("isMana",true);
		return roleDAO.queryAll(paramMap);
	}

	@Override
	public List<Role> queryCompManager(String rolestatus) {
		Map paramMap = new HashMap();
		paramMap.put("roleStatus",rolestatus);
		paramMap.put("isCompMana",true);
		return roleDAO.queryAll(paramMap);
	}

	@Override
	public int count(String roleStatus) {
		return roleDAO.count(roleStatus);
	}

	@Override
	public int updateStatus(Role role) {
		return roleDAO.updateStatus(role);
	}

	@Override
	public Role queryByName(String roleName) {
		return roleDAO.queryByName(roleName);
	}


	public List<Role> queryByStatus(String status) { return roleDAO.queryAll(status); }
	public Role query(Role role) { return roleDAO.query(role); }
	public Role queryById(String id) { return roleDAO.queryById(id); }
	public List<Role> queryByPager(Pager pager) { return roleDAO.queryByPager(pager); }
	public int count(User user) { return roleDAO.count(user); }

	public int inactive(String id) { return roleDAO.inactive(id); }
	public int active(String id) { return roleDAO.active(id); }

	public List<Role> queryByPagerDisable(Pager pager) {
		return roleDAO.queryByPagerDisable(pager);
	}

	public int countByDisable(User user) {
		return roleDAO.countByDisable(user);
	}

	public List<Role> blurredQuery(Pager pager, Role role) {
		return null;
	}

	@Override
	public int countByBlurred(Role role, User user) {
		return 0;
	}

	public int countByBlurred(Role role) {
		return 0;
	}
}