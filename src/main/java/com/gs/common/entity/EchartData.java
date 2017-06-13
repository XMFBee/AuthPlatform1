package com.gs.common.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2017/5/2.
 */
public class EchartData {

    public List<String> legend = new ArrayList<String>();// 数据分组
    public List<Double> category = new ArrayList<Double>();// 横坐标
    public List<Series> series = new ArrayList<Series>();// 纵坐标

    public EchartData(List<String> legendList, List<Double> categoryList,
                      List<Series> seriesList) {
        super();
        this.legend = legendList;
        this.category = categoryList;
        this.series = seriesList;
    }
}
