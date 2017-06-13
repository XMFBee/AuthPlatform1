package com.gs.bean.echarts;

/**
 * Created by Shinelon on 2017/5/11.
 */
public class MaintainRecordBean {
    private String date;
    private int maintainCount; //保养
    private int preserveCount; //维修

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaintainCount() {
        return maintainCount;
    }

    public void setMaintainCount(int maintainCount) {
        this.maintainCount = maintainCount;
    }

    public int getPreserveCount() {
        return preserveCount;
    }

    public void setPreserveCount(int preserveCount) {
        this.preserveCount = preserveCount;
    }
}
