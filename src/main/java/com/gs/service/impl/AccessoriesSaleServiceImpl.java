package com.gs.service.impl;

import com.gs.bean.AccessoriesBuy;
import com.gs.bean.AccessoriesSale;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.AccessoriesSaleDAO;
import com.gs.service.AccessoriesSaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:47:33
*@des 配件销售Service实现
*/
@Service
public class AccessoriesSaleServiceImpl implements AccessoriesSaleService {

	@Resource
	private AccessoriesSaleDAO accessoriesSaleDAO;

	public int insert(AccessoriesSale accessoriesSale) { return accessoriesSaleDAO.insert(accessoriesSale); }
	public int batchInsert(List<AccessoriesSale> list) { return accessoriesSaleDAO.batchInsert(list); }
	public int delete(AccessoriesSale accessoriesSale) { return accessoriesSaleDAO.delete(accessoriesSale); }
	public int deleteById(String id) {
        return accessoriesSaleDAO.deleteById(id);
    }
	public int batchDelete(List<AccessoriesSale> list) { return accessoriesSaleDAO.batchDelete(list); }
	public int update(AccessoriesSale accessoriesSale) { return accessoriesSaleDAO.update(accessoriesSale); }
	public int batchUpdate(List<AccessoriesSale> list) { return accessoriesSaleDAO.batchUpdate(list); }

	@Override
	public List<AccessoriesSale> queryAll(User user) {
		return accessoriesSaleDAO.queryAll(user);
	}

	@Override
	public List<AccessoriesSale> queryAll(String status) {
		return accessoriesSaleDAO.queryAll(status);
	}

	public List<AccessoriesSale> queryByStatus(String status) { return accessoriesSaleDAO.queryAll(status); }
	public AccessoriesSale query(AccessoriesSale accessoriesSale) { return accessoriesSaleDAO.query(accessoriesSale); }
	public AccessoriesSale queryById(String id) { return accessoriesSaleDAO.queryById(id); }
	public List<AccessoriesSale> queryByPager(Pager pager) { return accessoriesSaleDAO.queryByPager(pager); }

	@Override
	public int count(User user) {
		return accessoriesSaleDAO.count(user);
	}

	public int inactive(String id) { return accessoriesSaleDAO.inactive(id); }
	public int active(String id) { return accessoriesSaleDAO.active(id); }

	public List<AccessoriesSale> queryByPagerDisable(Pager pager) {
		return accessoriesSaleDAO.queryByPagerDisable(pager);
	}

	@Override
	public int countByDisable(User user) {
		return accessoriesSaleDAO.countByDisable(user);
	}

	public int countByDisable() {
		return accessoriesSaleDAO.countByDisable();
	}

	public List<AccessoriesSale> blurredQuery(Pager pager, AccessoriesSale accessoriesSale) {
		return accessoriesSaleDAO.blurredQuery(pager,accessoriesSale);
	}

	@Override
	public int countByBlurred(AccessoriesSale accessoriesSale, User user) {
		return accessoriesSaleDAO.countByBlurred(accessoriesSale,user);
	}
}