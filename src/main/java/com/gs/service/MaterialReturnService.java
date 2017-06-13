package com.gs.service;

import com.gs.bean.Accessories;
import com.gs.bean.MaterialReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:07:26
*@des 退料信息Service
*/
public interface MaterialReturnService extends BaseService<String, MaterialReturn>{

    /**
     * 库存报表
     */
    public List<MaterialReturn> queryByCondition(String start, String end, String companyId, String accTypeId,String type);
}