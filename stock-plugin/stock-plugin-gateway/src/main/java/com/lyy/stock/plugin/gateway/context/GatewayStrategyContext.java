package com.lyy.stock.plugin.gateway.context;

import org.springframework.web.server.ServerWebExchange;

/**
 * @Author:
 * @createTime: 2023/03/22 15:34:55
 * @version:
 * @Description:
 */
public class GatewayStrategyContext {

    private static final ThreadLocal<GatewayStrategyContext> THREAD_LOCAL = ThreadLocal.withInitial(GatewayStrategyContext::new);

    private ServerWebExchange exchange;

    public static GatewayStrategyContext getCurrentContext() {
        return THREAD_LOCAL.get();
    }

    public static void clearCurrentContext() {
        THREAD_LOCAL.remove();
    }

    public ServerWebExchange getExchange() {
        return exchange;
    }

    public void setExchange(ServerWebExchange exchange) {
        this.exchange = exchange;
    }
}
