package com.lyy.stock.plugin.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:
 * @createTime: 2023/03/22 15:26:26
 * @version:
 * @Description:
 */
public class BlueGreenGray implements Serializable {
    private static final long serialVersionUID = 8132971609944999016L;
    private List<Condition> conditionList;

    private String basicCondition;

    public BlueGreenGray(List<Condition> conditionList, String basicCondition) {
        this.conditionList = conditionList;
        this.basicCondition = basicCondition;
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public String getBasicCondition() {
        return basicCondition;
    }

    public void setBasicCondition(String basicCondition) {
        this.basicCondition = basicCondition;
    }
}
