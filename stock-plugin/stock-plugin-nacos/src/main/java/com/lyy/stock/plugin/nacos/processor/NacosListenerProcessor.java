package com.lyy.stock.plugin.nacos.processor;

import com.alibaba.nacos.api.config.listener.Listener;
import com.lyy.stock.plugin.common.constant.StockConstant;
import com.lyy.stock.plugin.common.thread.ThreadPoolFactory;
import com.lyy.stock.plugin.nacos.enums.NacosFormatType;
import com.lyy.stock.plugin.nacos.operation.NacosOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;

/**
 * @Author:
 * @createTime: 2023/03/22 16:20:45
 * @version:
 * @Description:
 */
public abstract class NacosListenerProcessor extends AbstractConfigProcessor {

    private final ExecutorService executorService = ThreadPoolFactory.getExecutorService("nacos-config");
    private static final Logger log = LoggerFactory.getLogger(NacosListenerProcessor.class);

    @Autowired
    private NacosOperation nacosOperation;

    @Autowired
    private Environment environment;

    private Listener listener;


    @PostConstruct
    public void initialize() {
        String group = getGroup();
        String dataId = getDataId();
        try {
            listener = nacosOperation.subscribeConfig(group, dataId, executorService, this::callbackConfig);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("add nacos config listener error");
        }
        try {
            String config = nacosOperation.getConfig(group, dataId);
            if (config != null) {
                callbackConfig(config);
            } else {
                log.warn("get nacos config is null");
                boolean initConfig = Boolean.parseBoolean(environment.getProperty(StockConstant.STOCK_NACOS_CONFIG_INIT));
                if(initConfig){
                    nacosOperation.publishConfig(group, dataId, getDefaultConfig(), NacosFormatType.JSON_FORMAT);
                    log.info("create nacos config success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get nacos config error");
        }
    }

    @Override
    public void destroy() {
        if (listener == null) {
            return;
        }
        log.info("NacosListenerProcessor destroy");
        String group = getGroup();
        String dataId = getDataId();
        try {
            nacosOperation.unsubscribeConfig(group, dataId, listener);
        } catch (Exception e) {
            e.printStackTrace();
            executorService.shutdownNow();
        }
    }
}
