package com.monitoring.client.rest;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;
import java.util.UUID;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.monitoring")
@EnableScheduling
public class RestClientApplication {
    private static MeasurementDto MEASUREMENT = MeasurementDto.builder()
            .deviceNumber("APU_REST")
            .latitude(59.57)
            .longitude(30.19)
            .alert(true)
            .build();

    public static void main(String... args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Autowired
    private MeasurementClient client;

    @Scheduled(cron = "*/10 * * * * *")
    void generateMeasurements() {
        ResponseDto dto = client.sendOne(MEASUREMENT);
        log.info("APU_REST. Send message. Have got: " + dto);
    }
}
