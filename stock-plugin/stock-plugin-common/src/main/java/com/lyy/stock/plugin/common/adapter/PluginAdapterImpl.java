package com.lyy.stock.plugin.common.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.serviceregistry.Registration;

import java.util.Map;

/**
 * @Author:
 * @createTime: 2023/03/22 15:18:24
 * @version:
 * @Description:
 */
public class PluginAdapterImpl implements PluginAdapter {

    @Autowired
    protected Registration registration;

    @Override
    public Map<String, String> getServerMetadata(ServiceInstance server) {
        return server.getMetadata();
    }

    @Override
    public String getServerServiceId(ServiceInstance server) {
        return server.getServiceId();
    }

    @Override
    public String getServiceId() {
        return registration.getServiceId().toLowerCase();
    }
}
