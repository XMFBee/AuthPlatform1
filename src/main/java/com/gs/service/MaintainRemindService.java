package com.gs.service;

import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:05:48
*@des 保养提醒记录Service
*/
public interface MaintainRemindService extends BaseService<String, MaintainRemind>{

    public List<MaintainRemind> queryByPagerNull(Pager pager);

    public int countNull(User user);

    public void insertBatch(List<MaintainRemind> maintainReminds);

    public List<MaintainRemind> queryByPagerUser(Pager pager, String userId);

    public int countUser(String userId);
}