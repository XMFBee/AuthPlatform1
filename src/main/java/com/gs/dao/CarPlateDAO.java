package com.gs.dao;

import com.gs.bean.CarPlate;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:55:04
*@des 汽车车牌dao
*/
@Repository
public interface CarPlateDAO extends BaseDAO<String, CarPlate>{

    //模糊查询
    public List<CarPlate> blurredQuery(@Param("pager")Pager pager,@Param("carPlate")CarPlate carPlate);

    //模糊查询记录
    public int countByBlurred(@Param("carPlate")CarPlate carPlate,@Param("user")User user);

    /**
     * 查询此车牌名称是否已存在
     */
    public int queryplateName(@Param("plateName")String plateName,@Param("plateId")String plateId);
 }