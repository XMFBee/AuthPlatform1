package com.gs.service;

import com.gs.bean.Checkin;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:56:48
*@des 登记表Service
*/
public interface CheckinService extends BaseService<String, Checkin>{
    // 根据注册的手机号查询是否有登记记录为此手机号的登记记录
    public void updateUserIds(String userId, String chIds);
    // 查询出有的登记id进行批量更新
    public List<Checkin> queryByPhone(String userPhone);
}