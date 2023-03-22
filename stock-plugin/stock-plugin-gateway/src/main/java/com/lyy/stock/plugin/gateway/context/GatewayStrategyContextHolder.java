package com.lyy.stock.plugin.gateway.context;

import com.lyy.stock.plugin.bgg.context.AbstractStrategyContextHolder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author:
 * @createTime: 2023/03/22 15:34:15
 * @version:
 * @Description:
 */
public class GatewayStrategyContextHolder extends AbstractStrategyContextHolder {
    @Override
    public String getHeader(String name) {
        ServerHttpRequest request = getServerHttpRequest();
        if (request == null) {
            return null;
        }
        return request.getHeaders().getFirst(name);
    }

    public ServerHttpRequest getServerHttpRequest() {
        ServerWebExchange exchange = getExchange();
        if (exchange == null) {
            return null;
        }
        return exchange.getRequest();
    }

    public ServerWebExchange getExchange() {
        return GatewayStrategyContext.getCurrentContext().getExchange();
    }
}
