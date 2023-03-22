package com.lyy.stock.plugin.nacos.operation;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.lyy.stock.plugin.nacos.constant.NacosConstant;
import com.lyy.stock.plugin.nacos.enums.NacosFormatType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;

/**
 * @Author:
 * @createTime: 2023/03/22 16:24:00
 * @version:
 * @Description:
 */
public class NacosOperation implements DisposableBean {

    private static final Logger LOG = LoggerFactory.getLogger(NacosOperation.class);

    @Autowired
    private ConfigService configService;


    public String getConfig(String group, String serviceId) throws NacosException {
        return configService.getConfig(serviceId, group, NacosConstant.NACOS_DEFAULT_TIMEOUT);
    }

    public boolean removeConfig(String group, String serviceId) throws NacosException {
        return configService.removeConfig(serviceId, group);
    }

    public boolean publishConfig(String group, String serviceId, String config) throws NacosException {
        return configService.publishConfig(serviceId, group, config);
    }

    public boolean publishConfig(String group, String serviceId, String config, NacosFormatType formatType) throws NacosException {
        return configService.publishConfig(serviceId, group, config, formatType.toString());
    }

    public Listener subscribeConfig(String group, String serviceId, Executor executor, NacosSubscribeCallback nacosSubscribeCallback) throws NacosException {
        Listener listener = new Listener() {
            @Override
            public void receiveConfigInfo(String config) {
                nacosSubscribeCallback.callback(config);
            }

            @Override
            public Executor getExecutor() {
                return executor;
            }
        };

        configService.addListener(serviceId, group, listener);

        return listener;
    }

    public void unsubscribeConfig(String group, String serviceId, Listener listener) {
        configService.removeListener(serviceId, group, listener);
    }

    @Override
    public void destroy() throws Exception {
        configService.shutDown();

        LOG.info("Shutting down Nacos config service...");
    }
}
