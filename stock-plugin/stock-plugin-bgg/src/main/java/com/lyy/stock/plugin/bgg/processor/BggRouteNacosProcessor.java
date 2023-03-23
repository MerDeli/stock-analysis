package com.lyy.stock.plugin.bgg.processor;

import com.alibaba.fastjson.JSONObject;
import com.lyy.stock.plugin.bgg.adapter.BggPluginAdapter;
import com.lyy.stock.plugin.common.constant.StockConstant;
import com.lyy.stock.plugin.common.entity.Rule;
import com.lyy.stock.plugin.nacos.processor.NacosListenerProcessor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @Author:
 * @createTime: 2023/03/23 17:59:39
 * @version:
 * @Description:
 */
public class BggRouteNacosProcessor extends NacosListenerProcessor {

    private static final Logger log = LoggerFactory.getLogger(BggRouteNacosProcessor.class);

    @Autowired
    private BggPluginAdapter pluginAdapter;

    @Override
    public String getGroup() {
        return StockConstant.DYNAMIC_BGG_ROUTE_KEY;
    }

    @Override
    public String getDataId() {
        return pluginAdapter.getServiceId() + "-" + StockConstant.DYNAMIC_BGG_ROUTE_KEY;
    }

    @Override
    public String getDefaultConfig() {
        return StockConstant.DEFAULT_JSON_1;
    }

    @Override
    public void callbackConfig(String config) {
        log.info("nacos listener dataId [bgg-route] -> {}", getDataId());
        log.info("config info [bgg-route]  -> {}", config);
        if(StringUtils.isBlank(config) || (StringUtils.isNotBlank(config) && config.equals(StockConstant.DEFAULT_JSON_1))){
            log.warn("config is null");
            return;
        }
        Rule rule = JSONObject.parseObject(config, Rule.class);
        if(Objects.isNull(rule)){
            log.warn("rule is null");
            return;
        }
        log.info("update bgg rule success");
        pluginAdapter.setRemoteRule(rule);
    }
}
