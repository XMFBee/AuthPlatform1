package com.gs.bean;

public class CarColor {
    private String colorId;//颜色编号

    private String colorName;//颜色名称

    private String colorRgb;//颜色的RGB值

    private String colorHex;//颜色的十六进制值

    private String colorDes;//颜色描述

    private String colorStatus;//颜色状态

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(String colorRgb) {
        this.colorRgb = colorRgb;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getColorDes() {
        return colorDes;
    }

    public void setColorDes(String colorDes) {
        this.colorDes = colorDes;
    }

    public String getColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(String colorStatus) {
        this.colorStatus = colorStatus;
    }
}