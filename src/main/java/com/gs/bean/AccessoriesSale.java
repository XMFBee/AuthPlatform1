package com.gs.bean;

import java.util.Date;

public class AccessoriesSale {
    private String accSaleId;

    private String accId;

    private Date accSaledTime;

    private Integer accSaleCount;

    private Double accSalePrice;

    private Double accSaleTotal;

    private Double accSaleDiscount;

    private Double accSaleMoney;

    private Date accSaleCreatedTime;

    private String companyId;

    private String accSaleStatus;

    private Company company;

    private Accessories accessories;

    public String getAccSaleId() {
        return accSaleId;
    }

    public void setAccSaleId(String accSaleId) {
        this.accSaleId = accSaleId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public Date getAccSaledTime() {
        return accSaledTime;
    }

    public void setAccSaledTime(Date accSaledTime) {
        this.accSaledTime = accSaledTime;
    }

    public Integer getAccSaleCount() {
        return accSaleCount;
    }

    public void setAccSaleCount(Integer accSaleCount) {
        this.accSaleCount = accSaleCount;
    }

    public Double getAccSalePrice() {
        return accSalePrice;
    }

    public void setAccSalePrice(Double accSalePrice) {
        this.accSalePrice = accSalePrice;
    }

    public Double getAccSaleTotal() {
        return accSaleTotal;
    }

    public void setAccSaleTotal(Double accSaleTotal) {
        this.accSaleTotal = accSaleTotal;
    }

    public Double getAccSaleDiscount() {
        return accSaleDiscount;
    }

    public void setAccSaleDiscount(Double accSaleDiscount) {
        this.accSaleDiscount = accSaleDiscount;
    }

    public Double getAccSaleMoney() {
        return accSaleMoney;
    }

    public void setAccSaleMoney(Double accSaleMoney) {
        this.accSaleMoney = accSaleMoney;
    }

    public Date getAccSaleCreatedTime() {
        return accSaleCreatedTime;
    }

    public void setAccSaleCreatedTime(Date accSaleCreatedTime) {
        this.accSaleCreatedTime = accSaleCreatedTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccSaleStatus() {
        return accSaleStatus;
    }

    public void setAccSaleStatus(String accSaleStatus) {
        this.accSaleStatus = accSaleStatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "AccessoriesSale{" +
                "accSaleId='" + accSaleId + '\'' +
                ", accId='" + accId + '\'' +
                ", accSaledTime=" + accSaledTime +
                ", accSaleCount=" + accSaleCount +
                ", accSalePrice=" + accSalePrice +
                ", accSaleTotal=" + accSaleTotal +
                ", accSaleDiscount=" + accSaleDiscount +
                ", accSaleMoney=" + accSaleMoney +
                ", accSaleCreatedTime=" + accSaleCreatedTime +
                ", companyId='" + companyId + '\'' +
                ", accSaleStatus='" + accSaleStatus + '\'' +
                ", company=" + company +
                ", accessories=" + accessories +
                '}';
    }
}