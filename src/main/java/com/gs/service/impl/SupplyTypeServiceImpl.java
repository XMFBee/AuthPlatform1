package com.gs.service.impl;

import com.gs.bean.SupplyType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.SupplyTypeDAO;
import com.gs.service.SupplyTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:40
*@des 
*/
@Service
public class SupplyTypeServiceImpl implements SupplyTypeService {

	@Resource
	private SupplyTypeDAO supplyTypeDAO;

	public int insert(SupplyType supplyType) { return supplyTypeDAO.insert(supplyType); }
	public int batchInsert(List<SupplyType> list) { return supplyTypeDAO.batchInsert(list); }
	public int delete(SupplyType supplyType) { return supplyTypeDAO.delete(supplyType); }

	@Override
	public int countByDisable(User user) {
		return supplyTypeDAO.countByDisable();
	}

	@Override
	public int countByDisable() {
		return supplyTypeDAO.countByDisable();
	}

	@Override
	public int queryNameByOne(String supplyTypeName, String supplyTypeId) {
		return supplyTypeDAO.queryNameByOne(supplyTypeName,supplyTypeId);
	}


	public int deleteById(String id) {
        return supplyTypeDAO.deleteById(id);
    }
	public int batchDelete(List<SupplyType> list) { return supplyTypeDAO.batchDelete(list); }
	public int update(SupplyType supplyType) { return supplyTypeDAO.update(supplyType); }
	public int batchUpdate(List<SupplyType> list) { return supplyTypeDAO.batchUpdate(list); }

	@Override
	public List<SupplyType> queryAll(User user) {
		return supplyTypeDAO.queryAll(user);
	}

	@Override
	public List<SupplyType> queryAll(String status) {
		return supplyTypeDAO.queryAll(status);
	}


	public List<SupplyType> queryByStatus(String status) { return supplyTypeDAO.queryAll(status); }
	public SupplyType query(SupplyType supplyType) { return supplyTypeDAO.query(supplyType); }
	public SupplyType queryById(String id) { return supplyTypeDAO.queryById(id); }
	public List<SupplyType> queryByPager(Pager pager) { return supplyTypeDAO.queryByPager(pager); }

	@Override
	public int count(User user) {
		return supplyTypeDAO.count(user);
	}

	public int inactive(String id) { return supplyTypeDAO.inactive(id); }
	public int active(String id) { return supplyTypeDAO.active(id); }

	public List<SupplyType> queryByPagerDisable(Pager pager) {
		return supplyTypeDAO.queryByPagerDisable(pager);
	}

	public List<SupplyType> blurredQuery(Pager pager, SupplyType supplyType) {
		return null;
	}

	@Override
	public int countByBlurred(SupplyType supplyType, User user) {
		return 0;
	}

	public int countByBlurred(SupplyType supplyType) {
		return 0;
	}
}