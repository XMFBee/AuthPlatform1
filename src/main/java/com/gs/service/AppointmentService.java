package com.gs.service;

import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;


/**
 *由CSWangBin技术支持
 *
 *@author CSWangBin
 *@since 2017-04-17 15:51:10
 *@des 预约service
 */
public interface AppointmentService extends BaseService<String, Appointment>{

    public void updateCurrentById(String currentStatus, String appointmentId);
    /**
     * 根据当前状态查询预约记录
     */
    public List<Appointment> queryByCurrentStatus(Pager pager);

    public int countByCurrentStatus(User user);

    /**
     * 根据用户id查询此车主所有预约记录
     */
    public int countByOwner(String userId);

    public List<Appointment> queryByOwner(Pager pager, String userId);

    // 根据注册的手机号查询是否有预约记录为此手机号的预约记录
    public List<Appointment> queryByPhone(String userPhone);
    // 查询出有的预约id进行批量更新
    public void updateUserIds(String userId, String appIds);

    //根据companyId查询展示的预约信息
    public List<Appointment> queryByCompanyId(String companyId);
}