package com.gs.service;

import com.gs.bean.*;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由CSWangBin技术支持
 *
 * @author CSWangBin
 * @des 收支表Service
 * @since 2017-04-17 15:59:05
 */
public interface IncomingOutgoingService extends BaseService<String, IncomingOutgoing> {

    /**
     * 分页查询被禁用的记录
     */
    public List<IncomingOutgoing> queryByPagerDisable(Pager pager);

    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    /**
     * 默认查询本月报表数据
     */
    public List<IncomingOutgoing> queryByDate(String inOutType, String companyId);


    /*
* 根据年，月，季度，周，日查询所有收支记录
* */
    public List<IncomingOutgoing> queryByCondition(String start, String end, String inOutType, String companyId, String type);

    /*
  * 批量添加
  * */
    public void addInsert(List<IncomingOutgoing> incomingOutgoings);


    public List<IncomingOutgoing> queryByCompanyIdForInType(String companyId);

    public List<IncomingOutgoing> queryByCompanyIdForOutType(String companyId);

}