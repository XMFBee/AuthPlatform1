package com.gs.service;

import com.gs.bean.SupplyType;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:39
*@des 
*/
public interface SupplyTypeService extends BaseService<String, SupplyType>{

    /**
     * 分页查询被禁用的记录
     */
    public List<SupplyType> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    // 检查此供应商类型名称是否已经存在
    public int queryNameByOne(String supplyTypeName, String supplyTypeId);

}