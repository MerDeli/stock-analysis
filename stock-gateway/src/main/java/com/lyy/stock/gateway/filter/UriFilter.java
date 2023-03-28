package com.lyy.stock.gateway.filter;

import com.lyy.stock.common.core.constant.ConfigValueConstant;
import com.lyy.stock.common.core.exception.CommonExceptionCode;
import com.lyy.stock.common.core.exception.StockException;
import com.lyy.stock.gateway.constant.OrderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author:
 * @createTime: 2023/03/28 09:48:28
 * @version:
 * @Description:
 */
@Slf4j
@Component
public class UriFilter extends BaseFilter{

    @Value("${" + ConfigValueConstant.SPRING_PROFILES_ACTIVE + "}")
    private String env;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 演示环境部分接口不允许操作
        if(env.equals("prod")){
            gatewayProperties.getNoOptPath().forEach(data -> {
                if(super.getPath(exchange).contains(data)){
                    throw new StockException(CommonExceptionCode.OPT_ERROR);
                }
            });
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return OrderConstant.URI_ORDER;
    }
}
