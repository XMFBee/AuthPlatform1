package com.gs.bean;

import java.util.Date;

/**
 * 收费单据bean, 张文星
 * */
public class ChargeBill {
    private String chargeBillId; // 收费单据编号

    private String maintainRecordId; //维修保养记录编号

    private Double chargeBillMoney; //收费总金额

    private String paymentMethod; //付款方式

    private Double actualPayment; //实付款

    private Date chargeTime; //收费时间

    private Date chargeCreatedTime; //收费单据创建时间

    private String chargeBillDes; //收费单据描述

    private String chargeBillStatus; //收费状态

    private String cdStatus; // 收费单据当前状态

    private MaintainRecord maintainRecord;

    public String getCdStatus() {
        return cdStatus;
    }

    public void setCdStatus(String cdStatus) {
        this.cdStatus = cdStatus;
    }

    public MaintainRecord getMaintainRecord() {
        return maintainRecord;
    }

    public void setMaintainRecord(MaintainRecord maintainRecord) {
        this.maintainRecord = maintainRecord;
    }

    public String getChargeBillId() {
        return chargeBillId;
    }

    public void setChargeBillId(String chargeBillId) {
        this.chargeBillId = chargeBillId;
    }

    public String getMaintainRecordId() {
        return maintainRecordId;
    }

    public void setMaintainRecordId(String maintainRecordId) {
        this.maintainRecordId = maintainRecordId;
    }

    public Double getChargeBillMoney() {
        return chargeBillMoney;
    }

    public void setChargeBillMoney(Double chargeBillMoney) {
        this.chargeBillMoney = chargeBillMoney;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(Double actualPayment) {
        this.actualPayment = actualPayment;
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    public Date getChargeCreatedTime() {
        return chargeCreatedTime;
    }

    public void setChargeCreatedTime(Date chargeCreatedTime) {
        this.chargeCreatedTime = chargeCreatedTime;
    }

    public String getChargeBillDes() {
        return chargeBillDes;
    }

    public void setChargeBillDes(String chargeBillDes) {
        this.chargeBillDes = chargeBillDes;
    }

    public String getChargeBillStatus() {
        return chargeBillStatus;
    }

    public void setChargeBillStatus(String chargeBillStatus) {
        this.chargeBillStatus = chargeBillStatus;
    }
}