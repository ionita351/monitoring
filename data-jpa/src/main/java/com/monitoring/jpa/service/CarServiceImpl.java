package com.monitoring.jpa.service;

import com.monitoring.jpa.mapper.CarMapper;
import com.monitoring.jpa.repository.CarRepository;
import com.monitoring.model.CarDto;
import com.monitoring.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {
    private static final CarMapper MAPPER = CarMapper.INSTANCE;

    private final CarRepository carRepository;

    @Override
    public Optional<CarDto> findById(UUID id) {
        return carRepository.findById(id).map(MAPPER::carToCarDto);
    }

    @Override
    public CarDto create(CarDto car) {
        return MAPPER.carToCarDto(carRepository.save(MAPPER.carDtoToCar(car)));
    }
}
