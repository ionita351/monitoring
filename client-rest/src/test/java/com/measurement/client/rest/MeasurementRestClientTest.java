package com.measurement.client.rest;

import com.monitoring.client.rest.client.MeasurementRestClient;
import com.monitoring.model.MeasurementDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MeasurementRestClientTest {
    private final MeasurementRestClient CLIENT = new MeasurementRestClient();
    private final MeasurementDto DATA = new MeasurementDto(
            UUID.randomUUID(),
            "DN",
            LocalDateTime.now(),
            1D,
            2D,
            true);

//    @Disabled
    @Test
    void testSendOne() {
        CLIENT.sendOne(DATA);
    }

//    @Disabled
    @Test
    void testSendMany() {
        CLIENT.sendMany(List.of(DATA));
    }
}
