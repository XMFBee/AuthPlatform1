package com.gs.dao;

import com.gs.bean.SupplyType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:38
*@des 
*/
@Repository
public interface SupplyTypeDAO extends BaseDAO<String, SupplyType>{

    /**
     * 分页查询被禁用的记录
     */
    public List<SupplyType> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    // 检查此供应商类型名称是否已经存在
    public int queryNameByOne(@Param("supplyTypeName") String supplyTypeName, @Param("supplyTypeId") String supplyTypeId);
}