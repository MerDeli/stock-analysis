package com.lyy.stock.common.core.enumerate;

public enum DeleteFlagEnum {
    DELETED(0,"已删除"),
    NOT_DELETE(1,"未删除");

    private int code;
    private String msg;

    DeleteFlagEnum(int code, String msg) {
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
