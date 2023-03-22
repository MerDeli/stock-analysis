package com.lyy.stock.plugin.nacos.processor;

import com.alibaba.nacos.api.config.listener.Listener;
import com.lyy.stock.plugin.common.thread.ThreadPoolFactory;
import com.lyy.stock.plugin.nacos.operation.NacosOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.concurrent.ExecutorService;

/**
 * @Author:
 * @createTime: 2023/03/22 16:20:45
 * @version:
 * @Description:
 */
public class NacosListenerProcessor extends AbstractConfigProcessor {

    private final ExecutorService executorService = ThreadPoolFactory.getExecutorService("nacos-config");
    private static final Logger log = LoggerFactory.getLogger(NacosListenerProcessor.class);

    @Autowired
    private NacosOperation nacosOperation;

    @Autowired
    private Environment environment;

    private Listener listener;


    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getDataId() {
        return null;
    }

    @Override
    public String getDefaultConfig() {
        return null;
    }

    @Override
    public void callbackConfig(String config) {

    }

    @Override
    public void destroy() throws Exception {

    }
}
