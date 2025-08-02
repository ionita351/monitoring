package com.monitoring.service;

import com.monitoring.model.CarDto;
import com.monitoring.model.CarDtoRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DataCarService {
    Optional<CarDto> findById(UUID id);
    CarDto create(CarDto car);
    List<CarDto> findAllByRequest(CarDtoRequest request);
}
