package com.gs.bean.view;

import com.gs.bean.MaintainRecord;
import com.gs.bean.WorkInfo;

/**
 * Temp
 *
 * @author 程燕
 * @create 2017-04-23 18:23
 */
public class RecordBaseView {
    private String recordId;
    private int hours;
    private String carplate;
    private String carmodel;
    private MaintainRecord record;
    private WorkInfo workInfo;



    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public MaintainRecord getRecord() {
        return record;
    }

    public void setRecord(MaintainRecord record) {
        this.record = record;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCarplate() {
        return carplate;
    }

    public void setCarplate(String carplate) {
        this.carplate = carplate;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public WorkInfo getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(WorkInfo workInfo) {
        this.workInfo = workInfo;
    }

    @Override
    public String toString() {
        return "RecordBaseView{" +
                "record=" + record +
                ", hours=" + hours +
                ", carplate='" + carplate + '\'' +
                ", carmodel='" + carmodel + '\'' +
                '}';
    }
}
