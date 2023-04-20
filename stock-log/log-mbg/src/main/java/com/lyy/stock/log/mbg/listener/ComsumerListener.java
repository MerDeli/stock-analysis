package com.lyy.stock.log.mbg.listener;

import com.lyy.stock.common.rocketmq.entity.MessageBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author:
 * @createTime: 2023/04/19 18:34:19
 * @version:
 * @Description:
 */
@RocketMQMessageListener(topic = "test-topic",nameServer = "${rocketmq.nameServer}",consumerGroup = "${rocketmq.consumer.group}", selectorExpression = "test-tag")
@Component
@Slf4j
public class ComsumerListener implements RocketMQListener<MessageBody> {
    @Override
    public void onMessage(MessageBody messageBody) {
        System.out.println(messageBody);
    }
}
