package com.monitoring.client.grpc.mapper;

import com.monitoring.model.MeasurementDto;
import com.monitoring.stub.Measurement;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MeasurementMapperTest {
    private static MeasurementMapper MAPPER = MeasurementMapper.INSTANCE;

    @Test
    void toMeasurementTest() {
        MeasurementDto dto = new MeasurementDto(
                null,
                "DN",
                null,
                1D,
                2D,
                true);
        Measurement measurement = MAPPER.toMeasurement(dto);
        assertNotNull(measurement);

        MeasurementDto dto1 = MAPPER.toMeasurementDto(measurement);
        assertNotNull(dto1);
    }
}
