package com.lyy.stock.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StockGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockGatewayApplication.class, args);
    }

}
