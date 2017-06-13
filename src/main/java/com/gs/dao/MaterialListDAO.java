package com.gs.dao;

import com.gs.bean.MaterialList;
import com.gs.bean.view.MaterialURTemp;
import com.gs.bean.view.MaterialView;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:06:47
*@des 物料清单dao
*/
@Repository
public interface MaterialListDAO extends BaseDAO<String, MaterialList>{
    public List<MaterialView> queryByPager(@Param("userId") String userId, @Param("pager")Pager pager);

    public int count(@Param("userId")String userId);

    public List<MaterialList> recordAccsByPager(Map paramMap);
    public int countRecordAccs(Map paramMap);
    public void insertList(List<MaterialList> materialLists);

    // 与流程相关
    public List<MaterialURTemp> queryFlowVarsByRecordId(Map map);
}