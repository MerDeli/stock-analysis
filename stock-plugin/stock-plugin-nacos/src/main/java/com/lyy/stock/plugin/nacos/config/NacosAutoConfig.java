package com.lyy.stock.plugin.nacos.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.lyy.stock.plugin.common.constant.StockConstant;
import com.lyy.stock.plugin.common.util.PropertiesUtil;
import com.lyy.stock.plugin.nacos.constant.NacosConstant;
import com.lyy.stock.plugin.nacos.operation.NacosOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @Author:
 * @createTime: 2023/03/22 16:27:30
 * @version:
 * @Description:
 */
@Configuration
public class NacosAutoConfig {
    @Autowired
    private Environment environment;

    @Bean
    public NacosOperation nacosOperation() {
        return new NacosOperation();
    }

    @Bean
    public ConfigService configService() throws NacosException {
        Properties properties = createNacosProperties(environment, true);

        return NacosFactory.createConfigService(properties);
    }

    public static Properties createNacosProperties(Environment environment, boolean enableRemoteSyncConfig) {
        Properties properties = new Properties();
        PropertiesUtil.enrichProperties(properties, environment, NacosConstant.SPRING_CLOUD_NACOS_CONFIG_PREFIX, true, true);
        properties.put(NacosConstant.ENABLE_REMOTE_SYNC_CONFIG, Boolean.toString(enableRemoteSyncConfig));
        properties.put(NacosConstant.NAMESPACE, environment.getProperty(StockConstant.SPRING_PROFILES_ACTIVE));
        return properties;
    }
}
