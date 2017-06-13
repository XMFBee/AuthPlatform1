package com.gs.dao;

import com.gs.bean.Accessories;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:42:38
*@des 配件表dao
*/
@Repository
public interface AccessoriesDAO extends BaseDAO<String, Accessories>{
    /**
     * 模糊查询
     */
    public List<Accessories> blurredQuery(@Param("pager")Pager pager, @Param("accInv")Accessories accessories);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("accInv")Accessories accessories);

    public List<Accessories> queryByIdPager(@Param("id") String id, @Param("pager") Pager pager);

    public int updateCount(@Param("accCount") int accCount,@Param("accId") String accId);

    public int reduceCount(@Param("accCount") int accCount,@Param("accId") String accId);

    public Accessories queryByName(@Param("accName") String accName);

    public int countByBlurred(@Param("accessories") Accessories accessories, @Param("user") User user);

    /**
     * 库存报表
     */
    public List<Accessories> queryByCondition(@Param("start")String start, @Param("end")String end, @Param("companyId")String companyId, @Param("accTypeId") String accTypeId,@Param("type")String type);
}

