package com.gs.bean;

import java.util.Date;

public class MaterialReturn {
    private String materialReturnId;

    private String matainRecordId;

    private String accId;

    private Integer accCount;

    private Date mrCreatedDate;

    private Date mrReturnDate;


    /**
     * 库存退料数量
     */
    private int count;

    /**
     * 库存可用数量
     */
    private int accIdle;

    public int getAccIdle() {
        return accIdle;
    }

    public void setAccIdle(int accIdle) {
        this.accIdle = accIdle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     *  关联其它表的其它实例
     */
    private MaintainRecord record;

    private Accessories accessories;


    public String getMaterialReturnId() {
        return materialReturnId;
    }

    public void setMaterialReturnId(String materialReturnId) {
        this.materialReturnId = materialReturnId;
    }

    public String getMatainRecordId() {
        return matainRecordId;
    }

    public void setMatainRecordId(String matainRecordId) {
        this.matainRecordId = matainRecordId;
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

    public Date getMrCreatedDate() {
        return mrCreatedDate;
    }

    public void setMrCreatedDate(Date mrCreatedDate) {
        this.mrCreatedDate = mrCreatedDate;
    }

    public Date getMrReturnDate() {
        return mrReturnDate;
    }

    public void setMrReturnDate(Date mrReturnDate) {
        this.mrReturnDate = mrReturnDate;
    }

    /**
     *  关联其它表的其它get / set
     */
    public MaintainRecord getRecord() {
        return record;
    }

    public void setRecord(MaintainRecord record) {
        this.record = record;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }
}