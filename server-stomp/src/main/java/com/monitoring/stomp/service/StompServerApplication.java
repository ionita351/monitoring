package com.monitoring.stomp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.monitoring")
public class StompServerApplication {

    public static void main(String... args) {
        SpringApplication.run(StompServerApplication.class, args);
    }
}
