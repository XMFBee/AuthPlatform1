package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.dao.UserRoleDAO;
import com.gs.service.UserRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.gs.common.bean.Pager;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2userRoleDAO17-userRoleDAO4-17 16:12:15
*@des 
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	public UserRoleDAO userRoleDAO;

	@Override
	public int insert(UserRole userRole) {
		return userRoleDAO.insert(userRole);
	}

	@Override
	public int batchInsert(List<UserRole> list) {
		return userRoleDAO.batchInsert(list);
	}

	@Override
	public int delete(UserRole userRole) {
		return userRoleDAO.delete(userRole);
	}

	@Override
	public int deleteById(String id) {
		return userRoleDAO.deleteById(id);
	}

	@Override
	public int batchDelete(List<UserRole> list) {
		return userRoleDAO.batchDelete(list);
	}

	@Override
	public int update(UserRole userRole) {
		return userRoleDAO.update(userRole);
	}

	@Override
	public int batchUpdate(List<UserRole> list) {
		return userRoleDAO.batchUpdate(list);
	}

	@Override
	public List<UserRole> queryAll(User user) {
		return userRoleDAO.queryAll(user);
	}

	@Override
	public List<UserRole> queryAll(String status) {
		return userRoleDAO.queryAll(status);
	}

	@Override
	public UserRole query(UserRole userRole) {
		return userRoleDAO.query(userRole);
	}

	@Override
	public UserRole queryById(String id) {
		return userRoleDAO.queryById(id);
	}

	@Override
	public List<UserRole> queryByPager(Pager pager) {
		return userRoleDAO.queryByPager(pager);
	}

	@Override
	public int count(User user) {
		return userRoleDAO.count(user);
	}

	@Override
	public int inactive(String id) {
		return userRoleDAO.inactive(id);
	}

	@Override
	public int active(String id) {
		return userRoleDAO.active(id);
	}

	@Override
	public List<UserRole> queryByPagerDisable(Pager pager) {
		return userRoleDAO.queryByPagerDisable(pager);
	}

	@Override
	public int countByDisable(User user) {
		return userRoleDAO.countByDisable(user);
	}

	@Override
	public List<UserRole> blurredQuery(Pager pager, UserRole userRole) {
		return userRoleDAO.blurredQuery(pager, userRole);
	}

	@Override
	public int countByBlurred(UserRole userRole, User user) {
		return userRoleDAO.countByBlurred(userRole, user);
	}
}