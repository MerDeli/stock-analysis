package com.lyy.stock.plugin.gateway.config;

import com.lyy.stock.plugin.common.constant.StockConstant;
import com.lyy.stock.plugin.gateway.context.GatewayStrategyContextHolder;
import com.lyy.stock.plugin.gateway.filter.GatewayRouteFilter;
import com.lyy.stock.plugin.gateway.processor.GatewayRouteNacosProcessor;
import com.lyy.stock.plugin.gateway.route.GatewayRoute;
import com.lyy.stock.plugin.gateway.route.GatewayRouteImpl;
import com.lyy.stock.plugin.nacos.processor.NacosListenerProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:
 * @createTime: 2023/03/22 14:13:35
 * @version:
 * @Description:
 */
@Configuration
public class GatewayAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = StockConstant.STOCK_BGG_ENABLED, matchIfMissing = true)
    public GatewayRouteFilter gatewayStrategyRouteFilter() {
        return new GatewayRouteFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public GatewayStrategyContextHolder gatewayStrategyContextHolder() {
        return new GatewayStrategyContextHolder();
    }

    @ConditionalOnClass(NacosListenerProcessor.class)
    @ConditionalOnProperty(value = StockConstant.STOCK_GATEWAY_ROUTE_ENABLED, matchIfMissing = true)
    protected static class GatewayRouteNacosConfiguration {
        @Bean
        public NacosListenerProcessor gatewayRouteNacosProcessor() {
            return new GatewayRouteNacosProcessor();
        }
    }

    @Bean
    public GatewayRoute gatewayRoute() {
        return new GatewayRouteImpl();
    }
}
