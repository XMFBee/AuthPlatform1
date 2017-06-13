package com.gs.bean;



import java.util.Date;
/**
 * 维修保养进度管理
 * Created by jyy on 2017/5/4.
 */
public class MaintainSchedule {

    private String maintainScheduleId;//维修保养进度id
    private String maintainRecordId;//维修保养记录Id
    private String maintainScheduleDes;//维修保养进度描述
    private Date msCreatedTime;//维修保养创建时间
    private String msStatus;//维修保养进度状态
    private String currentStatus;//维修保养当前状态

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    private MaintainRecord maintainRecord;//维修保养记录表

    public MaintainRecord getMaintainRecord() {
        return maintainRecord;
    }

    public void setMaintainRecord(MaintainRecord maintainRecord) {
        this.maintainRecord = maintainRecord;
    }



    public String getMaintainScheduleId() {
        return maintainScheduleId;
    }

    public void setMaintainScheduleId(String maintainScheduleId) {
        this.maintainScheduleId = maintainScheduleId;
    }

    public String getMaintainRecordId() {
        return maintainRecordId;
    }

    public void setMaintainRecordId(String maintainRecordId) {
        this.maintainRecordId = maintainRecordId;
    }

    public String getMaintainScheduleDes() {
        return maintainScheduleDes;
    }

    public void setMaintainScheduleDes(String maintainScheduleDes) {
        this.maintainScheduleDes = maintainScheduleDes;
    }

    public Date getMsCreatedTime() {
        return msCreatedTime;
    }

    public void setMsCreatedTime(Date msCreatedTime) {
        this.msCreatedTime = msCreatedTime;
    }

    public String getMsStatus() {
        return msStatus;
    }

    public void setMsStatus(String msStatus) {
        this.msStatus = msStatus;
    }



    @Override
    public String toString() {
        return "MaintainSchedule{" +
                "maintainScheduleId='" + maintainScheduleId + '\'' +
                ", maintainRecordId='" + maintainRecordId + '\'' +
                ", maintainScheduleDes='" + maintainScheduleDes + '\'' +
                ", msCreatedTime=" + msCreatedTime +
                ", msStatus='" + msStatus + '\'' +
                '}';
    }
}
