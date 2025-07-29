package com.measurement.client.rest;

import com.monitoring.client.rest.RestClientApplication;
import com.monitoring.client.rest.client.MeasurementRestClient;
import com.monitoring.model.MeasurementDto;
import com.monitoring.service.MeasurementClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = RestClientApplication.class)
public class MeasurementRestClientTest {
    private final MeasurementDto DATA = new MeasurementDto(
            UUID.randomUUID(),
            "DN",
            LocalDateTime.now(),
            1D,
            2D,
            true);

    @Autowired
    private MeasurementClient client;

//    @Disabled
    @Test
    void testSendOne() {
        client.sendOne(DATA);
    }

//    @Disabled
    @Test
    void testSendMany() {
        client.sendMany(List.of(DATA));
    }
}
