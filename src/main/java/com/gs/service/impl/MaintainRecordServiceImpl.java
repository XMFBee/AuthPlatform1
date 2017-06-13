package com.gs.service.impl;

import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaintainRecordDAO;
import com.gs.service.MaintainRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:04:55
*@des 维修保养记录Service实现
*/
@Service
public class MaintainRecordServiceImpl implements MaintainRecordService {

	@Resource
	private MaintainRecordDAO maintainRecordDAO;

	public int insert(MaintainRecord maintainRecord) { return maintainRecordDAO.insert(maintainRecord); }
	public int batchInsert(List<MaintainRecord> list) { return maintainRecordDAO.batchInsert(list); }
	public int delete(MaintainRecord maintainRecord) { return maintainRecordDAO.delete(maintainRecord); }
	public int deleteById(String id) {
        return maintainRecordDAO.deleteById(id);
    }
	public int batchDelete(List<MaintainRecord> list) { return maintainRecordDAO.batchDelete(list); }
	public int update(MaintainRecord maintainRecord) { return maintainRecordDAO.update(maintainRecord); }
	public int batchUpdate(List<MaintainRecord> list) { return maintainRecordDAO.batchUpdate(list); }
	public List<MaintainRecord> queryAll(User user) { return maintainRecordDAO.queryAll(user); }

	public List<MaintainRecord> queryAll(String status) {
		return maintainRecordDAO.queryAll(status);
	}

	public List<MaintainRecord> queryByStatus(String status) { return maintainRecordDAO.queryAll(status); }
	public MaintainRecord query(MaintainRecord maintainRecord) { return maintainRecordDAO.query(maintainRecord); }
	public MaintainRecord queryById(String id) { return maintainRecordDAO.queryById(id); }
	public List<MaintainRecord> queryByPager(Pager pager) { return maintainRecordDAO.queryByPager(pager); }
	public int count(User user) { return maintainRecordDAO.count(user); }
	public int inactive(String id) { return maintainRecordDAO.inactive(id); }
	public int active(String id) { return maintainRecordDAO.active(id); }

	public List<MaintainRecord> queryByPagerDisable(Pager pager) {
		return maintainRecordDAO.queryByPagerDisable(pager);
	}

	public int countByDisable(User user) {
		return maintainRecordDAO.countByDisable(user);
	}

	public List<MaintainRecord> blurredQuery(Pager pager, MaintainRecord maintainRecord) {
		return maintainRecordDAO.blurredQuery(pager, maintainRecord);
	}

	public int countByBlurred(MaintainRecord maintainRecord,User user) {
		return maintainRecordDAO.countByBlurred(maintainRecord, user);
	}

	public List<MaintainRecord> blurredQueryByRemind(Pager pager, MaintainRecord maintainRecord) {
		return maintainRecordDAO.blurredQueryByRemind(pager, maintainRecord);
	}

	public int countByBlurredByRemind(MaintainRecord maintainRecord, User user) {
		return maintainRecordDAO.countByBlurredByRemind(maintainRecord, user);
	}

	@Override
	public List<MaintainRecord> queryByCondition(String start, String end, String companyId,String maintainOrFix, String type) {
		return maintainRecordDAO.queryByCondition(start,end,companyId,maintainOrFix,type);
	}

	@Override
	public void updateCurrentStatus(String currentStatus, String recordId) {
		maintainRecordDAO.updateCurrentStatus(currentStatus, recordId);
	}

	/**
	 * 提车提醒维修保养记录查询
	 */
	@Override
	public List<MaintainRecord> queryByPagerRemindNo(Pager pager) {
		return maintainRecordDAO.queryByPagerRemindNo(pager);
	}

	@Override
	public int countByRemindNo(User user) {
		return maintainRecordDAO.countByRemindNo(user);
	}
	/**
	 * 提车提醒已提醒维修保养记录查询
	 */
	@Override
	public int countByRemindYes(User user) {
		return maintainRecordDAO.countByRemindYes(user);
	}

	@Override
	public List<MaintainRecord> queryByPagerRemindYes(Pager pager) {
		return maintainRecordDAO.queryByPagerRemindYes(pager);
	}

	@Override
	public List<MaintainRecord> queryByPagerSix(Pager pager, String actualEndTime) {
		return maintainRecordDAO.queryByPagerSix(pager,actualEndTime);
	}

	@Override
	public int countSix(User user, String actualEndTime) {
		return maintainRecordDAO.countSix(user,actualEndTime);
	}

	/**
	 * 根据用户id查询此车主所有预约记录
	 */
	@Override
	public int countByOwner(String userId) {
		return maintainRecordDAO.countByOwner(userId);
	}

	@Override
	public List<MaintainRecord> queryByOwner(Pager pager, String userId) {
		return maintainRecordDAO.queryByOwner(pager, userId);
	}

	@Override
	public List<MaintainRecord> queryByPagerSuccess(Pager pager) {
		return maintainRecordDAO.queryByPagerSuccess(pager);
	}

	@Override
	public int countSuccess(User user) {
		return maintainRecordDAO.countSuccess(user);
	}

	@Override
	public List<MaintainRecord> queryCheckinAll(String userIds) {
		return maintainRecordDAO.queryCheckinAll(userIds);
	}

	@Override
	public List<MaintainRecord> queryByBackstage(Pager pager) {
		return null;
	}

	@Override
	public void updateActualEndTime(String maintainRecordId) {
		maintainRecordDAO.updateActualEndTime(maintainRecordId);
	}

	@Override
	public void updatePickupTime(String maintainRecordId) {
		maintainRecordDAO.updatePickupTime(maintainRecordId);
	}
}