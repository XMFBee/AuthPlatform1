package com.gs.service;

import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:04:53
*@des 维修保养记录Service
*/
public interface MaintainRecordService extends BaseService<String, MaintainRecord>{
    /**
     * 此模糊查询为提车提醒的
     */
    public List<MaintainRecord> blurredQueryByRemind(Pager pager, MaintainRecord maintainRecord);

    /**
     * 此模糊查询的记录数为提车提醒的
     */
    public int countByBlurredByRemind(MaintainRecord maintainRecord, User user);

    /**
     * 提车提醒未提醒维修保养记录查询
     */
    public List<MaintainRecord> queryByPagerRemindNo(Pager pager);

    public int countByRemindNo(User user);

    /**
     *  维修记录报表
     */
    public List<MaintainRecord> queryByCondition(String start,String end, String companyId,String maintainOrFix, String type);

    /**
     * 修改维修保养实际结束时间
     */
    public void updateActualEndTime(String maintainRecordId);

    /**
     * 修改维修保养提车时间
     */
    public void updatePickupTime(String maintainRecordId);


    /**
     * 修改维修保养记录当前状态
     */
    public void updateCurrentStatus(String currentStatus, String recordId);

    /**
     * 提车提醒已提醒维修保养记录查询
     */
    public int countByRemindYes(User user);

    public List<MaintainRecord> queryByPagerRemindYes(Pager pager);

    public List<MaintainRecord> queryByPagerSix(Pager pager, String actualEndTime);

    public int countSix(User user, String actualEndTime);
    /**
     * 根据用户id查询此车主所有预约记录
     */
    public int countByOwner(String userId);

    public List<MaintainRecord> queryByOwner(Pager pager, String userId);

    public List<MaintainRecord> queryByPagerSuccess(Pager pager);

    public int countSuccess(User user);

    public List<MaintainRecord> queryCheckinAll(String userIds);

    /**
     * 后台首页查询最新的十条维修保养记录
     */
    public List<MaintainRecord> queryByBackstage(Pager pager);

}