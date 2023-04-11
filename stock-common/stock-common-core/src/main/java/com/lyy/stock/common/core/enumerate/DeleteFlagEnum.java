package com.lyy.stock.common.core.enumerate;

public enum DeleteFlagEnum {
    NOT_DELETE(0,"未删除"),
    DELETED(1,"已删除");

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
