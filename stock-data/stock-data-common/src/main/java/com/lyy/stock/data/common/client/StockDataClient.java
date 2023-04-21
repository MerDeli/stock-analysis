package com.lyy.stock.data.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="stock-data",path="/srv/stockData")
public interface StockDataClient {

    @GetMapping("/add")
    Boolean insertData(@RequestParam("name")String name,@RequestParam("code")String code);
}
