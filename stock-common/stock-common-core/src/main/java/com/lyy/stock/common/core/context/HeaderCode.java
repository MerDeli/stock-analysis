package com.lyy.stock.common.core.context;

/**
 * @Description 请求头code常量
 * @anthor hfbin
 * @date 2019/9/22
 */
public interface HeaderCode {
    /**
     * 链路id
     */
    String TRACE_ID = "Trace-Id";
    /**
     * token
     */
    String TOKEN = "Access-Token";
    /**
     * 公司CODE
     */
    String TENANT_CODE = "Tenant-Code";
    /**
     * 客户端code
     */
    String CLIENT_CODE = "Client-Code";
    /**
     * 部门ID
     */
    String DEPT_ID = "Dept-Id";
    /**
     *  部门code
     */
    String DEPT_CODE = "Dept-Code";
    /**
     * 用户身份ID
     */
    String IDENTITY_ID = "Identity-Id";
    /**
     * 账号
     */
    String USERNAME = "Username";
    /**
     * 账号ID
     */
    String ACCOUNT_ID = "Account-Id";
    /**
     * 身份来源
     */
    String IDENTITY_TYPE = "Identity-Type";
    /**
     * 公司数据范围
     */
    String DATA_SCOPE = "Libra-Data-Scope";
    /**
     * ip
     */
    String IP = "Ip";
    /**
     * 浏览器信息
     */
    String USER_AGENT = "User-Agent";
    /**
     * Feign 调用标识
     */
    String FEIGN = "Feign";

}
