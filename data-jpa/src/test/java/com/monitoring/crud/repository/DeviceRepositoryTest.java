package com.monitoring.crud.repository;

import com.monitoring.jpa.CrudApplication;
import com.monitoring.jpa.entity.Device;
import com.monitoring.jpa.repository.DeviceRepository;
import com.monitoring.model.MeasurementDto;
import com.monitoring.service.DataMeasurementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CrudApplication.class)
public class DeviceRepositoryTest {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DataMeasurementService dataMeasurementService;

    @Test
    void specificationTest() {

        Optional<Device> device = deviceRepository.findOne(DeviceRepository.Spec.byDeviceNumber("APU_REST"));
        assertTrue(device.isPresent());
        assertEquals("APU_REST", device.get().getDeviceNumber());

        MeasurementDto dto = MeasurementDto.builder()
                .deviceNumber("APU_REST")
                .longitude(10D)
                .latitude(20D)
                .alert(true)
                .build();
        dataMeasurementService.create(dto);

        List<Device> devices = deviceRepository.findAll(DeviceRepository.Spec
                .byTimeStampGreaterThen(LocalDateTime.now().minusDays(1)));
        assertTrue(!devices.isEmpty());

        devices = deviceRepository.findAll(DeviceRepository.Spec
                .byTimeStampLessThen(LocalDateTime.now().plusDays(1)));
        assertTrue(!devices.isEmpty());
    }
}
