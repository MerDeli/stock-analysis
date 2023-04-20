package com.lyy.stock.common.rocketmq.component;

import cn.hutool.core.util.IdUtil;
import com.lyy.stock.common.core.exception.StockException;
import com.lyy.stock.common.rocketmq.entity.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static com.lyy.stock.common.rocketmq.exception.MqExceptionCode.MSG_SEND_ERROR;

/**
 * @Author:
 * @createTime: 2023/04/19 17:59:33
 * @version:
 * @Description:
 */
@Component
@Slf4j
public class MqProducterComponent {

    private static enum MSG_TYPE {
        ONEWAY,
        ASYNC,
        SYNC
    }


    @Autowired
    public RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息，通用
     *
     * @param msg_type
     * @param destination
     * @param payload
     */
    private void sendMsg(MSG_TYPE msg_type, String destination, Object payload, String msgSource) {
        String msgKey = IdUtil.simpleUUID();
        MessageBody msgBody = new MessageBody(msgKey, payload, msgSource);
        Message<MessageBody> message = MessageBuilder.withPayload(msgBody).setHeader("KEYS", msgKey).build();
        log.info(String.format("消息发送 MqProducterComponent 开始: %s %s", destination, message));
        SendResult result = null;
        switch (msg_type) {
            case ONEWAY:
                rocketMQTemplate.sendOneWay(destination, message);
                break;
            case ASYNC:
                rocketMQTemplate.asyncSend(destination, message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.error("消息发送失败,topic_tag:{},exception:", destination, ExceptionUtils.getStackTrace(throwable));
                        throw new StockException(MSG_SEND_ERROR);
                    }
                });
                break;
            case SYNC:
                result = rocketMQTemplate.syncSend(destination, message);
                break;
        }
        log.info(String.format("消息发送 MqProducterComponent 结束: msgId: %s dest: %s msg: %s", result != null ? result.getMsgId() : "", destination, message));
    }

    /**
     * 同步发送消息,会确认应答
     *
     * @param destination
     * @param payload
     */
    public void syncSendMsg(String destination, Object payload, String msgSource) {
        sendMsg(MSG_TYPE.SYNC, destination, payload, msgSource);
    }

    /**
     * 同步发送消息,会确认应答
     *
     * @param topic
     * @param tag
     * @param payload
     */
    public void syncSendMsg(String topic, String tag, Object payload, String msgSource) {
        // 发送的消息体，消息体必须存在
        // 业务主键作为消息key
        String destination = topic + ":" + tag;
        syncSendMsg(destination, payload, msgSource);
    }

    /**
     * 异步消息发送,异步日志确认异常
     *
     * @param destination
     * @param payload
     */
    public void asyncSendMsg(String destination, Object payload, String msgSource) {
        sendMsg(MSG_TYPE.ASYNC, destination, payload, msgSource);
    }

    /**
     * 异步消息发送,异步日志确认异常
     *
     * @param topic
     * @param tag
     * @param payload
     * @return
     */
    public void asyncSendMsg(String topic, String tag, Object payload, String msgSource) {
        // 发送的消息体，消息体必须存在
        // 业务主键作为消息key
        String destination = topic + ":" + tag;
        asyncSendMsg(destination, payload, msgSource);
    }

    /**
     * 单向发送消息，不关注结果
     *
     * @param destination
     * @param payload
     */
    public void oneWaySendMsg(String destination, Object payload, String msgSource) {
        sendMsg(MSG_TYPE.ONEWAY, destination, payload, msgSource);
    }

    /**
     * 单向发送消息，不关注结果
     *
     * @param topic
     * @param tag
     * @param payload
     */
    public void oneWaySendMsg(String topic, String tag, Object payload, String msgSource) {
        // 发送的消息体，消息体必须存在
        // 业务主键作为消息key
        String destination = topic + ":" + tag;
        oneWaySendMsg(destination, payload, msgSource);
    }
}
