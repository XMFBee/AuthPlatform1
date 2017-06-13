package com.gs.service.impl;

import com.gs.bean.Supply;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.SupplyDAO;
import com.gs.service.SupplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:28
*@des 
*/
@Service
public class SupplyServiceImpl implements SupplyService {

	@Resource
	private SupplyDAO supplyDAO;

	public int insert(Supply supply) { return supplyDAO.insert(supply); }
	public int batchInsert(List<Supply> list) { return supplyDAO.batchInsert(list); }
	public int delete(Supply supply) { return supplyDAO.delete(supply); }
	public int deleteById(String id) {
        return supplyDAO.deleteById(id);
    }
	public int batchDelete(List<Supply> list) { return supplyDAO.batchDelete(list); }
	public int update(Supply supply) { return supplyDAO.update(supply); }
	public int batchUpdate(List<Supply> list) { return supplyDAO.batchUpdate(list); }

	@Override
	public List<Supply> queryAll(User user) {
		return supplyDAO.queryAll(user);
	}

	@Override
	public List<Supply> queryAll(String status) {
		return supplyDAO.queryAll(status);
	}


	public List<Supply> queryByStatus(String status) { return supplyDAO.queryAll(status); }
	public Supply query(Supply supply) { return supplyDAO.query(supply); }
	public Supply queryById(String id) { return supplyDAO.queryById(id); }
	public List<Supply> queryByPager(Pager pager) { return supplyDAO.queryByPager(pager); }

	@Override
	public int count(User user) {
		return supplyDAO.count(user);
	}

	public int inactive(String id) { return supplyDAO.inactive(id); }
	public int active(String id) { return supplyDAO.active(id); }


	public List<Supply> queryByPagerDisable(Pager pager) {
		return supplyDAO.queryByPagerDisable(pager);
	}

	@Override
	public int countByDisable(User user) {
		return supplyDAO.countByDisable();
	}

	public List<Supply> blurredQuery(Pager pager, Supply supply) {
		return supplyDAO.blurredQuery(pager,supply);
	}

	@Override
	public int countByBlurred(Supply supply, User user) {
		return supplyDAO.countByBlurred(supply,user);
	}

	@Override
	public int queryNameByOne(String supplyName, String supplyId) {
		return supplyDAO.queryNameByOne(supplyName,supplyId);
	}
}