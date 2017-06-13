package com.gs.dao;

import com.gs.bean.TrackList;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:49
*@des 
*/
@Repository
public interface TrackListDAO extends BaseDAO<String, TrackList>{

    public List<TrackList> queryByPagerName(@Param("pager") Pager pager, @Param("trackList") TrackList trackList);

    public int countName(@Param("trackList") TrackList trackList, @Param("user") User user);
}