package com.gs.service;

import com.gs.bean.MaintainDetail;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:01:17
*@des 维修保养明细Service
*/
public interface MaintainDetailService extends BaseService<String, MaintainDetail>{
    public List<MaintainDetail> queryByDetailByPager(Pager pager, String maintainRecordId);
    public int countByDetail(String maintainRecordId);
    public List<MaintainDetail> queryByRecordId(String maintainRecordId);

    public List<MaintainDetail> queryByCondition(String start, String end, String companyId, String maintainId,String type);

    /**
     * 车主根据维修保养记录查询所有明细
     */
    public int countByOwner(String recordId, String userId);

    public List<MaintainDetail> queryByOwner(Pager pager, String recordId, String userId);
    /**
     * 前台查询最新的十条维修保养记录
     */
    public List<MaintainDetail> queryByFrontpage(Pager pager);

}