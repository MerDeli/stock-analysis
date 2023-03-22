package com.lyy.stock.plugin.nacos.processor;

import org.springframework.beans.factory.DisposableBean;

/**
 * @Author:
 * @createTime: 2023/03/22 16:21:02
 * @version:
 * @Description:
 */
public abstract class AbstractConfigProcessor implements DisposableBean {

    public abstract String getGroup();

    public abstract String getDataId();

    public abstract String getDefaultConfig();

    public abstract void callbackConfig(String config);
}
