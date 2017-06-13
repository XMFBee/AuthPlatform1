package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.Supply;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:26
*@des 
*/
@Repository
public interface SupplyDAO extends BaseDAO<String, Supply>{

    /**
     * 分页查询被禁用的记录
     */
    public List<Supply> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    /**
     * 模糊查询
     */
    public List<Supply> blurredQuery(@Param("pager")Pager pager, @Param("supply")Supply supply);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("supply")Supply supply, @Param("user")User user);

    // 检查此供应商名称是否已经存在
    public int queryNameByOne(@Param("supplyName") String supplyName, @Param("supplyId") String supplyId);

}