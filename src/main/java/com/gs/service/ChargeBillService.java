package com.gs.service;

import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:56:01
*@des 收费单据Service
*/
public interface ChargeBillService extends BaseService<String, ChargeBill>{
    /**
     * 确认收费
     */
    public void updateDate(String chargeBillId);
    /**
     * 查询消费统计记录
     * @param start
     * @param end
     * @param maintainOrFix
     * @param type
     * @return
     */
    public List<ChargeBill> queryByCondition( String start,  String end,  String userId, String maintainOrFix, String type);

    /**
     * 用户确认收费单据
     */
    public void updateCurrent(String id);

    /**
     * 车主查询收费单据
     */
    public int countByOwner(User frontUser);

    public List<ChargeBill> queryByOwner(Pager pager);
}