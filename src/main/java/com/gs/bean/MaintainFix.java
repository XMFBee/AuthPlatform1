package com.gs.bean;

import java.util.Date;

/**
 * 维修保养项目表
 */
public class MaintainFix {
    private String maintainId;//维修保养项目编号

    private String maintainName;//维修保养项目名称

    private Double maintainHour;//维修保养项目工时

    private Double maintainMoney;//维修保养项目基础费用

    private Double maintainManHourFee;//维修保养项目工时费

    private String maintainOrFix;//标识是保养还是维修

    private String maintainDes;//维修保养项目描述

    private String maintainStatus;//维修保养项目状态

    private String companyId;

    /**
     * 统计维修项目的记录
     */
    private int count;

    private Date mdCreatedTime;

    public Date getMdCreatedTime() {
        return mdCreatedTime;
    }

    public void setMdCreatedTime(Date mdCreatedTime) {
        this.mdCreatedTime = mdCreatedTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    private Company company;//关联Company表

    private MaintainFixAcc maintainFixAcc;//关联MaintainFixAcc表

    private Accessories accessories;//关联Accessories

    private AccessoriesType accessoriesType;//关联accessoriesType

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getMaintainName() {
        return maintainName;
    }

    public void setMaintainName(String maintainName) {
        this.maintainName = maintainName;
    }

    public Double getMaintainHour() {
        return maintainHour;
    }

    public void setMaintainHour(Double maintainHour) {
        this.maintainHour = maintainHour;
    }

    public Double getMaintainMoney() {
        return maintainMoney;
    }

    public void setMaintainMoney(Double maintainMoney) {
        this.maintainMoney = maintainMoney;
    }

    public Double getMaintainManHourFee() {
        return maintainManHourFee;
    }

    public void setMaintainManHourFee(Double maintainManHourFee) {
        this.maintainManHourFee = maintainManHourFee;
    }

    public String getMaintainOrFix() {
        return maintainOrFix;
    }

    public void setMaintainOrFix(String maintainOrFix) {
        this.maintainOrFix = maintainOrFix;
    }

    public String getMaintainDes() {
        return maintainDes;
    }

    public void setMaintainDes(String maintainDes) {
        this.maintainDes = maintainDes;
    }

    public String getMaintainStatus() {
        return maintainStatus;
    }

    public void setMaintainStatus(String maintainStatus) {
        this.maintainStatus = maintainStatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public MaintainFixAcc getMaintainFixAcc() {
        return maintainFixAcc;
    }

    public void setMaintainFixAcc(MaintainFixAcc maintainFixAcc) {
        this.maintainFixAcc = maintainFixAcc;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public AccessoriesType getAccessoriesType() {
        return accessoriesType;
    }

    public void setAccessoriesType(AccessoriesType accessoriesType) {
        this.accessoriesType = accessoriesType;
    }

    @Override
    public String toString() {
        return "MaintainFix{" +
                "maintainId='" + maintainId + '\'' +
                ", maintainName='" + maintainName + '\'' +
                ", maintainHour=" + maintainHour +
                ", maintainMoney=" + maintainMoney +
                ", maintainManHourFee=" + maintainManHourFee +
                ", maintainOrFix='" + maintainOrFix + '\'' +
                ", maintainDes='" + maintainDes + '\'' +
                ", maintainStatus='" + maintainStatus + '\'' +
                ", company=" + company +
                ", maintainFixAcc=" + maintainFixAcc +
                ", accessories=" + accessories +
                ", accessoriesType=" + accessoriesType +
                '}';
    }
}