package com.lyy.stock.plugin.common.adapter;

import org.springframework.cloud.client.ServiceInstance;

import java.util.Map;

public interface PluginAdapter {
    Map<String, String> getServerMetadata(ServiceInstance server);

    String getServerServiceId(ServiceInstance server);

    String getServiceId();
}
