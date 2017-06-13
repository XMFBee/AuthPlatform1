package com.gs.common.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-22.
 */
public class SWFIconResult implements Serializable {
    private Boolean success;
    private String userid;
    private String username;

    private String msg;
    private String sourceUrl;
    private ArrayList avatarUrls;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public ArrayList getAvatarUrls() {
        return avatarUrls;
    }

    public void setAvatarUrls(ArrayList avatarUrls) {
        this.avatarUrls = avatarUrls;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
