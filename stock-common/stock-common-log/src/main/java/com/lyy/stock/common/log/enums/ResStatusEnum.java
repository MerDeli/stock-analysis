package com.lyy.stock.common.log.enums;

import lombok.Getter;

public enum ResStatusEnum {

    SUCCESS(1,"成功"),
    ERROR(2,"失败"),
    ;
    @Getter
    private final int code;
    @Getter
    private final String msg;

    ResStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
