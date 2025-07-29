package com.monitoring.ws.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.monitoring")
public class WsServerApplication {

    public static void main(String... args) {
        SpringApplication.run(WsServerApplication.class, args);
    }
}
