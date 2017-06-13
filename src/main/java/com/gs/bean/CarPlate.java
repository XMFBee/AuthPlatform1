package com.gs.bean;

public class CarPlate {
    private String plateId;//车牌编号

    private String plateName;//车牌名称

    private String plateDes;//车牌描述

    private String plateStatus;//车牌状态

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getPlateDes() {
        return plateDes;
    }

    public void setPlateDes(String plateDes) {
        this.plateDes = plateDes;
    }

    public String getPlateStatus() {
        return plateStatus;
    }

    public void setPlateStatus(String plateStatus) {
        this.plateStatus = plateStatus;
    }
}