package com.gs.bean;

/**
 * 维修保养项目
 */
public class MaintainFixAcc {
    private String mainAccId;//保养项目配件编号

    private String maintainId;//保养项目编号 来源于t_maintain_fix表

    private String accId;//配件编号 来源于t_accessories表

    private Integer accCount;//配件个数

    private Company company;//关联Company表

    private Accessories accessories;//关联Accessories

    private MaintainFix maintainFix;//maintainFix

    public MaintainFix getMaintainFix() {
        return maintainFix;
    }

    public void setMaintainFix(MaintainFix maintainFix) {
        this.maintainFix = maintainFix;
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

    public String getMainAccId() {
        return mainAccId;
    }

    public void setMainAccId(String mainAccId) {
        this.mainAccId = mainAccId;
    }

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public Integer getAccCount() {
        return accCount;
    }

    public void setAccCount(Integer accCount) {
        this.accCount = accCount;
    }

    @Override
    public String toString() {
        return "MaintainFixAcc{" +
                "mainAccId='" + mainAccId + '\'' +
                ", maintainId='" + maintainId + '\'' +
                ", accId='" + accId + '\'' +
                ", accCount=" + accCount +
                '}';
    }
}