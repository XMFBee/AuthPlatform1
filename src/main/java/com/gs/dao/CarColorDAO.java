package com.gs.dao;

import com.gs.bean.CarColor;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:53:23
*@des 汽车颜色dao
*/
@Repository
public interface CarColorDAO extends BaseDAO<String, CarColor>{
    /**
     * 模糊查询
     */
    public List<CarColor> blurredQuery(@Param("pager")Pager pager, @Param("carColor")CarColor carColor);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("carColor")CarColor carColor, @Param("user")User user);

    /**
     * 查询此颜色名称是否已存在
     */
    public int querycolorName(@Param("colorName")String colorName,@Param("colorId")String colorId);
}