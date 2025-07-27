package com.monitoring.server.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.monitoring"})
public class ServerRestApplication {

    public static void main(String... args) {
        SpringApplication.run(ServerRestApplication.class, args);
    }
}
