package com.gs.bean;

import java.util.Date;

public class Salary {
    private String salaryId;

    private String userId;

    private Double prizeSalary;

    private Double minusSalay;

    private Double totalSalary;

    private String salaryDes;

    private Date salaryTime;

    private Date salaryCreatedTime;

    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getPrizeSalary() {
        return prizeSalary;
    }

    public void setPrizeSalary(Double prizeSalary) {
        this.prizeSalary = prizeSalary;
    }

    public Double getMinusSalay() {
        return minusSalay;
    }

    public void setMinusSalay(Double minusSalay) {
        this.minusSalay = minusSalay;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getSalaryDes() {
        return salaryDes;
    }

    public void setSalaryDes(String salaryDes) {
        this.salaryDes = salaryDes;
    }

    public Date getSalaryTime() {
        return salaryTime;
    }

    public void setSalaryTime(Date salaryTime) {
        this.salaryTime = salaryTime;
    }

    public Date getSalaryCreatedTime() {
        return salaryCreatedTime;
    }

    public void setSalaryCreatedTime(Date salaryCreatedTime) {
        this.salaryCreatedTime = salaryCreatedTime;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId='" + salaryId + '\'' +
                ", userId='" + userId + '\'' +
                ", prizeSalary=" + prizeSalary +
                ", minusSalay=" + minusSalay +
                ", totalSalary=" + totalSalary +
                ", salaryDes='" + salaryDes + '\'' +
                ", salaryTime=" + salaryTime +
                ", salaryCreatedTime=" + salaryCreatedTime +
                ", user=" + user +
                '}';
    }
}