package com.lyy.stock.common.log.enums;

import lombok.Getter;

public enum OptBehaviorEnum {

    NOT(0,"未知"),
    ADD(10,"新增"),
    UPDATE(20,"修改"),
    SELECT(30,"查询"),
    DELETE(40,"删除"),
    LOGIN(100,"登录"),
    LOGOUT(101,"登出"),
    ;
    @Getter
    private final int code;
    @Getter
    private final String msg;

    OptBehaviorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
