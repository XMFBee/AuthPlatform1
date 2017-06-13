package com.gs.dao;

import com.gs.bean.Flow;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *@author 程燕
 *@since  2017-5-6
 *@des 公司表dao
 */
@Repository
public interface FlowDAO extends BaseDAO<String,Flow> {
    public List queryAcquisitionByPager(@Param("companyId")String companyId, @Param("pager")Pager pager);
    public List queryReturnedByPager(@Param("companyId")String companyId, @Param("pager")Pager pager);
    public List queryHistoryByPager(@Param("companyId")String companyId, @Param("pager")Pager pager);

    public int countAcquisition(String companyId);
    public int countReturned(String companyId);
    public int countHistory(String companyId);
}
