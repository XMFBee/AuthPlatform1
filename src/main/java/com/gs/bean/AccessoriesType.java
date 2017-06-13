package com.gs.bean;

public class AccessoriesType {
    private String accTypeId;

    private String accTypeName;

    private String accTypeDes;

    private String companyId;

    private String accTypeStatus;

    private Company company;

    public String getAccTypeId() {
        return accTypeId;
    }

    public void setAccTypeId(String accTypeId) {
        this.accTypeId = accTypeId;
    }

    public String getAccTypeName() {
        return accTypeName;
    }

    public void setAccTypeName(String accTypeName) {
        this.accTypeName = accTypeName;
    }

    public String getAccTypeDes() {
        return accTypeDes;
    }

    public void setAccTypeDes(String accTypeDes) {
        this.accTypeDes = accTypeDes;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccTypeStatus() {
        return accTypeStatus;
    }

    public void setAccTypeStatus(String accTypeStatus) {
        this.accTypeStatus = accTypeStatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "AccessoriesType{" +
                "accTypeId='" + accTypeId + '\'' +
                ", accTypeName='" + accTypeName + '\'' +
                ", accTypeDes='" + accTypeDes + '\'' +
                ", companyId='" + companyId + '\'' +
                ", accTypeStatus='" + accTypeStatus + '\'' +
                ", company=" + company +
                '}';
    }
}