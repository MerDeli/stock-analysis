package com.lyy.stock.plugin.common.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author:
 * @createTime: 2023/03/22 15:25:42
 * @version:
 * @Description:
 */
public class Rule implements Serializable {
    private static final long serialVersionUID = -6294291383078484829L;
    private StrategyRelease strategyRelease;

    private Map<String, String> routes = new LinkedHashMap<>();

    public Map<String, String> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<String, String> routes) {
        this.routes = routes;
    }

    public StrategyRelease getStrategyRelease() {
        return strategyRelease;
    }

    public void setStrategyRelease(StrategyRelease strategyRelease) {
        this.strategyRelease = strategyRelease;
    }

}
