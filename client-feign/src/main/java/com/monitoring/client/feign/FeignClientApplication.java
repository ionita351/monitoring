package com.monitoring.client.feign;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.monitoring")
@EnableFeignClients
@EnableScheduling
public class FeignClientApplication {
    private static MeasurementDto MEASUREMENT = new MeasurementDto(
            UUID.randomUUID(),
            "APU_FEIGN",
            null,
            1D,
            2D,
            true);

    public static void main(String... args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @Autowired
    private MeasurementClient client;

    @Scheduled(cron = "*/10 * * * * *")
    void generateMeasurements() {
        ResponseDto dto = client.sendOne(MEASUREMENT);
        log.info("APU_FEIGN. Send message. Have got: " + dto);
    }
}
