package com.gs.dao;

import com.gs.bean.Complaint;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:58:14
*@des 投诉表dao
*/
@Repository
public interface ComplaintDAO extends BaseDAO<String, Complaint>{

    public List<Complaint> queryByPagerName(@Param("pager") Pager pager, @Param("complaint") Complaint complaint);

    public int countName(@Param("complaint") Complaint complaint, @Param("user") User user);

    public List<Complaint> queryByPagerComplaintUser(@Param("pager") Pager pager, @Param("userId") String userId);

    public int countComplaintUser(String userId);
}