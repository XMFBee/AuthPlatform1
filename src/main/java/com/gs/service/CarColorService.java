package com.gs.service;

import com.gs.bean.CarColor;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:53:24
*@des 汽车颜色service
*/
public interface CarColorService extends BaseService<String, CarColor>{

    /**
     * 查询此颜色名称是否已存在
     */
    public int querycolorName(String colorName,String colorId);
}