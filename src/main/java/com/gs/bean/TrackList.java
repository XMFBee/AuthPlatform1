package com.gs.bean;

import java.util.Date;

public class TrackList {
    private String trackId;

    private String userId;

    private String trackContent;

    private Integer serviceEvaluate;

    private String trackUser;

    private Date trackCreatedTime;

    private String companyId;

    private Company company;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    private User user;

    private Checkin checkin;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Checkin getCheckin() {
        return checkin;
    }

    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrackContent() {
        return trackContent;
    }

    public void setTrackContent(String trackContent) {
        this.trackContent = trackContent;
    }

    public Integer getServiceEvaluate() {
        return serviceEvaluate;
    }

    public void setServiceEvaluate(Integer serviceEvaluate) {
        this.serviceEvaluate = serviceEvaluate;
    }

    public String getTrackUser() {
        return trackUser;
    }

    public void setTrackUser(String trackUser) {
        this.trackUser = trackUser;
    }

    public Date getTrackCreatedTime() {
        return trackCreatedTime;
    }

    public void setTrackCreatedTime(Date trackCreatedTime) {
        this.trackCreatedTime = trackCreatedTime;
    }
}