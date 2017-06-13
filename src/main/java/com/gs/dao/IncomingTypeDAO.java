package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:00:01
*@des 收支类型dao
*/
@Repository
public interface IncomingTypeDAO extends BaseDAO<String, IncomingType>{
    /**
     * 分页查询被禁用的记录
     */
    public List<IncomingType> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    public IncomingType queryById(@Param("inTypeName")String inTypeName, @Param("inTypeId")String inTypeId);

}