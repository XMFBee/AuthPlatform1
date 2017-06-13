package com.gs.dao;

import com.gs.bean.AccessoriesBuy;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *由CSWangBin技术支持
 *
 *@author CSWangBin
 *@since 2017-04-17 15:44:30
 *@des 配件采购dao
 */
@Repository
public interface AccessoriesBuyDAO extends BaseDAO<String, AccessoriesBuy>{
    /**
     * 分页查询被禁用的记录
     */
    public List<AccessoriesBuy> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    /**
     * 模糊查询
     */
    public List<AccessoriesBuy> blurredQuery(@Param("pager")Pager pager, @Param("accBuy")AccessoriesBuy accessoriesBuy);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("accessoriesBuy") AccessoriesBuy accessoriesBuy, @Param("user") User user);


    /**
     * 下单统计
     */
    public List<AccessoriesBuy> queryByCondition(@Param("start") String start, @Param("end")String end, @Param("companyId")String companyId,@Param("type")String type);


    /**
     * 支付统计
     */

    public List<AccessoriesBuy> queryByPayCondition(@Param("start") String start, @Param("end")String end, @Param("companyId")String companyId,@Param("type")String type);

}