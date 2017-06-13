package com.gs.bean;

public class CarModel {
    private String modelId;//车型编号

    private String modelName;//车型名称

    private String modelDes;//车型描述

    private String brandId;//车型品牌

    private String modelStaus;//车型状态

    private CarBrand carBrand;

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDes() {
        return modelDes;
    }

    public void setModelDes(String modelDes) {
        this.modelDes = modelDes;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getModelStaus() {
        return modelStaus;
    }

    public void setModelStaus(String modelStaus) {
        this.modelStaus = modelStaus;
    }
}