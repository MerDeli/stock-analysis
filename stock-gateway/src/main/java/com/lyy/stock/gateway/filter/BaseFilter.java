package com.lyy.stock.gateway.filter;

import com.lyy.stock.common.token.utils.AuthUtil;
import com.lyy.stock.gateway.properties.StockGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;
import java.util.Set;

/**
 * @Author:
 * @createTime: 2023/03/28 09:18:33
 * @version:
 * @Description:
 */
@Slf4j
@Component
public abstract class BaseFilter implements GlobalFilter, Ordered {

    @Autowired
    protected AuthUtil authUtil;

    @Autowired
    protected StockGatewayProperties gatewayProperties;

    protected final PathMatcher pathMatcher = new AntPathMatcher();

    protected void setHeader(ServerWebExchange exchange, Map<String, String> headers) {
        exchange.getRequest().mutate().headers(httpHeaders -> {
            for (String key : headers.keySet()) {
                String value = headers.get(key);
                if (value != null) {
                    httpHeaders.add(key, value);
                }
            }
        });
    }

    protected boolean checkPath(String requestPath, Set<String> configPathList) {
        if(CollectionUtils.isEmpty(configPathList)){
            return false;
        }
        for (String configPath : configPathList) {
            if (pathMatcher.match(configPath, requestPath)) {
                return true;
            }
        }
        return false;
    }

    protected String getPath(ServerWebExchange exchange) {
        return String.valueOf(exchange.getRequest().getPath());
    }
}
