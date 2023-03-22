package com.lyy.stock.plugin.gateway.route;

import com.alibaba.fastjson2.JSON;
import com.lyy.stock.plugin.common.entity.GatewayRouteEntity;
import com.lyy.stock.plugin.common.future.FutureResolver;
import com.lyy.stock.plugin.common.thread.ThreadPoolFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @Author:
 * @createTime: 2023/03/22 15:37:18
 * @version:
 * @Description:
 */
public class GatewayRouteImpl implements GatewayRoute, ApplicationEventPublisherAware  {

    private static final Logger log = LoggerFactory.getLogger(GatewayRouteImpl.class);
    private final ExecutorService executorService = ThreadPoolFactory.getExecutorService("gateway-route-operation");
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private RouteDefinitionLocator routeDefinitionLocator;
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    @Autowired
    private GatewayProperties gatewayProperties;

    @Override
    public void add(GatewayRouteEntity gatewayStrategyRouteEntity) {
        if (Objects.isNull(gatewayStrategyRouteEntity)) {
            log.error("route entity is null");
            return;
        }
        Map<String, RouteDefinition> routeDefinitionMap = this.currentGatewayRoutes();
        String routeId = gatewayStrategyRouteEntity.getId();
        if (routeDefinitionMap.containsKey(routeId)) {
            log.error("route key is exist");
            return;
        }
        RouteDefinition routeDefinition = this.convertRoute(gatewayStrategyRouteEntity);
        addGatewayRoute(routeDefinition);
        log.info("add gateway dynamic route={}", JSON.toJSONString(routeDefinition));
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void update(GatewayRouteEntity gatewayStrategyRouteEntity) {

    }

    @Override
    public void delete(String routeId) {

    }

    @Override
    public void updateAll(String gatewayStrategyRouteConfig) {

    }

    @Override
    public List<GatewayRouteEntity> allList() {
        return null;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public void addGatewayRoute(RouteDefinition routeDefinition) {
        Disposable disposable = null;
        try {
            disposable = routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        } finally {
            if (disposable != null) {
                disposable.dispose();
            }
        }
    }

    public void updateGatewayRoute(RouteDefinition routeDefinition) {
        deleteGatewayRoute(routeDefinition);
        addGatewayRoute(routeDefinition);
    }

    public void deleteGatewayRoute(RouteDefinition routeDefinition) {
        Disposable disposable = null;
        try {
            disposable = routeDefinitionWriter
                    .delete(Mono.just(routeDefinition.getId()))
                    .onErrorResume(throwable -> {
                        if (throwable instanceof NotFoundException) {
                            gatewayProperties.getRoutes().removeIf(routeCandidate -> routeCandidate.getId().equals(routeDefinition.getId()));
                            return Mono.empty();
                        }
                        return Mono.error(throwable);
                    }).subscribe();
        } finally {
            if (disposable != null) {
                disposable.dispose();
            }
        }
    }

    public Map<String, RouteDefinition> currentGatewayRoutes() {
        Flux<RouteDefinition> routeDefinitions = routeDefinitionLocator.getRouteDefinitions();
        try {
            return FutureResolver.call(executorService, () -> {
                List<RouteDefinition> routeDefinitionList = routeDefinitions.collectList().block();
                if (CollectionUtils.isEmpty(routeDefinitionList)) {
                    return new HashMap<>();
                }
                return routeDefinitionList.stream().collect(Collectors.toMap(RouteDefinition::getId, RouteDefinition -> RouteDefinition));
            });
        } catch (Exception e) {
            return new HashMap<>();
        }
    }


    public RouteDefinition convertRoute(GatewayRouteEntity gatewayRouteEntity) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRouteEntity.getId());
        routeDefinition.setUri(convertURI(gatewayRouteEntity.getUri()));
        List<String> predicateList = gatewayRouteEntity.getPredicates();
        List<PredicateDefinition> predicateDefinitionList = new ArrayList<>(predicateList.size());
        for (String predicate : predicateList) {
            predicateDefinitionList.add(new PredicateDefinition(predicate));
        }
        routeDefinition.setPredicates(predicateDefinitionList);
        List<String> filterList = gatewayRouteEntity.getFilters();
        List<FilterDefinition> filterDefinitionList = new ArrayList<>(filterList.size());
        for (String filter : filterList) {
            filterDefinitionList.add(new FilterDefinition(filter));
        }
        routeDefinition.setFilters(filterDefinitionList);
        routeDefinition.setOrder(gatewayRouteEntity.getOrder());
        routeDefinition.setMetadata(gatewayRouteEntity.getMetadata());
        return routeDefinition;
    }

    public URI convertURI(String value) {
        URI uri;
        if (value.toLowerCase().startsWith("http") || value.toLowerCase().startsWith("https")) {
            uri = UriComponentsBuilder.fromHttpUrl(value).build().toUri();
        } else {
            uri = URI.create(value);
        }
        return uri;
    }
}
