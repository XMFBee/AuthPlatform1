package com.gs.bean.echarts;

import java.util.Date;

/**
 * Created by Shinelon on 2017/5/5.
 */
public class IncomingOutInFo {

    private Double inMoney;
    private Double outMoney;
    private String date;
    private Date dateTime;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Double getInMoney() {
        return inMoney;
    }

    public void setInMoney(Double inMoney) {
        this.inMoney = inMoney;
    }

    public Double getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(Double outMoney) {
        this.outMoney = outMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "IncomingOutInFo{" +
                "inMoney=" + inMoney +
                ", outMoney=" + outMoney +
                ", date='" + date + '\'' +
                '}';
    }
}
