package com.lyy.stock.plugin.bgg.config;

import com.lyy.stock.plugin.bgg.loadbalancer.DefaultLoadBalancer;
import com.lyy.stock.plugin.common.constant.StockConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Author:
 * @createTime: 2023/03/23 18:06:23
 * @version:
 * @Description:
 */
@Configuration
@ConditionalOnProperty(value = StockConstant.STOCK_BGG_ENABLED, matchIfMissing = true)
@LoadBalancerClients(defaultConfiguration = {LoadBalancerConfig.class})
public class LoadBalancerConfig {

    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new DefaultLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}
