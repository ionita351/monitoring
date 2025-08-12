package com.monitoring.server.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication(scanBasePackages = "com.monitoring", exclude =  {DataSourceAutoConfiguration.class })
public class ReactiveServerApplication {
    public static void main(String... args) {
        SpringApplication.run(ReactiveServerApplication.class, args);
    }
}
