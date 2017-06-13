package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;
import com.gs.dao.WorkInfoDAO;
import com.gs.service.WorkInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:12:26
*@des 工单Service实现
*/
@Service
public class WorkInfoServiceImpl implements WorkInfoService {

	@Resource
	private WorkInfoDAO workInfoDAO;

	public int insert(WorkInfo workInfo) { return workInfoDAO.insert(workInfo); }
	public int batchInsert(List<WorkInfo> list) { return workInfoDAO.batchInsert(list); }
	public int delete(WorkInfo workInfo) { return workInfoDAO.delete(workInfo); }
	public int deleteById(String id) {
        return workInfoDAO.deleteById(id);
    }
	public int batchDelete(List<WorkInfo> list) { return workInfoDAO.batchDelete(list); }
	public int update(WorkInfo workInfo) { return workInfoDAO.update(workInfo); }
	public int batchUpdate(List<WorkInfo> list) { return workInfoDAO.batchUpdate(list); }


	public List<WorkInfo> queryByStatus(String status) { return workInfoDAO.queryAll(status); }
	public WorkInfo query(WorkInfo workInfo) { return workInfoDAO.query(workInfo); }
	public WorkInfo queryById(String id) { return workInfoDAO.queryById(id); }

	@Override
	public List<WorkInfo> queryByPager(Pager pager) {
		return queryByPager(pager, "");
	}

	@Override
	public int count(User user) {
		return count(user, "");
	}

	public List<WorkInfo> queryByPager(Pager pager,  String status) {
		Map paramMap = new HashMap();
		paramMap.put("pager", pager);
		paramMap.put("status", status);
		return workInfoDAO.queryByPager(paramMap);
	}
	public int inactive(String id) { return workInfoDAO.inactive(id); }
	public int active(String id) { return workInfoDAO.active(id); }

	@Override
	public List<WorkInfo> queryByPagerDisable(Pager pager) {
		return workInfoDAO.queryByPagerDisable(pager);
	}


	@Override
	public List<WorkInfo> blurredQuery(Pager pager, WorkInfo workInfo) {
		return workInfoDAO.blurredQuery(pager,workInfo);
	}

	@Override
	public List<WorkInfo> queryByCondition(String start, String end, String companyId,String maintainOrFix, String type) {
		return workInfoDAO.queryByCondition(start, end, companyId,maintainOrFix, type);
	}

	@Override
	public List<WorkInfo> queryByPagerschelude(Pager pager) {
		return workInfoDAO.queryByPagerschelude(pager);
	}

	@Override
	public List<WorkInfo> queryAll(User user) {
		return null;
	}

	@Override
	public List<WorkInfo> queryAll(String status) {
		return null;
	}

	@Override
	public int count(User user, String status) {
		Map param = new HashMap();
		param.put("user", user);
		param.put("status", status);
		return workInfoDAO.count(param);
	}

	@Override
	public int countByDisable(User user) {
		return 0;
	}

	@Override
	public int countByBlurred(WorkInfo workInfo, User user) {
		return workInfoDAO.countByBlurred(workInfo,user);
	}

	@Override
	public int countByFront(User frontUser) {
		return workInfoDAO.countByFront(frontUser);
	}

	@Override
	public List<WorkInfo> queryByFront(Pager pager) {
		return workInfoDAO.queryByFront(pager);
	}
}