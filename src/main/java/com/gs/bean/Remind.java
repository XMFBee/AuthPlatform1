package com.gs.bean;

/**
 * 提醒表
 * Created by Administrator on 2017/5/25.
 */
public class Remind {
    private String remindId;
    private String remindDes;
    private String remindUser;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Remind{" +
                "remindId='" + remindId + '\'' +
                ", remindDes='" + remindDes + '\'' +
                ", remindUser='" + remindUser + '\'' +
                ", user=" + user +
                '}';
    }

    public String getRemindId() {
        return remindId;
    }

    public void setRemindId(String remindId) {
        this.remindId = remindId;
    }

    public String getRemindDes() {
        return remindDes;
    }

    public void setRemindDes(String remindDes) {
        this.remindDes = remindDes;
    }

    public String getRemindUser() {
        return remindUser;
    }

    public void setRemindUser(String remindUser) {
        this.remindUser = remindUser;
    }

}
