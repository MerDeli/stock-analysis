package com.lyy.stock.common.log.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author:
 * @createTime: 2023/03/24 17:36:02
 * @version:
 * @Description:
 */
@Data
public class OptLogDto {

    private String username;
    private String ip;
    private String location;
    private String clientCode;
    private String reqUrl;
    private String userAgent;
    private String httpMethod;
    private String reqParam;
    private String resInfo;
    private Integer resStatus;
    private String description;
    private String tenantCode;
    private Long createBy;
    private LocalDateTime createTime;
    private Integer optType;
    private Integer optBehavior;
}
