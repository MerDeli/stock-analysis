package com.lyy.stock.common.core.exception;

import com.lyy.stock.common.core.api.ServiceCode;

public enum CommonExceptionCode implements ServiceCode {

    SYSTEM_500(500, "未知异常，请联系管理员"),
    SYSTEM_501(501, "入参错误或入参不全"),
    SYSTEM_502(502, "服务调用异常"),
    SYSTEM_503(503, "服务调用超时"),

    SYSTEM_UNAUTHORIZED(401, "接口未授权，请联系管理员"),
    SYSTEM_404(404, "路径不存在，请检查路径是否正确"),

    SENTINEL_FLOW(429, "操作过于频繁稍后重试 [Sentinel]"),
    SENTINEL_DEGRADE_FLOW(430, "服务降级，请稍后重试 [Sentinel]"),
    SENTINEL_PARAM_FLOW(431, "参数访问过于频繁，请稍后重试 [Sentinel]"),
    SENTINEL_SYSTEM_BLOCK(432, "系统异常，繁稍后重试 [Sentinel]"),
    SENTINEL_AUTHORITY(433, "服务授权异常，稍后重试 [Sentinel]"),


    JWT_TOKEN_EXPIRED(600, "token已失效，请重新登录"),
    JWT_SIGNATURE(601, "token签名错误，请重新登录"),
    JWT_ILLEGAL_ARGUMENT(602, "token为空，请重新登录"),
    JWT_PARSER_TOKEN_FAIL(603, "token解析失败，请重新登录"),

    TENANT_AUTH_NULL(701, "租户未开通此端权限"),
    OPT_ERROR(702, "演示环境不可操作"),



    HTTP_REQUEST_ERROR(5000,"请求方式错误"),
    ;
    private int code;
    private String msg;

    CommonExceptionCode(int code, String msg) {
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
