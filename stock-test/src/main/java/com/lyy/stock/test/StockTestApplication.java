package com.lyy.stock.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.lyy.stock.data.common.client"})
public class StockTestApplication {

    public static void main(String[] args) {
        // 设置drools的日期格式，否则会导致启动异常错误:it should follow: [dd-MMM-yyyy]
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        SpringApplication.run(StockTestApplication.class, args);
    }

}
