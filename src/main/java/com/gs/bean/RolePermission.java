package com.gs.bean;

import java.util.Date;

public class RolePermission {
    private String rpId;

    private String roleId;

    private String permissionId;

    private Date rpcreatedTime;

    public String getRpId() {
        return rpId;
    }

    public void setRpId(String rpId) {
        this.rpId = rpId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public Date getRpcreatedTime() {
        return rpcreatedTime;
    }

    public void setRpcreatedTime(Date rpcreatedTime) {
        this.rpcreatedTime = rpcreatedTime;
    }
}