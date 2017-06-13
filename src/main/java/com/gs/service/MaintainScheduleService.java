package com.gs.service;

import com.gs.bean.MaintainSchedule;

import java.util.List;

/**
 * Created by jyy on 2017/5/4.
 */
public interface MaintainScheduleService extends BaseService<String, MaintainSchedule>{

    public List<MaintainSchedule> queryScheduleByRecord(String recordId);

    public void updateById(String maintainScheduleId);
}
