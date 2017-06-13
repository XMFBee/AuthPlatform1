package com.gs.bean;

import java.util.Arrays;
import java.util.Date;

public class IncomingOutgoing {
    private String inOutId;

    private String inTypeId;

    private String outTypeId;

    private Double inOutMoney;

    private String inOutCreatedUser;

    private Date inOutCreatedTime;

    private String inOutStatus;

    private String companyId;



    private double outMoneys[];
    private double inMoneys[];
    private OutgoingType outgoingType;
    private IncomingType incomingType;
    private User user;
    private Company company;


    public double[] getOutMoneys() {
        return outMoneys;
    }

    public void setOutMoneys(double[] outMoneys) {
        this.outMoneys = outMoneys;
    }

    public double[] getInMoneys() {
        return inMoneys;
    }

    public void setInMoneys(double[] inMoneys) {
        this.inMoneys = inMoneys;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public OutgoingType getOutgoingType() {
        return outgoingType;
    }

    public void setOutgoingType(OutgoingType outgoingType) {
        this.outgoingType = outgoingType;
    }

    public IncomingType getIncomingType() {
        return incomingType;
    }

    public void setIncomingType(IncomingType incomingType) {
        this.incomingType = incomingType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInOutId() {
        return inOutId;
    }

    public void setInOutId(String inOutId) {
        this.inOutId = inOutId;
    }

    public String getInTypeId() {
        return inTypeId;
    }

    public void setInTypeId(String inTypeId) {
        this.inTypeId = inTypeId;
    }

    public String getOutTypeId() {
        return outTypeId;
    }

    public void setOutTypeId(String outTypeId) {
        this.outTypeId = outTypeId;
    }

    public Double getInOutMoney() {
        return inOutMoney;
    }

    public void setInOutMoney(Double inOutMoney) {
        this.inOutMoney = inOutMoney;
    }

    public String getInOutCreatedUser() {
        return inOutCreatedUser;
    }

    public void setInOutCreatedUser(String inOutCreatedUser) {
        this.inOutCreatedUser = inOutCreatedUser;
    }

    public Date getInOutCreatedTime() {
        return inOutCreatedTime;
    }

    public void setInOutCreatedTime(Date inOutCreatedTime) {
        this.inOutCreatedTime = inOutCreatedTime;
    }

    public String getInOutStatus() {
        return inOutStatus;
    }

    public void setInOutStatus(String inOutStatus) {
        this.inOutStatus = inOutStatus;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "IncomingOutgoing{" +
                "inOutId='" + inOutId + '\'' +
                ", inTypeId='" + inTypeId + '\'' +
                ", outTypeId='" + outTypeId + '\'' +
                ", inOutMoney=" + inOutMoney +
                ", inOutCreatedUser='" + inOutCreatedUser + '\'' +
                ", inOutCreatedTime=" + inOutCreatedTime +
                ", inOutStatus='" + inOutStatus + '\'' +
                ", companyId='" + companyId + '\'' +
                ", outMoneys=" + Arrays.toString(outMoneys) +
                ", inMoneys=" + Arrays.toString(inMoneys) +
                ", outgoingType=" + outgoingType +
                ", incomingType=" + incomingType +
                ", user=" + user +
                ", company=" + company +
                '}';
    }
}