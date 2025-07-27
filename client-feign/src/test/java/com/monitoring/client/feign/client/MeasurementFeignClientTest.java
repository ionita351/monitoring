package com.monitoring.client.feign.client;

import com.monitoring.model.MeasurementDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class MeasurementFeignClientTest {
    private final MeasurementDto DATA = new MeasurementDto(
            UUID.randomUUID(),
            "DN",
            LocalDateTime.now(),
            1D,
            2D,
            true);

    @Autowired
    private MeasurementFeignClient service;

    @Test
    void readOneTest() {
        service.sendOne(DATA);
    }

    @Test
    void readMany() {
        service.sendMany(List.of(DATA));
    }
}
