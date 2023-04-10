package com.lyy.stock.ums.mbg.exception;

import com.lyy.stock.common.core.api.ServiceCode;
import lombok.Getter;

/**
 * @Author:
 * @createTime: 2023/04/10 11:01:52
 * @version:
 * @Description:
 */
@Getter
public enum UmsExceptionCode implements ServiceCode {

    USER_NOT_EXIST(0201001,"用户不存在"),
    USERNAME_OR_PASSWORD_ERROR(0201001,"用户名或密码错误"),

    ;


    private int code;
    private String msg;

    UmsExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
