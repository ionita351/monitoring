package com.monitoring.crud.repository;

import com.monitoring.jpa.CrudApplication;
import com.monitoring.jpa.entity.Car;
import com.monitoring.jpa.repository.CarRepository;
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
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DataMeasurementService dataMeasurementService;

    @Test
    void specificationTest() {
        Optional<Car> car = carRepository.findOne(CarRepository.Spec.byCarStateSign("001"));
        assertTrue(car.isPresent());
        assertEquals("001", car.get().getStateSign());

        List<Car> cars = carRepository.findAll(CarRepository.Spec.byBrand("REST"));
        assertTrue(!cars.isEmpty());
        assertEquals("REST", cars.get(0).getBrand());

        car = carRepository.findOne(CarRepository.Spec.byDeviceNumber("APU_REST"));
        assertTrue(car.isPresent());
        assertEquals("APU_REST", car.get().getDevice().getDeviceNumber());

        MeasurementDto dto = MeasurementDto.builder()
                .deviceNumber("APU_REST")
                .longitude(10D)
                .latitude(20D)
                .alert(true)
                .build();
        dataMeasurementService.create(dto);

        cars = carRepository.findAll(CarRepository.Spec.byTimeStampGreaterThen(LocalDateTime.now().minusDays(1)));
        assertTrue(!cars.isEmpty());

        cars = carRepository.findAll(CarRepository.Spec.byTimeStampLessThen(LocalDateTime.now().plusDays(1)));
        assertTrue(!cars.isEmpty());

    }
}
