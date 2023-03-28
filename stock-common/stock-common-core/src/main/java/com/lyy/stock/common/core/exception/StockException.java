package com.lyy.stock.common.core.exception;

import com.lyy.stock.common.core.api.ServiceCode;
import lombok.Data;

/**
 * @Author:
 * @createTime: 2023/03/28 09:42:35
 * @version:
 * @Description:
 */
@Data
public class StockException extends RuntimeException {

    private ServiceCode serviceCode;

    public StockException(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }
    public StockException(int code, String msg) {
        this.serviceCode = new ServiceCode() {
            @Override
            public int getCode() {
                return code;
            }
            @Override
            public String getMsg() {
                return msg;
            }
        };
    }
}
