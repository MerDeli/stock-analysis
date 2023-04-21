package com.lyy.stock.data.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lyy.stock"})
public class StockDataServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockDataServerApplication.class, args);
    }

}
