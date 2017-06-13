package com.gs.bean;

import java.util.Date;

/**
 * 维修保养明细bean, 张文星
 * */
public class MaintainDetail {
    private String maintainDetailId; //维修保养明细编号

    private String maintainRecordId; //维修保养记录编号

    private String maintainItemId; //维修保养项目编号

    private Double maintainDiscount; //维修保养项目折扣

    private Date mdcreatedTime; //维修保养明细创建时间

    private String mdStatus; // 维修保养明细状态

    private MaintainFix maintainFix;

    private MaintainRecord maintainRecord;

    /**
     * 根据维修项目名字统计维修记录数
     */
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // 打印需要字段
    private String disCount; // 折扣

    public MaintainRecord getMaintainRecord() {
        return maintainRecord;
    }

    public void setMaintainRecord(MaintainRecord maintainRecord) {
        this.maintainRecord = maintainRecord;
    }

    public String getDisCount() {
        return disCount;
    }

    public void setDisCount(String disCount) {
        this.disCount = disCount;
    }

    public MaintainFix getMaintainFix() {
        return maintainFix;
    }

    public void setMaintainFix(MaintainFix maintainFix) {
        this.maintainFix = maintainFix;
    }

    public String getMaintainDetailId() {
        return maintainDetailId;
    }

    public void setMaintainDetailId(String maintainDetailId) {
        this.maintainDetailId = maintainDetailId;
    }

    public String getMaintainRecordId() {
        return maintainRecordId;
    }

    public void setMaintainRecordId(String maintainRecordId) {
        this.maintainRecordId = maintainRecordId;
    }

    public String getMaintainItemId() {
        return maintainItemId;
    }

    public void setMaintainItemId(String maintainItemId) {
        this.maintainItemId = maintainItemId;
    }

    public Double getMaintainDiscount() {
        return maintainDiscount;
    }

    public void setMaintainDiscount(Double maintainDiscount) {
        this.maintainDiscount = maintainDiscount;
    }

    public Date getMdcreatedTime() {
        return mdcreatedTime;
    }

    public void setMdcreatedTime(Date mdcreatedTime) {
        this.mdcreatedTime = mdcreatedTime;
    }

    public String getMdStatus() {
        return mdStatus;
    }

    public void setMdStatus(String mdStatus) {
        this.mdStatus = mdStatus;
    }

    @Override
    public String toString() {
        return "MaintainDetail{" +
                "maintainDetailId='" + maintainDetailId + '\'' +
                ", maintainRecordId='" + maintainRecordId + '\'' +
                ", maintainItemId='" + maintainItemId + '\'' +
                ", maintainDiscount=" + maintainDiscount +
                ", mdcreatedTime=" + mdcreatedTime +
                '}';
    }
}