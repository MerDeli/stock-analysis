package com.lyy.stock.plugin.sentinel.processor;

import com.lyy.stock.plugin.nacos.processor.NacosListenerProcessor;

/**
 * @Author:
 * @createTime: 2023/03/29 15:39:59
 * @version:
 * @Description:
 */
public class SentinelRuleNacosProcessor extends NacosListenerProcessor {
    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getDataId() {
        return null;
    }

    @Override
    public String getDefaultConfig() {
        return null;
    }

    @Override
    public void callbackConfig(String config) {

    }
}
