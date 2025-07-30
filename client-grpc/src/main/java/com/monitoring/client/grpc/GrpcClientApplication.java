package com.monitoring.client.grpc;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.monitoring")
@EnableDiscoveryClient
@EnableScheduling
public class GrpcClientApplication {
    private static MeasurementDto MEASUREMENT = new MeasurementDto(
            UUID.randomUUID(),
            "APU_GRPC",
            null,
            1D,
            2D,
            true);

    public static void main(String... args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    @Autowired
    private MeasurementClient client;

    @Scheduled(cron = "*/10 * * * * *")
    void generateMeasurements() {
        ResponseDto dto = client.sendOne(MEASUREMENT);
        log.info("APU_GRPC. Send message. Have got: " + dto);
    }
}
