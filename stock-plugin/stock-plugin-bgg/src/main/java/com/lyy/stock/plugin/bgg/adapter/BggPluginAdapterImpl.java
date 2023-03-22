package com.lyy.stock.plugin.bgg.adapter;

import com.lyy.stock.plugin.bgg.cache.RuleCache;
import com.lyy.stock.plugin.common.adapter.PluginAdapterImpl;
import com.lyy.stock.plugin.common.constant.StockConstant;
import com.lyy.stock.plugin.common.entity.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @Author:
 * @createTime: 2023/03/22 15:27:54
 * @version:
 * @Description:
 */
public class BggPluginAdapterImpl extends PluginAdapterImpl implements BggPluginAdapter {

    @Autowired
    protected RuleCache ruleCache;

    @Override
    public void setLocalRule(Rule rule) {
        ruleCache.put(StockConstant.LOCAL_RULE, rule);
    }

    @Override
    public Rule getLocalRule() {
        return ruleCache.get(StockConstant.LOCAL_RULE);
    }

    @Override
    public void setRemoteRule(Rule rule) {
        ruleCache.put(StockConstant.REMOTE_RULE, rule);
    }

    @Override
    public Rule getRemoteRule() {
        return ruleCache.get(StockConstant.REMOTE_RULE);
    }

    @Override
    public Rule getRule() {
        Rule remoteRule = getRemoteRule();
        if (Objects.nonNull(remoteRule)) {
            return remoteRule;
        }
        return getLocalRule();
    }
}
