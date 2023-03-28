package com.lyy.stock.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @Author:
 * @createTime: 2023/03/28 09:52:17
 * @version:
 * @Description:
 */
@Data
@ConfigurationProperties("stock.gateway")
@Configuration
public class StockGatewayProperties {

    /**
     *  不需要token认证路径
     */
    private Set<String> noIdentityPath;

    /**
     * 生产不可以操作接口
     */
    private Set<String> noOptPath;
}
