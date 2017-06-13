package com.gs.dao;

import com.gs.bean.CarModel;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:54:15
*@des 汽车车型dao
*/
@Repository
public interface CarModelDAO extends BaseDAO<String, CarModel>{

    public List<CarModel> queryByBrandId(String id);

    /**
     * 模糊查询
     */
    public List<CarModel> blurredQuery(@Param("pager")Pager pager, @Param("carModel")CarModel carModel);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("carModel")CarModel carModel, @Param("user")User user);

    /**
     * 查询此车型名称是否已存在
     */
    public int querymodelName(@Param("modelName")String modelName,@Param("modelId")String modelId);
}