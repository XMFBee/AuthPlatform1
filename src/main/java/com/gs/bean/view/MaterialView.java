package com.gs.bean.view;

import com.gs.bean.*;

/**
 * 员工领料视图
 *
 * @author Administrator
 * @create 2017-04-24 14:36
 */
public class MaterialView {
    private String carPlate;
    private String recordId;
    private String accId;
    private String accName;
    private int accTotal;
    private int needAccCount;
    private int useAccCount;
    private int returnAccCount;

    private User user;

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public int getAccTotal() {
        return accTotal;
    }

    public void setAccTotal(int accTotal) {
        this.accTotal = accTotal;
    }

    public int getNeedAccCount() {
        return needAccCount;
    }

    public void setNeedAccCount(int needAccCount) {
        this.needAccCount = needAccCount;
    }

    public int getUseAccCount() {
        return useAccCount;
    }

    public void setUseAccCount(int useAccCount) {
        this.useAccCount = useAccCount;
    }

    public int getReturnAccCount() {
        return returnAccCount;
    }

    public void setReturnAccCount(int returnAccCount) {
        this.returnAccCount = returnAccCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
