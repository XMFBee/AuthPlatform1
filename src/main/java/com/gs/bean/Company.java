package com.gs.bean;

import java.util.Date;

public class Company {
    private String companyId;//公司编号

    private String companyName;//公司名称

    private String companyAddress;//公司地址

    private String companyTel;//公司联系方式

    private String companyPricipal;//公司负责人

    private String companyPricipalphone;//公司负责人电话

    private String companyWebsite;//公司官网URL

    private String companyLogo;//公司logo图片

    private Date companyOpendate;//公司成立时间

    private String companySize;//公司规模

    private Double companyLongitude;//公司经度

    private Double companyLatitude;//公司纬度

    private String companyDes;//公司描述

    public String getCompanyPricipalphone() {
        return companyPricipalphone;
    }

    public void setCompanyPricipalphone(String companyPricipalphone) {
        this.companyPricipalphone = companyPricipalphone;
    }

    private String companyStatus;//公司状态

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyPricipal() {
        return companyPricipal;
    }

    public void setCompanyPricipal(String companyPricipal) {
        this.companyPricipal = companyPricipal;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public Date getCompanyOpendate() {
        return companyOpendate;
    }

    public void setCompanyOpendate(Date companyOpendate) {
        this.companyOpendate = companyOpendate;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public Double getCompanyLongitude() {
        return companyLongitude;
    }

    public void setCompanyLongitude(Double companyLongitude) {
        this.companyLongitude = companyLongitude;
    }

    public Double getCompanyLatitude() {
        return companyLatitude;
    }

    public void setCompanyLatitude(Double companyLatitude) {
        this.companyLatitude = companyLatitude;
    }

    public String getCompanyDes() {
        return companyDes;
    }

    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyTel='" + companyTel + '\'' +
                ", companyPricipal='" + companyPricipal + '\'' +
                ", companyWebsite='" + companyWebsite + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", companyOpendate=" + companyOpendate +
                ", companySize='" + companySize + '\'' +
                ", companyLongitude=" + companyLongitude +
                ", companyLatitude=" + companyLatitude +
                ", companyDes='" + companyDes + '\'' +
                ", companyStatus='" + companyStatus + '\'' +
                '}';
    }
}