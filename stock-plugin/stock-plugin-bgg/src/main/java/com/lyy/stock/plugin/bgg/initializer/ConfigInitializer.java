package com.lyy.stock.plugin.bgg.initializer;

import com.alibaba.fastjson.JSONObject;
import com.lyy.stock.plugin.bgg.adapter.BggPluginAdapter;
import com.lyy.stock.plugin.bgg.loader.LocalConfigLoader;
import com.lyy.stock.plugin.common.entity.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @Author:
 * @createTime: 2023/03/23 17:55:41
 * @version:
 * @Description:
 */
public class ConfigInitializer {

    private static final Logger log = LoggerFactory.getLogger(ConfigInitializer.class);

    @Autowired
    private BggPluginAdapter pluginAdapter;
    @Autowired
    private LocalConfigLoader localConfigLoader;

    @PostConstruct
    public void initialize() {
        String config = null;
        try {
            log.info("init local config [bgg-route]");
            config = localConfigLoader.getConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Rule rule = JSONObject.parseObject(config, Rule.class);
        if(Objects.nonNull(rule)){
            log.info("init local config success [bgg-route] -> {}", config);
            pluginAdapter.setLocalRule(rule);
        }
    }
}
