package com.lyy.stock.websocket.mbg.interceptor;

import com.lyy.stock.websocket.mbg.entity.model.WebSocketUser;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @createTime: 2023/05/08 15:19:07
 * @version:
 * @Description:
 */
public class UserInterceptor extends ChannelInterceptorAdapter {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message
                    .getHeaders()
                    .get(SimpMessageHeaderAccessor.NATIVE_HEADERS);

            if (raw instanceof Map) {
                Object name = ((Map) raw).get("name");

                if (name instanceof List) {
                    accessor.setUser(new WebSocketUser(((ArrayList) name).get(0).toString()));
                }
            }
        }
        return message;
    }
}
