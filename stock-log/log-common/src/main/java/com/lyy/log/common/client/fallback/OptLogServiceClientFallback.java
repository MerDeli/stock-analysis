package com.lyy.log.common.client.fallback;

import com.lyy.log.common.client.OptLogServiceClient;
import com.lyy.log.common.entity.OptLog;
import com.lyy.stock.common.core.api.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:
 * @createTime: 2023/03/27 14:06:16
 * @version:
 * @Description:
 */
@Component
public class OptLogServiceClientFallback implements OptLogServiceClient {

    @Override
    public ResponseData<Integer> save(@RequestBody OptLog optLog) {
        return ResponseData.ok();
    }
}
