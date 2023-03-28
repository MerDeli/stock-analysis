package com.lyy.stock.common.token.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:
 * @createTime: 2023/03/28 09:38:27
 * @version:
 * @Description:
 */
@Data
public class JwtUserInfo implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long identityId;

    private Long accountId;

    private String username;

    private String tenantCode;

    private Long deptId;

    private String deptCode;

    private Integer dataScope;

    private Integer identityType;
}
