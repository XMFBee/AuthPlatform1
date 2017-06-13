package com.gs.service;

import com.gs.bean.TrackList;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:50
*@des 
*/
public interface TrackListService extends BaseService<String, TrackList>{

    public List<TrackList> queryByPagerName(Pager pager, TrackList trackList);

    public int countName(TrackList trackList, User user);
}