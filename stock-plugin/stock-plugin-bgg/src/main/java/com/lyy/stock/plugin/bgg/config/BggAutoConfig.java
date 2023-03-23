package com.lyy.stock.plugin.bgg.config;

import com.lyy.stock.plugin.bgg.adapter.BggPluginAdapter;
import com.lyy.stock.plugin.bgg.adapter.BggPluginAdapterImpl;
import com.lyy.stock.plugin.bgg.cache.RuleCache;
import com.lyy.stock.plugin.bgg.initializer.ConfigInitializer;
import com.lyy.stock.plugin.bgg.loader.LocalConfigLoader;
import com.lyy.stock.plugin.bgg.processor.BggRouteNacosProcessor;
import com.lyy.stock.plugin.bgg.service.StrategyContextService;
import com.lyy.stock.plugin.common.constant.StockConstant;
import com.lyy.stock.plugin.nacos.processor.NacosListenerProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:
 * @createTime: 2023/03/23 17:53:17
 * @version:
 * @Description:
 */
@Configuration
public class BggAutoConfig {

    @Bean
    public RuleCache ruleCache() {
        return new RuleCache();
    }

    @Bean
    public BggPluginAdapter pluginAdapter() {
        return new BggPluginAdapterImpl();
    }

    @Bean
    public LocalConfigLoader localConfigLoader() {
        return new LocalConfigLoader() {
            @Override
            protected String getPath() {
                return StockConstant.CONFIG_PATH;
            }
        };
    }

    @Bean
    @ConditionalOnProperty(value = StockConstant.STOCK_BGG_ENABLED, matchIfMissing = true)
    public ConfigInitializer configInitializer() {
        return new ConfigInitializer();
    }

    @Bean
    public StrategyContextService strategyContextService() {
        return new StrategyContextService();
    }

    @ConditionalOnClass(NacosListenerProcessor.class)
    @ConditionalOnProperty(value = StockConstant.STOCK_BGG_REMOTE_CONFIG_ENABLED, matchIfMissing = true)
    protected static class GatewayBggRouteNacosConfiguration {
        @Bean
        public NacosListenerProcessor gatewayBggRouteNacosProcessor() {
            return new BggRouteNacosProcessor();
        }
    }
}
