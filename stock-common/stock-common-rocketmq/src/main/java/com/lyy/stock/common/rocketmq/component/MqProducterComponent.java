package com.lyy.stock.common.rocketmq.component;

import cn.hutool.core.util.IdUtil;
import com.lyy.stock.common.core.exception.StockException;
import com.lyy.stock.common.rocketmq.entity.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
        // 单向
        ONEWAY,
        // 异步
        ASYNC,
        // 同步
        SYNC,
        // 单向延迟
        ONEWAY_ORDERLY,
        // 异步延迟
        ASYNC_ORDERLY,
        // 同步延迟
        SYNC_ORDERLY,
        // 事务
        TRANSACTION,
    }


    @Autowired
    public RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息，通用
     *
     * @param msg_type 消息类型
     * @param destination  主题[:标签]
     * @param payload   数据
     * @param msgSource   附加信息
     * @param timeout   超时时长
     * @param delayLevel   延迟发送等级
     * @param hashKey   顺序发送的hashkey
     */
    private void sendMsg(MSG_TYPE msg_type, String destination, Object payload, String msgSource, Integer timeout, Integer delayLevel, String hashKey) {
        // 当超时时长不存在时候,赋默认值
        timeout = Objects.isNull(timeout)?3000:timeout;
        // 当延迟发送等级不存在时候,赋默认值
        delayLevel = Objects.isNull(delayLevel)?0:delayLevel;
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
                        log.info("异步发送消息成功,消息返回:{}",sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.error("异步发送消息失败,topic_tag:{},exception:", destination, ExceptionUtils.getStackTrace(throwable));
                        throw new StockException(MSG_SEND_ERROR);
                    }
                },timeout,delayLevel);
                break;
            case SYNC:
                result = rocketMQTemplate.syncSend(destination, message,timeout,delayLevel);
                break;
            case ONEWAY_ORDERLY:
                rocketMQTemplate.sendOneWayOrderly(destination,message,hashKey);
                break;
            case SYNC_ORDERLY:
                result = rocketMQTemplate.syncSendOrderly(destination, message, hashKey);
                break;
            case ASYNC_ORDERLY:
                rocketMQTemplate.asyncSendOrderly(destination,message,hashKey, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("异步顺序发送消息成功,消息返回:{}",sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        log.error("异步顺序发送消息失败,topic_tag:{},exception:", destination, ExceptionUtils.getStackTrace(throwable));
                        throw new StockException(MSG_SEND_ERROR);
                    }
                },timeout);
                break;
            case TRANSACTION:
                // 需要添加事务监听器
                TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, msgSource);
                if(sendResult.getLocalTransactionState() != LocalTransactionState.COMMIT_MESSAGE){
                    throw new RuntimeException("执行事务消息失败");
                }else{
                    log.info("执行事务消息成功");
                }
                break;
        }
        log.info(String.format("消息发送 MqProducterComponent 结束: msgId: %s dest: %s msg: %s", result != null ? result.getMsgId() : "", destination, message));
    }

    /**
     * 同步发送消息,会确认应答(延迟)
     *
     * @param destination
     * @param payload
     */
    public void syncSendMsg(String destination, Object payload, String msgSource,Integer timeout,Integer delayLevel) {
        sendMsg(MSG_TYPE.SYNC, destination, payload, msgSource,timeout,delayLevel,null);
    }

    /**
     * 异步消息发送,异步日志确认异常(延迟)
     *
     * @param destination
     * @param payload
     */
    public void asyncSendMsg(String destination, Object payload, String msgSource,Integer timeout,Integer delayLevel) {
        sendMsg(MSG_TYPE.ASYNC, destination, payload, msgSource,timeout,delayLevel,null);
    }

    /**
     * 单向发送消息，不关注结果(延迟)
     *
     * @param destination
     * @param payload
     */
    public void oneWaySendMsg(String destination, Object payload, String msgSource) {
        sendMsg(MSG_TYPE.ONEWAY, destination, payload, msgSource,null,null,null);
    }


    /**
     * 同步顺序发送消息,会确认应答
     *
     * @param destination
     * @param payload
     */
    public void syncSendOrderlyMsg(String destination, Object payload, String msgSource,Integer timeout,Integer delayLevel,String hashKey) {
        sendMsg(MSG_TYPE.SYNC_ORDERLY, destination, payload, msgSource,timeout,delayLevel,hashKey);
    }

    /**
     * 异步顺序消息发送,异步日志确认异常
     *
     * @param destination
     * @param payload
     */
    public void asyncSendOrderlyMsg(String destination, Object payload, String msgSource,Integer timeout,Integer delayLevel,String hashKey) {
        sendMsg(MSG_TYPE.ASYNC_ORDERLY, destination, payload, msgSource,timeout,delayLevel,hashKey);
    }

    /**
     * 单向顺序发送消息，不关注结果
     *
     * @param destination
     * @param payload
     */
    public void oneWaySendOrderlyMsg(String destination, Object payload, String msgSource,String hashKey) {
        sendMsg(MSG_TYPE.ONEWAY_ORDERLY, destination, payload, msgSource,null,null,hashKey);
    }

    /**
     * 事务发送消息，会确认应答
     *
     * @param destination
     * @param payload
     */
    public void transactionSendMsg(String destination, Object payload, String msgSource) {
        sendMsg(MSG_TYPE.TRANSACTION, destination, payload, msgSource,null,null,null);
    }
}
