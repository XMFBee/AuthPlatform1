package com.gs.dao;

import com.gs.bean.*;
import com.gs.bean.view.*;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:08:01
*@des 领料信息dao
*/
@Repository
public interface MaterialUseDAO extends BaseDAO<String, MaterialUse>{

    public List<MaterialURTemp> materialByPager(@Param("pager")Pager pager);
    public List<MaterialURTemp> materialByPager(@Param("pager")Pager pager,@Param("userId")String userId);
    public List<MaterialURTemp> queryMaterialFlowingbyPager(Map hashMap);
    public List queryHistoryFlowingbyPager(Map hashMap);
    public List queryUserFlowingByPager(Map hashMap);


    public int countMaterials();
    public int countMaterials(@Param("userId")String userId);
    public int countMaterialFlowing(Map hashMap);
    public int countHistoryFlowing(Map hashMap);
    public int countUserFlowing(Map hashMap);



    /**
     *
     * 不是应该放在这个Bean中的,但是会与其他人的有碰撞,所以放在这里
     *
     */
    public List<RecordBaseView> queryCurRecordsByPager(@Param("companyId")String companyId,@Param("hasEmp")String hasEmp, @Param("pager")Pager pager);
    public int countNoUseRecord(@Param("companyId")String companyId, @Param("hasEmp")String hasEmp);
    public List<User> companyEmps(@Param("companyId")String companyId);
    public int insertWorkInfo(WorkInfo workInfo);

    public List<WorkInfo> userWorksByPager(@Param("user")User user,@Param("pager") Pager pager);
    public List<WorkInfo> userWorksByPager(@Param("user")User user,@Param("status")String status,@Param("pager") Pager pager);

    public int countUserWorks(@Param("user")User user);
    public int countUserWorks(@Param("user")User user, @Param("status")String status);

    public WorkInfo queryWorkInfoByRecordId(@Param("recordId")String recordId);
    public List<DetailTemp> queryDetailsByRecordId(@Param("recordId")String recordId, @Param("companyId")String companyId, @Param("pager")Pager pager);
    public int countDetailsByRecordId(@Param("recordId")String recordId,@Param("companyId")String companyId);

    public int updWorkInfoUser(@Param("recordId")String recordId, @Param("userId")String userId);

    public Accessories accQueryById(String accId);

    public List accQueryAll(User user);


    public int updRunProInstStartUser(@Param("newUserId")String newUserId, @Param("recordId")String recordId, @Param("flowName")String flowName);

    public String queryUserIdbyRecordId4workInfo(@Param("recordId")String recordId);


    public List<Company> queryNearCompanys(Map pointsMap);

    /**
     * 库存使用情况报表
     */
    public List<MaterialUse> queryByCondition(@Param("start")String start, @Param("end")String end, @Param("companyId")String companyId, @Param("accTypeId") String accTypeId, @Param("type")String type);
}