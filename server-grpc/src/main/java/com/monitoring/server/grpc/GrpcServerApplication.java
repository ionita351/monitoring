package com.monitoring.server.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.monitoring")
public class GrpcServerApplication {

    public static void main(String... args) {
        SpringApplication.run(GrpcServerApplication.class, args);
    }
}
