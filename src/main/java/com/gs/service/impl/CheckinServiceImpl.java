package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.CheckinDAO;
import com.gs.service.CheckinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:56:49
*@des 登记表Service实现
*/
@Service
public class CheckinServiceImpl implements CheckinService {

	@Resource
	private CheckinDAO checkinDAO;

	public int insert(Checkin checkin) { return checkinDAO.insert(checkin); }
	public int batchInsert(List<Checkin> list) { return checkinDAO.batchInsert(list); }
	public int delete(Checkin checkin) { return checkinDAO.delete(checkin); }
	public int deleteById(String id) {
        return checkinDAO.deleteById(id);
    }
	public int batchDelete(List<Checkin> list) { return checkinDAO.batchDelete(list); }
	public int update(Checkin checkin) { return checkinDAO.update(checkin); }
	public int batchUpdate(List<Checkin> list) { return checkinDAO.batchUpdate(list); }
	public List<Checkin> queryAll(User user) { return checkinDAO.queryAll(user); }

	public List<Checkin> queryAll(String status) {
		return checkinDAO.queryAll(status);
	}

	public Checkin query(Checkin checkin) { return checkinDAO.query(checkin); }
	public Checkin queryById(String id) { return checkinDAO.queryById(id); }
	public List<Checkin> queryByPager(Pager pager) { return checkinDAO.queryByPager(pager); }
	public int count(User user) { return checkinDAO.count(user); }
	public int inactive(String id) { return checkinDAO.inactive(id); }
	public int active(String id) { return checkinDAO.active(id); }

	public List<Checkin> queryByPagerDisable(Pager pager) {
		return checkinDAO.queryByPagerDisable(pager);
	}

	public int countByDisable(User user) {
		return checkinDAO.countByDisable(user);
	}

	public List<Checkin> blurredQuery(Pager pager, Checkin checkin) {
		return checkinDAO.blurredQuery(pager, checkin);
	}

	public int countByBlurred(Checkin checkin, User user) {
		return checkinDAO.countByBlurred(checkin, user);
	}

	@Override
	public void updateUserIds(String userId, String chIds) {
		checkinDAO.updateUserIds(userId, chIds);
	}

	@Override
	public List<Checkin> queryByPhone(String userPhone) {
		return checkinDAO.queryByPhone(userPhone);
	}
}