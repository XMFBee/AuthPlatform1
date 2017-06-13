package com.gs.common.bean;

/**
 * Created by WangGenshen on 5/18/16.
 */
public class ControllerResult {

    public static final int SUCCESS_CODE = 100;
    public static final int FAIL_CODE = 101;
    public static final int NOT_LOGIN_CODE = 102;
    public static final int NOT_ROLE_CODE = 103;
    public static final int IS_OWNER_CODE = 104;
    public static final int NOT_PHONE_CODE = 105;
    public static final String SUCCESS_RESULT = "success";
    public static final String FAIL_RESULT = "fail";
    public static final String NOT_LOGIN_RESULT = "notLogin";
    public static final String NOT_ROLE_RESULT = "notRole";
    public static final String IS_OWNER_RESULT = "isOwner";
    public static final String NOT_PHONE_RESULT = "notPhoneCode";

    private int code;
    private String result;
    private String message;

    public ControllerResult() {

    }

    public ControllerResult(int code, String result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ControllerResult{" +
                "code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public static ControllerResult getSuccessResult(String message) {
        return new ControllerResult(ControllerResult.SUCCESS_CODE, ControllerResult.SUCCESS_RESULT, message);
    }

    public static ControllerResult getFailResult(String message) {
        return new ControllerResult(ControllerResult.FAIL_CODE, ControllerResult.FAIL_RESULT, message);
    }

    public static ControllerResult getNotLoginResult(String message) {
        return new ControllerResult(ControllerResult.NOT_LOGIN_CODE, ControllerResult.NOT_LOGIN_RESULT, message);
    }

    public static ControllerResult getNotRoleResult(String message) {
        return new ControllerResult(ControllerResult.NOT_ROLE_CODE, ControllerResult.NOT_ROLE_RESULT, message);
    }

    public static ControllerResult getIsOwnerResult(String message){
        return new ControllerResult(ControllerResult.IS_OWNER_CODE, ControllerResult.IS_OWNER_RESULT, message);
    }

    public static ControllerResult getNotPhoneCodeResult(String message){
        return new ControllerResult(ControllerResult.NOT_PHONE_CODE, ControllerResult.NOT_PHONE_RESULT, message);
    }
}
