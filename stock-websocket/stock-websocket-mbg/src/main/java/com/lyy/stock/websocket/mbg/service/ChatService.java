package com.lyy.stock.websocket.mbg.service;

import com.lyy.stock.websocket.mbg.entity.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author:
 * @createTime: 2023/05/06 16:22:13
 * @version:
 * @Description:
 */
@Service
public class ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    public void sendMsg(@Payload ChatMessage chatMessage) {
        Set<SimpUser> users = simpUserRegistry.getUsers();
        LOGGER.info("Send msg by simpMessageSendingOperations:" + chatMessage.toString());
//        simpMessageSendingOperations.convertAndSend("/topic/public", chatMessage);
        String name = users.stream().findFirst().get().getName();
        simpMessageSendingOperations.convertAndSendToUser(name,"/queue/public", chatMessage);
    }

    public void alertUserStatus(@Payload ChatMessage chatMessage) {
        LOGGER.info("Alert user online by simpMessageSendingOperations:" + chatMessage.toString());
        simpMessageSendingOperations.convertAndSend("/topic/public", chatMessage);
    }
}
