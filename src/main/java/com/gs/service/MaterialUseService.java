package com.gs.service;

import com.gs.bean.*;
import com.gs.bean.view.*;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:08:02
*@des 领料信息Service
*/
public interface MaterialUseService extends BaseService<String, MaterialUse>{

    public List<MaterialURTemp> histByPager(Pager pager);

    public List<MaterialURTemp> userHistByPager(Pager pager,String userId);

    public int countHist();
    public int countUserHist(String userId);

    public List<MaterialURTemp> queryUseFlowingbyPager(String flowName, User user, String roleId , Pager pager);

    public int countUseFlowing(String flowName,User user, String roleId);

    public List<MaterialURTemp> queryReturnFlowingbyPager(String flowName, User user, String roleId , Pager pager);

    public int countReturnFlowing(String flowName,User user, String roleId);

    public List queryHistoryFlowingbyPager(User user, String flowName, String taskKey, Pager pager);

    public int countHistoryFlowing(User user, String flowName);

    public List queryUserFlowingByPager(String flowName, User user, String reviewTaskName, Pager pager);

    public int countUserFlowing(String flowName, User user);

    /**
     *
     * 不是应该放在这个Bean中的,但是会与其他人的有碰撞,所以放在这里
     *
     */
    public List<RecordBaseView> queryNoUseRecord( String companyId, Pager pager);
    public List<RecordBaseView> queryHasUseRecord(String companyId, Pager pager);
    public int countNoUseRecord(String companyId);
    public int countHasUseRecord(String companyId);
    public List<User> companyEmps(String companyId);
    public int insertWorkInfo(WorkInfo workInfo);

    public List<WorkInfo> userWorksByPager(User user, Pager pager);
    public List<WorkInfo> userWorksStatusByPager(User user,String status,Pager pager);
    public int countUserWorks(User user);
    public int countUserWorksStatus(User user, String status);
    public boolean recordIsDisp(String recordId);
    public List<DetailTemp> queryDetailsByRecordId(String recordId, String companyId, Pager pager);
    public int countDetailsByRecordId(String recordId,String companyId);
    public int updWorkInfoUser(String recordId, String userId);

    public Accessories accQueryById(String accId);
    public List accQueryAll(User user);

    public int updRunProInstStartUser(String newUserId, String recordId, String flowName);
    public String queryUserIdbyRecordId4workInfo(String recordId);

    public List<Company> queryNearCompanys(Map pointsMap);

    /**
     * 库存使用情况报表
     */
    public List<MaterialUse> queryByCondition(String start, String end, String companyId,  String accTypeId, String type);
}