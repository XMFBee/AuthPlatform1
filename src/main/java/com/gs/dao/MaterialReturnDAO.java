package com.gs.dao;

import com.gs.bean.Accessories;
import com.gs.bean.MaterialReturn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author 程燕
*@since 2017-04-17 16:07:25
*@des 退料信息dao
*/
@Repository
public interface MaterialReturnDAO extends BaseDAO<String, MaterialReturn>{

    /**
     * 库存使用情况报表
     */
    public List<MaterialReturn> queryByCondition(@Param("start")String start, @Param("end")String end, @Param("companyId")String companyId, @Param("accTypeId") String accTypeId, @Param("type")String type);
}