package com.gs.service;

import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:02:40
*@des 维修保养项目Service
*/
public interface MaintainFixService extends BaseService<String, MaintainFix>{
    /**
     * 分页查询被禁用的记录
     */
    public List<MaintainFix> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable(User user);


    public int querymaintainName(String maintainName,String maintainId);

    public int querymaintainNameMaintain(String maintainName,String maintainId);

    public List<MaintainFix> queryByPagerMaintain(Pager pager);

    //    分页查询保养项目总数
    public int countMaintain(User user);

    public List<MaintainFix> queryByPagerAll(Pager pager);

    public int countqueryByPagerAll(User user);

    public List<MaintainFix> queryByPagerDisableService(Pager pager);

    public int countByDisableService(User user);

    /*<!--根据汽修公司， 汽修项目，查询项目名字（id）-->*/
    public List<MaintainFix> queryByMaintainName(String companyId, String maintainOrFix);

    /**
     * 维修项目报表
     * @param start
     * @param end
     * @param maintainId
     * @param companyId
     * @param maintainOrFix
     * @return
     */
    public List<MaintainFix> queryByCondition(String start, String end,String maintainId,
                                      String companyId, String maintainOrFix, String type);

    public List<MaintainFix> queryByCompanyId(String companyId);
}