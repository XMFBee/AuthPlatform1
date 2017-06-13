package com.gs.dao;

import com.gs.bean.AccessoriesSale;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:47:04
*@des 配件销售dao
*/
@Repository
public interface AccessoriesSaleDAO extends BaseDAO<String, AccessoriesSale>{
    /**
     * 分页查询被禁用的记录
     */
    public List<AccessoriesSale> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    /**
     * 模糊查询
     */
    public List<AccessoriesSale> blurredQuery(@Param("pager")Pager pager, @Param("accSale")AccessoriesSale accessoriesSale);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("accessoriesSale") AccessoriesSale accessoriesSale, @Param("user") User user);
}