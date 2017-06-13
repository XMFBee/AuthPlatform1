package com.gs.bean;

import java.util.Date;

/**
 * 登记bean， 张文星
 * */
public class Checkin {
    private String checkinId;//登记编号

    private String userId; // 用户编号

    private String appointmentId;//预约编号

    private String userName; //车主姓名

    private String userPhone; //车主电话

    private String brandId; //汽车品牌编号

    private String colorId; //汽车颜色编号

    private String modelId; //汽车车型编号

    private String plateId; //汽车车牌编号

    private String carPlate; //汽车车牌

    private Date arriveTime; //到店时间

    private Double carMileage; //汽车行驶里程

    private String carThings; //车上物品描述

    private String intactDegrees; //汽车完好度描述

    private String userRequests; //用户要求描述

    private String maintainOrFix; //标识是保养还是维修

    private Date checkinCreatedTime; //登记记录创建的时间

    private String companyId; //汽修公司编号

    private String checkinStatus; //登记记录状态

    private String ifClearCar; // 是否洗车

    private Double nowOil; // 当前油量

    private CarBrand brand; // 汽车品牌
    private CarColor color; // 汽车颜色
    private CarModel model; // 汽车车型
    private CarPlate plate; // 汽车车牌
    private Company company; // 汽修公司
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(String checkinId) {
        this.checkinId = checkinId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Double getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(Double carMileage) {
        this.carMileage = carMileage;
    }

    public String getCarThings() {
        return carThings;
    }

    public void setCarThings(String carThings) {
        this.carThings = carThings;
    }

    public String getIntactDegrees() {
        return intactDegrees;
    }

    public void setIntactDegrees(String intactDegrees) {
        this.intactDegrees = intactDegrees;
    }

    public String getUserRequests() {
        return userRequests;
    }

    public void setUserRequests(String userRequests) {
        this.userRequests = userRequests;
    }

    public String getMaintainOrFix() {
        return maintainOrFix;
    }

    public void setMaintainOrFix(String maintainOrFix) {
        this.maintainOrFix = maintainOrFix;
    }

    public Date getCheckinCreatedTime() {
        return checkinCreatedTime;
    }

    public void setCheckinCreatedTime(Date checkinCreatedTime) {
        this.checkinCreatedTime = checkinCreatedTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(String checkinStatus) {
        this.checkinStatus = checkinStatus;
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

    public String getIfClearCar() {
        return ifClearCar;
    }

    public void setIfClearCar(String ifClearCar) {
        this.ifClearCar = ifClearCar;
    }

    public Double getNowOil() {
        return nowOil;
    }

    public void setNowOil(Double nowOil) {
        this.nowOil = nowOil;
    }

    @Override
    public String toString() {
        return "Checkin{" +
                "checkinId='" + checkinId + '\'' +
                ", userId='" + userId + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", brandId='" + brandId + '\'' +
                ", colorId='" + colorId + '\'' +
                ", modelId='" + modelId + '\'' +
                ", plateId='" + plateId + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", arriveTime=" + arriveTime +
                ", carMileage=" + carMileage +
                ", carThings='" + carThings + '\'' +
                ", intactDegrees='" + intactDegrees + '\'' +
                ", userRequests='" + userRequests + '\'' +
                ", maintainOrFix='" + maintainOrFix + '\'' +
                ", checkinCreatedTime=" + checkinCreatedTime +
                ", companyId='" + companyId + '\'' +
                ", checkinStatus='" + checkinStatus + '\'' +
                '}';
    }
}