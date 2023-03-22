package com.lyy.stock.plugin.gateway.route;

import com.lyy.stock.plugin.common.entity.GatewayRouteEntity;

import java.util.List;

public interface GatewayRoute {

    void add(GatewayRouteEntity gatewayStrategyRouteEntity);

    void update(GatewayRouteEntity gatewayStrategyRouteEntity);

    void delete(String routeId);

    void updateAll(String gatewayStrategyRouteConfig);

    List<GatewayRouteEntity> allList();
}
