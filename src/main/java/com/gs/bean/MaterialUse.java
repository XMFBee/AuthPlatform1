package com.gs.bean;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class MaterialUse {
    private String materialUseId;

    private String matainRecordId;

    private String accId;

    private Integer accCount;

    private Date muCreatedTime;

    private Date muUseDate;

    /**
     * 领料数量
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


    public String getMaterialUseId() {
        return materialUseId;
    }

    public void setMaterialUseId(String materialUseId) {
        this.materialUseId = materialUseId;
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

    public Date getMuCreatedTime() {
        return muCreatedTime;
    }

    public void setMuCreatedTime(Date muCreatedTime) {
        this.muCreatedTime = muCreatedTime;
    }

    public Date getMuUseDate() {
        return muUseDate;
    }

    public void setMuUseDate(Date muUseDate) {
        this.muUseDate = muUseDate;
    }

    /**
     *
     * 关联到其它表的get / set
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