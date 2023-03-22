package com.lyy.stock.plugin.bgg.context;

import com.lyy.stock.plugin.bgg.service.StrategyContextService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:
 * @createTime: 2023/03/22 14:45:50
 * @version:
 * @Description:
 */
public abstract class AbstractStrategyContextHolder implements StrategyContextHolder{

    @Autowired
    private StrategyContextService strategyContextService;

    @Override
    public String getRouteVersion() {
        return strategyContextService.getRouteVersion();
    }
}
