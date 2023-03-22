package com.lyy.stock.plugin.common.entity;

import java.io.Serializable;

/**
 * @Author:
 * @createTime: 2023/03/22 15:26:43
 * @version:
 * @Description:
 */
public class Condition implements Serializable {
    private static final long serialVersionUID = -4958997347620640664L;
    private String expression;
    private String routeKey;

    public Condition(String expression, String routeKey) {
        this.expression = expression;
        this.routeKey = routeKey;
    }
    public Condition() {}


    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }
}
