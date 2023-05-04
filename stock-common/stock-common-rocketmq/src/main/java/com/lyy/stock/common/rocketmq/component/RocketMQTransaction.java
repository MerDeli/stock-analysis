package com.lyy.stock.common.rocketmq.component;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:
 * @createTime: 2023/04/28 18:27:04
 * @version:
 * @Description:
 */
@Component
@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")
public class RocketMQTransaction implements RocketMQLocalTransactionListener {

    private ConcurrentHashMap map = new ConcurrentHashMap();

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        map.forEach((Object t,Object u)->{
            System.out.println(t);
        });
        return RocketMQLocalTransactionState.COMMIT;
    }
}
