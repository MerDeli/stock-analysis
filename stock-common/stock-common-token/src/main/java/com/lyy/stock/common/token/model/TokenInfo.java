package com.lyy.stock.common.token.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author:
 * @createTime: 2023/03/28 09:39:57
 * @version:
 * @Description:
 */
@Data
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * token
     */
    private String token;
    /**
     * 有效时间：单位：秒
     */
    private Long expire;
    /**
     * 到期时间
     */
    private LocalDateTime expiration;
}
