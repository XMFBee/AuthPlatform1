package com.gs.service;

import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:00:03
*@des 收支类型Service
*/
public interface IncomingTypeService extends BaseService<String, IncomingType>{
    /**
     * 分页查询被禁用的记录
     */
    public List<IncomingType> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    public IncomingType queryById(String inTypeName, String inTypeId);
}