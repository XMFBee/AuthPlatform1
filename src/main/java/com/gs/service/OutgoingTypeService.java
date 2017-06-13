package com.gs.service;

import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:10:13
*@des 模块表Service
*/
public interface OutgoingTypeService extends BaseService<String, OutgoingType>{


    /**
     * 分页查询被禁用的记录
     */
    public List<OutgoingType> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();


    public OutgoingType queryById(String outTypeName, String outTypeId);

}