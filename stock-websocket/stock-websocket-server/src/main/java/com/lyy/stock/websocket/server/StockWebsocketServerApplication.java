package com.lyy.stock.websocket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.lyy.stock"})
public class StockWebsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockWebsocketServerApplication.class, args);
    }

}
