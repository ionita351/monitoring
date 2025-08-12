package com.monitoring.crud.mapper;

import com.monitoring.jpa.entity.Device;
import com.monitoring.jpa.entity.Measurement;
import com.monitoring.jpa.mapper.DeviceMapper;
import com.monitoring.model.DeviceDto;
import com.monitoring.model.MeasurementDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeviceMapperTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String DEVICE_NUMBER = "123";

    private static final UUID MEASUREMENT_ID = UUID.randomUUID();
    private static final LocalDateTime TIME_STAMP = LocalDateTime.now();
    private static final Double LATITUDE = 1D;
    private static final Double LONGITUDE = 2D;
    private static final boolean ALERT = true;

    @Test
    void deviceDtoToDeviceTest() {
        MeasurementDto measurementDto = new MeasurementDto(MEASUREMENT_ID, DEVICE_NUMBER, TIME_STAMP, LATITUDE, LONGITUDE, ALERT);
        DeviceDto dto = new DeviceDto(ID, DEVICE_NUMBER, measurementDto);

        Device device = DeviceMapper.INSTANCE.deviceDtoToDevice(dto);
        assertEquals(ID, device.getId());
        assertEquals(DEVICE_NUMBER, device.getDeviceNumber());

        Measurement measurement = device.getMeasurement();
        assertEquals(MEASUREMENT_ID, measurement.getId());
        assertEquals(TIME_STAMP, measurement.getTimeStamp());
        assertEquals(LATITUDE, measurement.getLatitude());
        assertEquals(LONGITUDE, measurement.getLongitude());
        assertTrue(measurement.isAlert());
    }

    @Test
    void deviceToDeviceDtoTest() {
        Measurement measurement = new Measurement(MEASUREMENT_ID, DEVICE_NUMBER, TIME_STAMP, LATITUDE, LONGITUDE, ALERT);
        Device device = new Device(ID, LocalDateTime.now(), DEVICE_NUMBER, measurement);

        DeviceDto dto = DeviceMapper.INSTANCE.deviceToDeviceDto(device);
        assertEquals(ID, dto.getId());
        assertEquals(DEVICE_NUMBER, dto.getDeviceNumber());

        Measurement measurementDto = device.getMeasurement();
        assertEquals(MEASUREMENT_ID, measurementDto.getId());
        assertEquals(TIME_STAMP, measurementDto.getTimeStamp());
        assertEquals(LATITUDE, measurementDto.getLatitude());
        assertEquals(LONGITUDE, measurementDto.getLongitude());
        assertTrue(measurementDto.isAlert());
    }
}
