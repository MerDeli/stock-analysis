package com.lyy.stock.ums.mbg.entity.enumerate.user;

public enum UserStatusEnum {

    BAN(0,"禁用"),
    ENABLE(1,"启用"),
    ;

    private int code;
    private String msg;

    UserStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
