package com.gs.dao;

import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:12:24
*@des 工单DAO
*/
@Repository
public interface WorkInfoDAO extends BaseDAO<String, WorkInfo>{

    public List<WorkInfo> queryByCondition(@Param("start") String start, @Param("end") String end, @Param("companyId") String companyId,@Param("maintainOrFix")String maintainOrFix, @Param("type") String type);

    public List<WorkInfo> queryByPagerschelude(Pager pager);

    public List<WorkInfo> blurredQuery(@Param("pager")Pager pager, @Param("workInfo")WorkInfo workInfo);

    public int countByBlurred(@Param("workInfo")WorkInfo workInfo, @Param("user")User user);

    public List<WorkInfo> queryByPager(Map paramMap);

    public int count(Map paramMap);
    // 车主维修保养进度查询
    public int countByFront(User frontUser);

    public List<WorkInfo> queryByFront(Pager pager);

}