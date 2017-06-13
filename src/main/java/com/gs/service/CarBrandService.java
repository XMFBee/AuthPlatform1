package com.gs.service;

import com.gs.bean.CarBrand;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:52:45
*@des 汽车品牌service
*/
public interface CarBrandService extends BaseService<String, CarBrand>{

    /**
     * 查询此品牌名称是否已存在此品牌名称
     */
    public int querybrandName(String brandName,String brandId);
}