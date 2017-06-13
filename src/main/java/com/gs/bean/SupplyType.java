package com.gs.bean;

/*
供应商类型管理
 */
public class SupplyType {
    private String supplyTypeId;        // 供应商类型编号

    private String supplyTypeName;      // 供应商类型名称

    private String supplyTypeDes;       // 供应商类型描述

    private String companyId;           // 供应商类型所属公司

    private String supplyTypeStatus;   // 供应商类型状态

    private Company company;            // 关联到t_company表

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSupplyTypeId() {
        return supplyTypeId;
    }

    public void setSupplyTypeId(String supplyTypeId) {
        this.supplyTypeId = supplyTypeId;
    }

    public String getSupplyTypeName() {
        return supplyTypeName;
    }

    public void setSupplyTypeName(String supplyTypeName) {
        this.supplyTypeName = supplyTypeName;
    }

    public String getSupplyTypeDes() {
        return supplyTypeDes;
    }

    public void setSupplyTypeDes(String supplyTypeDes) {
        this.supplyTypeDes = supplyTypeDes;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSupplyTypeStatus() {
        return supplyTypeStatus;
    }

    public void setSupplyTypeStatus(String supplyTypeStatus) {
        this.supplyTypeStatus = supplyTypeStatus;
    }
}