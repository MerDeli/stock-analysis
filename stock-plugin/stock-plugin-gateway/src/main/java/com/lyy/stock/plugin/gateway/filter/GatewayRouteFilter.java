package com.lyy.stock.plugin.gateway.filter;

import com.lyy.stock.plugin.bgg.context.StrategyContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author:
 * @createTime: 2023/03/22 14:39:53
 * @version:
 * @Description:
 */
public class GatewayRouteFilter implements GlobalFilter, Ordered {

    @Autowired
    private StrategyContextHolder strategyContextHolder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }

    @Override
    public int getOrder() {
        return GatewayOrderedFilter.SET_ROUTE_VERSION_ORDERED_FILTER;
    }
}
