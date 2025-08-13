package com.monitoring.client.reactive;

import com.monitoring.client.reactive.client.MeasurementReactiveClient;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class ReactiveClientApplication {
    private static MeasurementDto MEASUREMENT = MeasurementDto.builder()
            .deviceNumber("APU_REACTIVE")
            .latitude(59.57)
            .longitude(30.19)
            .alert(true)
            .build();

    public static void main(String... args) {
        SpringApplication.run(ReactiveClientApplication.class, args);
    }

    @Autowired
    private MeasurementReactiveClient client;

    @Scheduled(cron = "*/10 * * * * *")
    void generateMeasurements() {
        ResponseDto dto = client.sendOne(MEASUREMENT);
        log.info("APU_REACTIVE. Send message. Have got: " + dto);
    }
}
