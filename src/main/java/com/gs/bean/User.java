package com.gs.bean;

import java.util.Date;

public class User {
    private String userId;

    private String userEmail;

    private String userPhone;

    private String userPwd;

    private String userNickname;

    private String userIdentity;

    private String userName;

    private String userGender;

    private Date userBirthday;

    private String userAddress;

    private String qqOpenId;

    private String weiboOpenId;

    private String weChatOpenId;

    private String userIcon;

    private String userDes;

    private String companyId;

    private Double userSalary;

    private Date userCreatedTime;

    private Date userLoginedTime;

    private String userStatus;

    private String roleId;

    private int phonecode; // 短信验证码;

    private Company company;    // 汽修公司
    private Role role;        // 角色
    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public int getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(int phonecode) {
        this.phonecode = phonecode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getWeiboOpenId() {
        return weiboOpenId;
    }

    public void setWeiboOpenId(String weiboOpenId) {
        this.weiboOpenId = weiboOpenId;
    }

    public String getWeChatOpenId() {
        return weChatOpenId;
    }

    public void setWeChatOpenId(String weChatOpenId) {
        this.weChatOpenId = weChatOpenId;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserDes() {
        return userDes;
    }

    public void setUserDes(String userDes) {
        this.userDes = userDes;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Double getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(Double userSalary) {
        this.userSalary = userSalary;
    }

    public Date getUserCreatedTime() {
        return userCreatedTime;
    }

    public void setUserCreatedTime(Date userCreatedTime) {
        this.userCreatedTime = userCreatedTime;
    }

    public Date getUserLoginedTime() {
        return userLoginedTime;
    }

    public void setUserLoginedTime(Date userLoginedTime) {
        this.userLoginedTime = userLoginedTime;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userBirthday=" + userBirthday +
                ", userAddress='" + userAddress + '\'' +
                ", qqOpenId='" + qqOpenId + '\'' +
                ", weiboOpenId='" + weiboOpenId + '\'' +
                ", weChatOpenId='" + weChatOpenId + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userDes='" + userDes + '\'' +
                ", companyId='" + companyId + '\'' +
                ", userSalary=" + userSalary +
                ", userCreatedTime=" + userCreatedTime +
                ", userLoginedTime=" + userLoginedTime +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}