package com.gs.bean;

import java.util.Date;

public class UserRole {
    private String userRoleId;

    private String userId;

    private String roleId;

    private Date urcreatedTime;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getUrcreatedTime() {
        return urcreatedTime;
    }

    public void setUrcreatedTime(Date urcreatedTime) {
        this.urcreatedTime = urcreatedTime;
    }
}