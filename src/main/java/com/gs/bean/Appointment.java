package com.gs.bean;

import java.util.Date;

public class Appointment {
    private String appointmentId;


    private String userId;

    private String userName;

    private String userPhone;

    private String brandId;

    private String colorId;

    private String modelId;

    private String plateId;

    private String carPlate;

    private Date arriveTime;

    private String maintainOrFix;

    private Date appCreatedTime;

    private String companyId;

    private String appoitmentStatus;

    private String currentStatus;

    private CarBrand brand; // 汽车品牌
    private CarColor color; // 汽车颜色
    private CarModel model; // 汽车车型
    private CarPlate plate; // 汽车车牌
    private Company company; // 汽修公司
    private User user;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {this.carPlate = carPlate;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getMaintainOrFix() {
        return maintainOrFix;
    }

    public void setMaintainOrFix(String maintainOrFix) {
        this.maintainOrFix = maintainOrFix;
    }

    public Date getAppCreatedTime() {
        return appCreatedTime;
    }

    public void setAppCreatedTime(Date appCreatedTime) {
        this.appCreatedTime = appCreatedTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAppoitmentStatus() {
        return appoitmentStatus;
    }

    public void setAppoitmentStatus(String appoitmentStatus) {
        this.appoitmentStatus = appoitmentStatus;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public CarPlate getPlate() {
        return plate;
    }

    public void setPlate(CarPlate plate) {
        this.plate = plate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", brandId='" + brandId + '\'' +
                ", colorId='" + colorId + '\'' +
                ", modelId='" + modelId + '\'' +
                ", plateId='" + plateId + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", arriveTime=" + arriveTime +
                ", maintainOrFix='" + maintainOrFix + '\'' +
                ", appCreatedTime=" + appCreatedTime +
                ", companyId='" + companyId + '\'' +
                ", appoitmentStatus='" + appoitmentStatus + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                '}';
    }
}