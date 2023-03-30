package com.lyy.stock.plugin.sentinel.config;

import com.lyy.stock.plugin.nacos.processor.NacosListenerProcessor;
import com.lyy.stock.plugin.sentinel.processor.SentinelRuleNacosProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:
 * @createTime: 2023/03/29 15:39:13
 * @version:
 * @Description:
 */
@Configuration
public class SentinelAutoConfig {
    @ConditionalOnClass(NacosListenerProcessor.class)
    protected static class SentinelSRuleNacosConfiguration {
        @Bean
        public NacosListenerProcessor sentinelStrategyFlowRuleNacosProcessor() {
            return new SentinelRuleNacosProcessor();
        }
    }
}
