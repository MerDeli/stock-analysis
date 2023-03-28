package com.lyy.stock.common.token.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author:
 * @createTime: 2023/03/28 09:37:45
 * @version:
 * @Description:
 */
@Data
public class AuthUserInfo implements Serializable {

    private static final long serialVersionUID = -1L;
    // 对应登录身份的id
    private Long identityId;
    // 令牌
    private String token;
    // 刷新令牌
    private String refreshToken;
    // 过期时间（秒）
    private long expire;
    // 到期时间
    private LocalDateTime expiration;
}
