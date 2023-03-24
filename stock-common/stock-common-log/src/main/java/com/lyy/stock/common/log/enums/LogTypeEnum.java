package com.lyy.stock.common.log.enums;

import lombok.Getter;

public enum LogTypeEnum {

    OPT_LOG(1,"操作日志"),
    LOGIN_LOG(2,"登录日志"),
    ;
    @Getter
    private final int code;
    @Getter
    private final String msg;

    LogTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
