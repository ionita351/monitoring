package com.monitoring.crud.mapper;

import com.monitoring.jpa.entity.Measurement;
import com.monitoring.jpa.mapper.MeasurementMapper;
import com.monitoring.model.MeasurementDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeasurementMapperTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String DEVICE_NUMBER = "12345";
    private static final LocalDateTime TIME_STAMP = LocalDateTime.now();
    private static final Double LONGITUDE = 1D;
    private static final Double LATITUDE = 2D;
    private static final boolean ALERT = true;

    @Test
    void toLocation() {
        MeasurementDto dto = new MeasurementDto(ID, DEVICE_NUMBER, TIME_STAMP, LATITUDE, LONGITUDE, ALERT);

        Measurement measurement = MeasurementMapper.INSTANCE.toMeasurement(dto);
        assertEquals(ID, measurement.getId());
        assertEquals(DEVICE_NUMBER, measurement.getDeviceNumber());
        assertEquals(TIME_STAMP, measurement.getTimeStamp());
        assertEquals(LATITUDE, measurement.getLatitude());
        assertEquals(LONGITUDE, measurement.getLongitude());
        assertEquals(ALERT, measurement.isAlert());
    }

    @Test
    void toLocationDto() {
        Measurement measurement = new Measurement(ID, DEVICE_NUMBER, TIME_STAMP, LATITUDE, LONGITUDE, ALERT);

        MeasurementDto measurementDto = MeasurementMapper.INSTANCE.toMeasurementDto(measurement);
        assertEquals(ID, measurementDto.getId());
        assertEquals(DEVICE_NUMBER, measurementDto.getDeviceNumber());
        assertEquals(TIME_STAMP, measurementDto.getTimeStamp());
        assertEquals(LATITUDE, measurementDto.getLatitude());
        assertEquals(LONGITUDE, measurementDto.getLongitude());
        assertEquals(ALERT, measurementDto.isAlert());
    }
}
