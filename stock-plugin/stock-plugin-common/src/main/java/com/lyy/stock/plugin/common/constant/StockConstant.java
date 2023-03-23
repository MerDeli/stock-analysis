package com.lyy.stock.plugin.common.constant;

public interface StockConstant {

    String LOCAL_RULE = "local-rule";
    String REMOTE_RULE = "remote-rule";
    String CONFIG_PATH = "classpath:rule.json";
    String VERSION = "version";
    String NACOS_WEIGHT = "nacos.weight";
    String BGG_ROUTE = "Bgg-Route";

    String STOCK_BGG_ENABLED = "stock.bgg.enabled";
    String STOCK_BGG_REMOTE_CONFIG_ENABLED = "stock.bgg.remote-config.enabled";
    String STOCK_GATEWAY_ROUTE_ENABLED = "stock.gateway.dynamic-route.enabled";
    String SPRING_PROFILES_ACTIVE =  "spring.profiles.active";
    String STOCK_NACOS_CONFIG_INIT = "stock.nacos-config-init";




    String EXPRESSION_PREFIX = "H";
    String EXPRESSION_REGEX = "\\#" + EXPRESSION_PREFIX + "\\['\\S+'\\]";
    String SPLIT_FH = ";";
    String SPLIT_DY = "=";
    String EXPRESSION_SUB_PREFIX = "#" + EXPRESSION_PREFIX + "['";
    String EXPRESSION_SUB_SUFFIX = "']";
    String DEFAULT_JSON_2 = "[]";
    String DEFAULT_JSON_1 = "{}";

    String DYNAMIC_ROUTE_KEY = "dynamic-route";
    String DYNAMIC_BGG_ROUTE_KEY = "bgg-dynamic-route";
}
