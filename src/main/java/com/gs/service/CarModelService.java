package com.gs.service;

import com.gs.bean.CarModel;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:54:17
*@des 汽车车型Service
*/
public interface CarModelService extends BaseService<String, CarModel>{

 public List<CarModel> queryByBrandId(String id);

 /**
  * 查询此车型名称是否已存在
  */
 public int querymodelName(String modelName,String modelId);
}
