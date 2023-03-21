package com.lyy.ums.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.lyy.ums"})
@EnableDiscoveryClient
public class UmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmsServerApplication.class, args);
    }

}
