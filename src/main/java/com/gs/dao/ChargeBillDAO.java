package com.gs.dao;

import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:55:59
*@des 收费单据dao
*/
@Repository
public interface ChargeBillDAO extends BaseDAO<String, ChargeBill>{
    /**
     * 模糊查询
     */
    public List<ChargeBill> blurredQuery(@Param("pager")Pager pager, @Param("chargeBill")ChargeBill chargeBill);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("chargeBill")ChargeBill chargeBill,@Param("user")User user);

    /**
     * 查询消费统计记录
     * @param start
     * @param end
     * @param companyId
     * @param maintainOrFix
     * @param type
     * @return
     */
    public List<ChargeBill> queryByCondition(@Param("start") String start, @Param("end") String end, @Param("userId") String companyId, @Param("maintainOrFix")String maintainOrFix, @Param("type") String type);
    /**
     * 确认收费
     */
    public void updateDate(String chargeBillId);
    /**
     * 用户确认收费单据
     */
    public void updateCurrent(String id);

    /**
     * 车主查询收费单据
     */
    public int countByOwner(User user);

    public List<ChargeBill> queryByOwner(Pager pager);
}