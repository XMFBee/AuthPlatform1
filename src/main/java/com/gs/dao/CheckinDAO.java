package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:56:47
*@des 登记表dao
*/
@Repository
public interface CheckinDAO extends BaseDAO<String, Checkin>{
    /**
     * 模糊查询
     */
    public List<Checkin> blurredQuery(@Param("pager")Pager pager, @Param("checkin")Checkin checkin);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("checkin")Checkin checkin, @Param("user") User user);
    // 根据注册的手机号查询是否有登记记录为此手机号的登记记录
    public void updateUserIds(@Param("userId")String userId, @Param("chIds")String chIds);
    // 查询出有的登记id进行批量更新
    public List<Checkin> queryByPhone(String userPhone);
}