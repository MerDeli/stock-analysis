package com.lyy.stock.plugin.bgg.adapter;

import com.lyy.stock.plugin.common.adapter.PluginAdapter;
import com.lyy.stock.plugin.common.entity.Rule;

public interface BggPluginAdapter extends PluginAdapter {

    void setLocalRule(Rule rule);

    Rule getLocalRule();

    void setRemoteRule(Rule rule);

    Rule getRemoteRule();

    Rule getRule();
}
