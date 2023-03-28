package com.lyy.stock.gateway.enums;

import com.lyy.stock.common.core.api.ServiceCode;

/**
 * @Author:
 * @createTime: 2023/03/28 10:04:40
 * @version:
 * @Description:
 */
public enum GatewayExceptionCode implements ServiceCode {

    TOKEN_INVALID(5020,"token失效"),
    TOKEN_EMPTY(5021,"token不能为空"),


    ;
    private int code;
    private String msg;

    GatewayExceptionCode(int code, String msg) {
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

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
