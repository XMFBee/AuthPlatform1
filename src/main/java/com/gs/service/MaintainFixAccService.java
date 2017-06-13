package com.gs.service;

import com.gs.bean.MaintainFix;
import com.gs.bean.MaintainFixAcc;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:03:18
*@des 维修保养项目配件关联Service
*/
public interface MaintainFixAccService extends BaseService<String, MaintainFixAcc>{
    public List<MaintainFixAcc> queryByRecord(String fixId);

    public List<MaintainFixAcc> queryByDetailsByPager(Pager pager, String maintainId);
    public int countByDetails(String maintainId,User user);
}