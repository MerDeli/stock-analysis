package com.lyy.stock.sms.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:
 * @createTime: 2023/04/26 17:10:49
 * @version:
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun")
@Setter
@Getter
public class AliyunSendNoteConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint="dysmsapi.aliyuncs.com";
    private String region="cn-hangzhou";

    private String signName;
    private String templateCode;
}
