package com.gs.service;

import com.gs.bean.Checkin;
import com.gs.bean.Supply;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:27
*@des 
*/
public interface SupplyService extends BaseService<String, Supply>{

    // 检查此供应商名称是否已经存在
    public int queryNameByOne(String supplyName, String supplyId);

}