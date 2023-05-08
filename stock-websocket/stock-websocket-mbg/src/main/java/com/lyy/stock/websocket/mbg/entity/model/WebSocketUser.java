package com.lyy.stock.websocket.mbg.entity.model;

import java.security.Principal;

/**
 * @Author:
 * @createTime: 2023/05/08 13:50:22
 * @version:
 * @Description:
 */
public class WebSocketUser implements Principal {

    private String username;

    public WebSocketUser(String username) {
        this.username = username;
    }

    @Override
    public String getName() {
        return this.username;
    }
}
