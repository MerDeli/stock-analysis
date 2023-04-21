package com.lyy.stock.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.lyy.stock.data.common.client"})
public class StockTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockTestApplication.class, args);
    }

}
