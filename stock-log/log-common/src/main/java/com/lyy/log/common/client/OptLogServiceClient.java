package com.lyy.log.common.client;


import com.lyy.log.common.client.fallback.OptLogServiceClientFallback;
import com.lyy.log.common.constant.OptLogConstant;
import com.lyy.log.common.entity.OptLog;
import com.lyy.stock.common.core.api.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = OptLogConstant.BASE_SERVICE_NAME, path = OptLogConstant.BASE_PATH + "/optlog",fallback = OptLogServiceClientFallback.class)
public interface OptLogServiceClient {

    /**
     * 新增系统操作日志表
     * @param optLog 系统操作日志表
     * @return ResponseData
     */
    @PostMapping("/save")
    ResponseData<Integer> save(@RequestBody OptLog optLog);
}
