package com.gs.bean;

import java.util.Date;
import java.util.Map;

/**
 *  @Autho 程燕
 *  @Des 物料清单表
 */
public class MaterialList {
    private String materialId;

    private String maintainRecordId;

    private String accId;

    private Integer materialCount;

    private Date materialCreatedTime;

    private String materialStatus;

    private Map other;

    /**
     *  关联其它表的其它实例
     */
    private MaintainRecord record;

    private Accessories accessories;

    private MaterialUse materialUse;

    private MaterialReturn materialReturn;


    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaintainRecordId() {
        return maintainRecordId;
    }

    public void setMaintainRecordId(String maintainRecordId) {
        this.maintainRecordId = maintainRecordId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public Integer getMaterialCount() {
        return materialCount;
    }

    public void setMaterialCount(Integer materialCount) {
        this.materialCount = materialCount;
    }

    public Date getMaterialCreatedTime() {
        return materialCreatedTime;
    }

    public void setMaterialCreatedTime(Date materialCreatedTime) {
        this.materialCreatedTime = materialCreatedTime;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    @Override
    public String toString() {
        return "MaterialList{" +
                "materialId='" + materialId + '\'' +
                ", maintainRecordId='" + maintainRecordId + '\'' +
                ", accId='" + accId + '\'' +
                ", materialCount=" + materialCount +
                ", materialCreatedTime=" + materialCreatedTime +
                ", materialStatus='" + materialStatus + '\'' +
                '}';
    }

    /**
     *  关联到其它的表的get / set
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

    public MaterialUse getMaterialUse() {
        return materialUse;
    }

    public void setMaterialUse(MaterialUse materialUse) {
        this.materialUse = materialUse;
    }

    public MaterialReturn getMaterialReturn() {
        return materialReturn;
    }

    public void setMaterialReturn(MaterialReturn materialReturn) {
        this.materialReturn = materialReturn;
    }

    public Map getOther() {
        return other;
    }

    public void setOther(Map other) {
        this.other = other;
    }

}