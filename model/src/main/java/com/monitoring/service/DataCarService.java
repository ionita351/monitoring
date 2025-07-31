package com.monitoring.service;

import com.monitoring.model.CarDto;

import java.util.Optional;
import java.util.UUID;

public interface DataCarService {
    Optional<CarDto> findById(UUID id);
    CarDto create(CarDto car);
}
