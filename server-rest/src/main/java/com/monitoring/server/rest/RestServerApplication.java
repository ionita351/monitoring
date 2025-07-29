package com.monitoring.server.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.monitoring"})
public class RestServerApplication {

    public static void main(String... args) {
        SpringApplication.run(RestServerApplication.class, args);
    }
}
