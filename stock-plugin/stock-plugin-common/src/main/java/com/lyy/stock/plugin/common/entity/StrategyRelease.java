package com.lyy.stock.plugin.common.entity;

import java.io.Serializable;

/**
 * @Author:
 * @createTime: 2023/03/22 15:26:11
 * @version:
 * @Description:
 */
public class StrategyRelease implements Serializable {
    private static final long serialVersionUID = 3698676565717246478L;
    private BlueGreenGray blueGreen;

    private BlueGreenGray gray;

    public BlueGreenGray getBlueGreen() {
        return blueGreen;
    }

    public void setBlueGreen(BlueGreenGray blueGreen) {
        this.blueGreen = blueGreen;
    }

    public BlueGreenGray getGray() {
        return gray;
    }

    public void setGray(BlueGreenGray gray) {
        this.gray = gray;
    }
}
