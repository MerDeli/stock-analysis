package com.lyy.stock.common.rocketmq.exception;

import com.lyy.stock.common.core.api.ServiceCode;
import lombok.Getter;

/**
 * @Author:
 * @createTime: 2023/04/19 18:05:40
 * @version:
 * @Description:
 */
@Getter
public enum MqExceptionCode implements ServiceCode {
    MSG_SEND_ERROR(0101001,"消息发送失败");


    private int code;
    private String msg;

    MqExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
