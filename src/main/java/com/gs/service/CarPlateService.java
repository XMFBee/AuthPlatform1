package com.gs.service;

import com.gs.bean.CarPlate;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:55:06
*@des 汽车车牌Service
*/
public interface CarPlateService extends BaseService<String, CarPlate>{

    /**
     * 查询此车牌名称是否已存在
     */
    public int queryplateName(String plateName,String plateId);
}