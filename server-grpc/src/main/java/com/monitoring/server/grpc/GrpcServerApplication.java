package com.monitoring.server.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.monitoring")
public class GrpcServerApplication {

    public static void main(String... args) {
        SpringApplication.run(GrpcServerApplication.class, args);
    }
}
