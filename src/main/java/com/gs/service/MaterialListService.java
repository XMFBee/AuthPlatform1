package com.gs.service;

import com.gs.bean.MaterialList;
import com.gs.bean.User;
import com.gs.bean.view.MaterialURTemp;
import com.gs.bean.view.MaterialView;
import com.gs.common.bean.Pager;

import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:06:48
*@des 物料清单Service
*/
public interface MaterialListService extends BaseService<String, MaterialList>{
    public List<MaterialView> queryByPager(String userId, Pager pager);

    public int count(String userId);

    public List<MaterialList> recordAccsByPager(String recordId, User user, Pager pager);
    public int countRecordAccs(String recordId, User user);

    public void insertList(List<MaterialList> materialLists);

    // 与流程相关, 副查询
    public MaterialURTemp queryFlowVarsByRecordId(String recordId, String accId);
}