package com.gs.dao;

import com.gs.bean.CarBrand;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:52:44
*@des 汽车品牌dao
*/
@Repository
public interface CarBrandDAO extends BaseDAO<String, CarBrand>{

    /**
     * 模糊查询
     */
    public List<CarBrand> blurredQuery(@Param("pager")Pager pager, @Param("carBrand")CarBrand carBrand);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("carBrand")CarBrand carBrand, @Param("user")User user);

    /**
     * 查询此品牌名称是否已存在此品牌名称
     */
    public int querybrandName(@Param("brandName")String brandName,@Param("brandId")String brandId);
}