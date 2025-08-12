package com.monitoring.crud.mapper;

import com.monitoring.jpa.entity.Car;
import com.monitoring.jpa.entity.Device;
import com.monitoring.jpa.entity.Measurement;
import com.monitoring.jpa.mapper.CarMapper;
import com.monitoring.model.CarDto;
import com.monitoring.model.DeviceDto;
import com.monitoring.model.MeasurementDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarMapperTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String STATE_SIGN = "AA 178 BB";
    private static final String BRAND = "VOLGA";

    private static final UUID DEVICE_ID = UUID.randomUUID();
    private static final String DEVICE_NUMBER = "123";

    private static final UUID MEASUREMENT_ID = UUID.randomUUID();
    private static final LocalDateTime TIME_STAMP = LocalDateTime.now();
    private static final Double LATITUDE = 1D;
    private static final Double LONGITUDE = 2D;
    private static final boolean ALERT = true;

    @Test
    void carDtoToCarTest() {
        MeasurementDto measurementDto = new MeasurementDto(MEASUREMENT_ID, DEVICE_NUMBER, TIME_STAMP, LATITUDE, LONGITUDE, ALERT);
        DeviceDto deviceDto = new DeviceDto(DEVICE_ID, DEVICE_NUMBER, measurementDto);
        CarDto dto = new CarDto(ID, STATE_SIGN, BRAND, deviceDto);

        Car car = CarMapper.INSTANCE.carDtoToCar(dto);
        assertEquals(ID, car.getId());
        assertEquals(STATE_SIGN, car.getStateSign());
        assertEquals(BRAND, car.getBrand());

        Device device = car.getDevice();
        assertEquals(DEVICE_ID, device.getId());
        assertEquals(DEVICE_NUMBER, device.getDeviceNumber());

        Measurement measurement = device.getMeasurement();
        assertEquals(MEASUREMENT_ID, measurement.getId());
        assertEquals(TIME_STAMP, measurement.getTimeStamp());
        assertEquals(LATITUDE, measurement.getLatitude());
        assertEquals(LONGITUDE, measurement.getLongitude());
        assertTrue(measurement.isAlert());
    }

    @Test
    void carToCarDtoTest() {
        Measurement measurement = new Measurement(MEASUREMENT_ID, DEVICE_NUMBER, TIME_STAMP, LATITUDE, LONGITUDE, ALERT);
        Device device = new Device(DEVICE_ID, LocalDateTime.now(), DEVICE_NUMBER, measurement);
        Car car = new Car(ID, LocalDateTime.now(), STATE_SIGN, BRAND, device);

        CarDto dto = CarMapper.INSTANCE.carToCarDto(car);
        assertEquals(ID, dto.getId());
        assertEquals(STATE_SIGN, dto.getStateSign());
        assertEquals(BRAND, dto.getBrand());

        DeviceDto deviceDto = dto.getDevice();
        assertEquals(DEVICE_ID, deviceDto.getId());
        assertEquals(DEVICE_NUMBER, deviceDto.getDeviceNumber());

        Measurement measurementDto = device.getMeasurement();
        assertEquals(MEASUREMENT_ID, measurementDto.getId());
        assertEquals(TIME_STAMP, measurementDto.getTimeStamp());
        assertEquals(LATITUDE, measurementDto.getLatitude());
        assertEquals(LONGITUDE, measurementDto.getLongitude());
        assertTrue(measurementDto.isAlert());
    }
}
