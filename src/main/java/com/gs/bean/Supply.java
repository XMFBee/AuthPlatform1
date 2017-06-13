package com.gs.bean;

import java.util.Date;
/*
供应商管理
 */
public class Supply {
    private String supplyId;              // 供应商编号

    private String supplyName;            // 供应商名称

    private String supplyTel;             // 供应商联系电话

    private String supplyPricipal;       // 供应商负责人

    private String supplyAddress;        // 供应商地址

    private String supplyBank;           // 供应商开户银行全称

    private String supplyBankAccount;   // 供应商开户人姓名

    private String supplyBankNo;         // 供应商开户卡号

    private String supplyAlipay;         // 供应商支付宝

    private String supplyWeChat;         // 供应商微信

    private Date supplyCreatedTime;     // 供应商创建时间

    private String supplyTypeId;        // 供应商类型

    private String companyId;           // 供应商所属公司

    private String supplyStatus;        // 供应商状态

    private SupplyType supplyType;      // 关联到供应商类型表
    private Company company;            // 关联到t_company表

    public SupplyType getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(SupplyType supplyType) {
        this.supplyType = supplyType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getSupplyTel() {
        return supplyTel;
    }

    public void setSupplyTel(String supplyTel) {
        this.supplyTel = supplyTel;
    }

    public String getSupplyPricipal() {
        return supplyPricipal;
    }

    public void setSupplyPricipal(String supplyPricipal) {
        this.supplyPricipal = supplyPricipal;
    }

    public String getSupplyAddress() {
        return supplyAddress;
    }

    public void setSupplyAddress(String supplyAddress) {
        this.supplyAddress = supplyAddress;
    }

    public String getSupplyBank() {
        return supplyBank;
    }

    public void setSupplyBank(String supplyBank) {
        this.supplyBank = supplyBank;
    }

    public String getSupplyBankAccount() {
        return supplyBankAccount;
    }

    public void setSupplyBankAccount(String supplyBankAccount) {
        this.supplyBankAccount = supplyBankAccount;
    }

    public String getSupplyBankNo() {
        return supplyBankNo;
    }

    public void setSupplyBankNo(String supplyBankNo) {
        this.supplyBankNo = supplyBankNo;
    }

    public String getSupplyAlipay() {
        return supplyAlipay;
    }

    public void setSupplyAlipay(String supplyAlipay) {
        this.supplyAlipay = supplyAlipay;
    }

    public String getSupplyWeChat() {
        return supplyWeChat;
    }

    public void setSupplyWeChat(String supplyWeChat) {
        this.supplyWeChat = supplyWeChat;
    }

    public Date getSupplyCreatedTime() {
        return supplyCreatedTime;
    }

    public void setSupplyCreatedTime(Date supplyCreatedTime) {
        this.supplyCreatedTime = supplyCreatedTime;
    }

    public String getSupplyTypeId() {
        return supplyTypeId;
    }

    public void setSupplyTypeId(String supplyTypeId) {
        this.supplyTypeId = supplyTypeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSupplyStatus() {
        return supplyStatus;
    }

    public void setSupplyStatus(String supplyStatus) {
        this.supplyStatus = supplyStatus;
    }
}