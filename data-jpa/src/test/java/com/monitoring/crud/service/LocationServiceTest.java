package com.monitoring.crud.service;

import com.monitoring.jpa.CrudApplication;
import com.monitoring.model.DeviceDto;
import com.monitoring.model.MeasurementDto;
import com.monitoring.service.DeviceService;
import com.monitoring.service.MeasurementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CrudApplication.class)
public class LocationServiceTest {
    private static final String DEVICE_NUMBER = "DN";
    private static final LocalDateTime TIME_STAMP = LocalDateTime.now();
    private static final Double LATITUDE = 1D;
    private static final Double LONGITUDE = 2D;
    private static final boolean ALERT = true;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private DeviceService deviceService;

    @Test
    void createTest() {
        DeviceDto deviceDto = new DeviceDto(null, DEVICE_NUMBER, null);
        deviceDto = deviceService.create(deviceDto);

        MeasurementDto dto = new MeasurementDto(null, DEVICE_NUMBER, TIME_STAMP, LATITUDE, LONGITUDE, ALERT);
        dto = measurementService.create(dto);

        assertNotNull(dto.getId());
        Optional<MeasurementDto> optionalMeasurement = measurementService.findById(dto.getId());
        assertTrue(optionalMeasurement.isPresent());

        Optional<DeviceDto> ddto = deviceService.findById(deviceDto.getId());
        assertTrue(ddto.isPresent());
        assertNotNull(ddto.get().getMeasurement());
    }
}
