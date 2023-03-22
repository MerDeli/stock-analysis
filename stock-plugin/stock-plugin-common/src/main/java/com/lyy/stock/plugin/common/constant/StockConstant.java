package com.lyy.stock.plugin.common.constant;

public interface StockConstant {

    String LOCAL_RULE = "local-rule";
    String REMOTE_RULE = "remote-rule";

    String STOCK_BGG_ENABLED = "stock.bgg.enabled";
    String STOCK_GATEWAY_ROUTE_ENABLED = "stock.gateway.dynamic-route.enabled";
    String SPRING_PROFILES_ACTIVE =  "spring.profiles.active";




    String EXPRESSION_PREFIX = "H";
    String EXPRESSION_REGEX = "\\#" + EXPRESSION_PREFIX + "\\['\\S+'\\]";
    String SPLIT_FH = ";";
    String SPLIT_DY = "=";
    String EXPRESSION_SUB_PREFIX = "#" + EXPRESSION_PREFIX + "['";
    String EXPRESSION_SUB_SUFFIX = "']";
    String DEFAULT_JSON_2 = "[]";

    String DYNAMIC_ROUTE_KEY = "dynamic-route";
    String DYNAMIC_BGG_ROUTE_KEY = "bgg-dynamic-route";
}
