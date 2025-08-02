package com.monitoring.crud.repository;

import com.monitoring.jpa.CrudApplication;
import com.monitoring.jpa.entity.Measurement;
import com.monitoring.jpa.repository.MeasurementRepository;
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
public class MeasurementRepositoryTest {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DataMeasurementService dataMeasurementService;

    @Test
    void specificationTest() {

        MeasurementDto dto = MeasurementDto.builder()
                .deviceNumber("APU_REST")
                .longitude(10D)
                .latitude(20D)
                .alert(true)
                .build();
        dataMeasurementService.create(dto);

        List<Measurement> measurements = measurementRepository.findAll(MeasurementRepository.Spec.byDeviceNumber("APU_REST"));
        assertTrue(!measurements.isEmpty());

        measurements = measurementRepository.findAll(MeasurementRepository.Spec
                .byTimeStampGreaterThen(LocalDateTime.now().minusDays(1)));
        assertTrue(!measurements.isEmpty());

        measurements = measurementRepository.findAll(MeasurementRepository.Spec
                .byTimeStampLessThen(LocalDateTime.now().plusDays(1)));
        assertTrue(!measurements.isEmpty());
    }
}
