package com.gs.dao;

import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.activiti.engine.impl.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.applet.Main;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:02:40
*@des 维修保养项目dao
*/
@Repository
public interface MaintainFixDAO extends BaseDAO<String, MaintainFix>{
    /**
     * 分页查询被禁用的保养记录
     */
    public List<MaintainFix> queryByPagerDisable(Pager pager);
    /**
     * 分页查询被禁用的保养记录
     */
    public int countByDisable(User user);

    public int querymaintainName(@Param("maintainName")String maintainName,@Param("maintainId")String maintainId);

    public int querymaintainNameMaintain(@Param("maintainName")String maintainName,@Param("maintainId")String maintainId);

//    分页查询保养项目
    public List<MaintainFix> queryByPagerMaintain(Pager pager);
//    分页查询保养项目总数
    public int countMaintain(User user);

    public List<MaintainFix> queryByPagerAll(Pager pager);

    public int countqueryByPagerAll(User user);

    public List<MaintainFix> queryByPagerDisableService(Pager pager);

    public int countByDisableService(User user);

    /*<!--根据汽修公司， 汽修项目，查询项目名字（id）-->*/
    public List<MaintainFix> queryByMaintainName(@Param("companyId")String companyId, @Param("maintainOrFix")String maintainOrFix);

    public List<MaintainFix> queryByCondition(@Param("start")String start, @Param("end") String end,@Param("maintainId")String maintainId,
                                              @Param("companyId")String companyId, @Param("maintainOrFix")String maintainOrFix, @Param("type")String type);

    public List<MaintainFix> queryByCompanyId(String companyId);
}