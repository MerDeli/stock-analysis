package com.lyy.stock.gateway.filter;

import cn.hutool.core.util.IdUtil;
import com.lyy.stock.common.core.context.HeaderCode;
import com.lyy.stock.gateway.constant.OrderConstant;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author:
 * @createTime: 2023/03/28 09:55:42
 * @version:
 * @Description:
 */
@Component
public class TraceFilter extends BaseFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uuid = IdUtil.simpleUUID();
        exchange.getRequest().mutate().header(HeaderCode.TRACE_ID, uuid);
        MDC.put(HeaderCode.TRACE_ID, uuid);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return OrderConstant.TRACE_ORDER;
    }
}
