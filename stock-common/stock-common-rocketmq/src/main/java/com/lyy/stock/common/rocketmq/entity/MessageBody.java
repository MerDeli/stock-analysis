package com.lyy.stock.common.rocketmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:
 * @createTime: 2023/04/19 17:55:28
 * @version:
 * @Description:
 */
@Data
public class MessageBody {
    // 消息id
    private String messageId;
    // body组装时间
    private long timestamp;
    // 来源 附加信息
    private String msgSource;
    // overload
    private Object data;

    public MessageBody() {

    }
    public MessageBody(String msgKey, Object data, String msgSource) {
        this.messageId = msgKey;
        this.data = data ;
        this.msgSource = msgSource;
        this.timestamp = System.currentTimeMillis();
    }
}

