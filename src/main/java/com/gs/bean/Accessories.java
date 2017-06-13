package com.gs.bean;

import java.util.Date;

public class Accessories {
    private String accId;

    private String accName;

    private String accCommodityCode;

    private String accDes;

    private Double accPrice;

    private Double accSalePrice;

    private String accUnit;

    private Integer accTotal;

    private Integer accIdle;

    private Date accUsedTime;

    private Date accBuyedTime;

    private String supplyId;

    private Date accCreatedTime;

    private String accTypeId;

    private String companyId;

    private String accStatus;

    /**
     * 库存记录数
     */
    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private Company company;

    private AccessoriesType accessoriesType;

    private Supply supply;

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccCommodityCode() {
        return accCommodityCode;
    }

    public void setAccCommodityCode(String accCommodityCode) {
        this.accCommodityCode = accCommodityCode;
    }

    public String getAccDes() {
        return accDes;
    }

    public void setAccDes(String accDes) {
        this.accDes = accDes;
    }

    public Double getAccPrice() {
        return accPrice;
    }

    public void setAccPrice(Double accPrice) {
        this.accPrice = accPrice;
    }

    public Double getAccSalePrice() {
        return accSalePrice;
    }

    public void setAccSalePrice(Double accSalePrice) {
        this.accSalePrice = accSalePrice;
    }

    public String getAccUnit() {
        return accUnit;
    }

    public void setAccUnit(String accUnit) {
        this.accUnit = accUnit;
    }

    public Integer getAccTotal() {
        return accTotal;
    }

    public void setAccTotal(Integer accTotal) {
        this.accTotal = accTotal;
    }

    public Integer getAccIdle() {
        return accIdle;
    }

    public void setAccIdle(Integer accIdle) {
        this.accIdle = accIdle;
    }

    public Date getAccUsedTime() {
        return accUsedTime;
    }

    public void setAccUsedTime(Date accUsedTime) {
        this.accUsedTime = accUsedTime;
    }

    public Date getAccBuyedTime() {
        return accBuyedTime;
    }

    public void setAccBuyedTime(Date accBuyedTime) {
        this.accBuyedTime = accBuyedTime;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public Date getAccCreatedTime() {
        return accCreatedTime;
    }

    public void setAccCreatedTime(Date accCreatedTime) {
        this.accCreatedTime = accCreatedTime;
    }

    public String getAccTypeId() {
        return accTypeId;
    }

    public void setAccTypeId(String accTypeId) {
        this.accTypeId = accTypeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AccessoriesType getAccessoriesType() {
        return accessoriesType;
    }

    public void setAccessoriesType(AccessoriesType accessoriesType) {
        this.accessoriesType = accessoriesType;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "accId='" + accId + '\'' +
                ", accName='" + accName + '\'' +
                ", accCommodityCode='" + accCommodityCode + '\'' +
                ", accDes='" + accDes + '\'' +
                ", accPrice=" + accPrice +
                ", accSalePrice=" + accSalePrice +
                ", accUnit='" + accUnit + '\'' +
                ", accTotal=" + accTotal +
                ", accIdle=" + accIdle +
                ", accUsedTime=" + accUsedTime +
                ", accBuyedTime=" + accBuyedTime +
                ", supplyId='" + supplyId + '\'' +
                ", accCreatedTime=" + accCreatedTime +
                ", accTypeId='" + accTypeId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", accStatus='" + accStatus + '\'' +
                ", company=" + company +
                ", accessoriesType=" + accessoriesType +
                ", supply=" + supply +
                '}';
    }


}