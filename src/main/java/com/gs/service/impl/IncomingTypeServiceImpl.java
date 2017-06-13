package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.bean.User;
import com.gs.dao.IncomingTypeDAO;
import com.gs.service.IncomingTypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.gs.common.bean.Pager;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:00:05
*@des 收支类型Service实现
*/
@Service
public class IncomingTypeServiceImpl implements IncomingTypeService {

	@Resource
	private IncomingTypeDAO incomingTypeDAO;

	public int insert(IncomingType incomingType) { return incomingTypeDAO.insert(incomingType); }
	public int batchInsert(List<IncomingType> list) { return incomingTypeDAO.batchInsert(list); }
	public int delete(IncomingType incomingType) { return incomingTypeDAO.delete(incomingType); }
	public int deleteById(String id) {
        return incomingTypeDAO.deleteById(id);
    }
	public int batchDelete(List<IncomingType> list) { return incomingTypeDAO.batchDelete(list); }
	public int update(IncomingType incomingType) { return incomingTypeDAO.update(incomingType); }
	public int batchUpdate(List<IncomingType> list) { return incomingTypeDAO.batchUpdate(list); }
	public List<IncomingType> queryAll(User user) { return incomingTypeDAO.queryAll(user); }

	@Override
	public List<IncomingType> queryAll(String status) {
		return incomingTypeDAO.queryAll(status);
	}

	public List<IncomingType> queryByStatus(String status) { return incomingTypeDAO.queryAll(status); }
	public IncomingType query(IncomingType incomingType) { return incomingTypeDAO.query(incomingType); }
	public IncomingType queryById(String id) { return incomingTypeDAO.queryById(id); }
	public List<IncomingType> queryByPager(Pager pager) { return incomingTypeDAO.queryByPager(pager); }
	public int count(User user) { return incomingTypeDAO.count(user); }
	public int inactive(String id) { return incomingTypeDAO.inactive(id); }
	public int active(String id) { return incomingTypeDAO.active(id); }

	@Override
	public List<IncomingType> queryByPagerDisable(Pager pager) {
		return incomingTypeDAO.queryByPagerDisable(pager);
	}

	@Override
	public int countByDisable(User user) {
		return incomingTypeDAO.countByDisable(user);
	}

	@Override
	public int countByDisable() {
		return incomingTypeDAO.countByDisable();
	}

	@Override
	public IncomingType queryById(String inTypeName, String inTypeId) {
		return incomingTypeDAO.queryById(inTypeName, inTypeId);
	}

	public List<IncomingType> blurredQuery(Pager pager, IncomingType incomingType) {
		return null;
	}

	@Override
	public int countByBlurred(IncomingType incomingType, User user) {
		return 0;
	}

	public int countByBlurred(IncomingType incomingType) {
		return 0;
	}
}