package com.gs.bean;

import java.util.Date;

public class MaintainRemind {
    private String remindId;

    private String userId;

    private Date lastMaintainTime;

    private Double lastMaintainMileage;

    private String remindMsg;

    private Date remindTime;

    private String remindType;

    private Date remindCreatedTime;

    private String companyId;

    private Checkin checkin;

    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Checkin getCheckin() {
        return checkin;
    }

    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    public String getRemindId() {
        return remindId;
    }

    public void setRemindId(String remindId) {
        this.remindId = remindId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastMaintainTime() {
        return lastMaintainTime;
    }

    public void setLastMaintainTime(Date lastMaintainTime) {
        this.lastMaintainTime = lastMaintainTime;
    }

    public Double getLastMaintainMileage() {
        return lastMaintainMileage;
    }

    public void setLastMaintainMileage(Double lastMaintainMileage) {
        this.lastMaintainMileage = lastMaintainMileage;
    }

    public String getRemindMsg() {
        return remindMsg;
    }

    public void setRemindMsg(String remindMsg) {
        this.remindMsg = remindMsg;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemindType() {
        return remindType;
    }

    public void setRemindType(String remindType) {
        this.remindType = remindType;
    }

    public Date getRemindCreatedTime() {
        return remindCreatedTime;
    }

    public void setRemindCreatedTime(Date remindCreatedTime) {
        this.remindCreatedTime = remindCreatedTime;
    }
}