package com.gs.bean.view;

import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainRecord;

import java.util.List;

/**
 * Created by Administrator on 2017-04-27.
 */
public class DetailTemp extends MaintainDetail {
    public MaintainRecord getRecord() {
        return record;
    }

    public void setRecord(MaintainRecord record) {
        this.record = record;
    }

    private MaintainRecord record;


}
