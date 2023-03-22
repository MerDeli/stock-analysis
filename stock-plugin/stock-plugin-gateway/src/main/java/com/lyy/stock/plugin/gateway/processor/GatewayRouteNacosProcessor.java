package com.lyy.stock.plugin.gateway.processor;

import com.lyy.stock.plugin.common.adapter.PluginAdapter;
import com.lyy.stock.plugin.common.constant.StockConstant;
import com.lyy.stock.plugin.gateway.route.GatewayRoute;
import com.lyy.stock.plugin.nacos.processor.NacosListenerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:
 * @createTime: 2023/03/22 16:35:42
 * @version:
 * @Description:
 */
public class GatewayRouteNacosProcessor extends NacosListenerProcessor {

    private static final Logger log = LoggerFactory.getLogger(GatewayRouteNacosProcessor.class);

    @Autowired
    private PluginAdapter pluginAdapter;
    @Autowired
    private GatewayRoute gatewayRoute;

    @Override
    public String getGroup() {
        return StockConstant.DYNAMIC_ROUTE_KEY;
    }

    @Override
    public String getDataId() {
        return pluginAdapter.getServiceId() + "-" + StockConstant.DYNAMIC_ROUTE_KEY;
    }

    @Override
    public String getDefaultConfig() {
        return StockConstant.DEFAULT_JSON_2;
    }

    @Override
    public void callbackConfig(String config) {
        log.info("nacos listener dataId [gateway-route] -> {}", getDataId());
        log.info("nacos config info [gateway-route] -> {}", config);
        gatewayRoute.updateAll(config);
    }
}
