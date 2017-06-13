package com.gs.dao;

import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:05:47
*@des 保养提醒记录dao
*/
@Repository
public interface MaintainRemindDAO extends BaseDAO<String, MaintainRemind>{

    public List<MaintainRemind> queryByPagerNull(@Param("pager") Pager pager);

    public int countNull(@Param("user") User user);

    public void insertBatch(List<MaintainRemind> maintainReminds);

    public List<MaintainRemind> queryByPagerUser(@Param("pager") Pager pager, @Param("userId") String userId);

    public int countUser(@Param("userId") String userId);

    public List<MaintainRemind> blurredQuery(@Param("pager") Pager pager,@Param("maintainRemind") MaintainRemind maintainRemind);

    public int countByBlurred(@Param("maintainRemind") MaintainRemind maintainRemind, @Param("user") User user);
}