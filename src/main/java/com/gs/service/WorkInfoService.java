package com.gs.service;

import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:12:25
*@des 
*/
public interface WorkInfoService extends BaseService<String, WorkInfo>{

    public List<WorkInfo> queryByCondition(String start, String end,String companyId,String maintainOrFix, String type);

    public List<WorkInfo> queryByPagerschelude(Pager pager);

    public List<WorkInfo> queryByPager(Pager pager, String status);

    public int count(User user , String status);

    // 车主维修保养进度查询
    public int countByFront(User frontUser);

    public List<WorkInfo> queryByFront(Pager pager);
}