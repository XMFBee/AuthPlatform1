package com.gs.dao;

import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:04:52
*@des 维修保养记录dao
*/
@Repository
public interface MaintainRecordDAO extends BaseDAO<String, MaintainRecord>{
    /**
     * 模糊查询
     */
    public List<MaintainRecord> blurredQuery(@Param("pager")Pager pager, @Param("maintainRecord")MaintainRecord maintainRecord);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("maintainRecord")MaintainRecord maintainRecord, @Param("user")User user);
    /**
     * 模糊查询
     */
    public List<MaintainRecord> blurredQueryByRemind(@Param("pager")Pager pager, @Param("maintainRecord")MaintainRecord maintainRecord);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurredByRemind(@Param("maintainRecord")MaintainRecord maintainRecord, @Param("user")User user);

    /**
     *  维修记录报表
     */
    public List<MaintainRecord> queryByCondition(@Param("start") String start,@Param("end") String end, @Param("companyId") String companyId,@Param("maintainOrFix")String maintainOrFix, @Param("type") String type);

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
    public void updateCurrentStatus(@Param("currentStatus")String currentStatus, @Param("recordId")String recordId);

    /**
     * 提车提醒未提醒维修保养记录查询
     */
    public List<MaintainRecord> queryByPagerRemindNo(Pager pager);

    public int countByRemindNo(User user);

    /**
     * 提车提醒已提醒维修保养记录查询
     */
    public int countByRemindYes(User user);

    public List<MaintainRecord> queryByPagerRemindYes(Pager pager);

    public List<MaintainRecord> queryByPagerSix(@Param("pager")Pager pager, @Param("actualEndTime") String actualEndTime);

    public int countSix(@Param("user") User user, @Param("actualEndTime") String actualEndTime);

    /**
     * 根据用户id查询此车主所有预约记录
     */
    public int countByOwner(String userId);

    public List<MaintainRecord> queryByOwner(@Param("pager")Pager pager, @Param("userId")String userId);

    public List<MaintainRecord> queryByPagerSuccess(Pager pager);

    public int countSuccess(User user);

    public List<MaintainRecord> queryCheckinAll(String userIds);

    /**
     * 后台首页查询最新的十条维修保养记录
     */
    public List<MaintainRecord> queryByBackstage(Pager pager);
}