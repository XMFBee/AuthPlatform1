package com.gs.dao;

import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *由CSWangBin技术支持
 *
 *@author CSWangBin
 *@since 2017-04-17 15:51:08
 *@des 预约dao
 */
@Repository
public interface AppointmentDAO extends BaseDAO<String, Appointment>{

    /**
     * 模糊查询
     */
    public List<Appointment> blurredQuery(@Param("pager")Pager pager, @Param("appointment")Appointment appointment);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("appointment")Appointment appointment, @Param("user") User user);

    /**
     * 修改预约当前状态
     */
    public void updateCurrentById(@Param("currentStatus")String currentStatus,@Param("appointmentId")String appointmentId);

    /**
     * 根据当前状态查询预约记录
     */
    public List<Appointment> queryByCurrentStatus(Pager pager);

    public int countByCurrentStatus(User user);

    /**
     * 根据用户id查询此车主所有预约记录
     */
    public int countByOwner(String userId);

    public List<Appointment> queryByOwner(@Param("pager")Pager pager, @Param("userId")String userId);
    // 根据注册的手机号查询是否有预约记录为此手机号的预约记录
    public List<Appointment> queryByPhone(String userPhone);
    // 查询出有的预约id进行批量更新
    public void updateUserIds(@Param("userId")String userId, @Param("appIds")String appIds);

    /**
     * 根据companyId查询对应的预约信息
     */
    public List<Appointment> queryByCompanyId(String companyId);
}