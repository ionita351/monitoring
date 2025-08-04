package com.monitoring.service;

import com.monitoring.model.CarDto;
import com.monitoring.model.CarDtoRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DataCarService {
    CarDto create(CarDto car);
    Optional<CarDto> findById(UUID id);
    Optional<CarDto> findByDeviceNumber(String deviceNumber);
    List<CarDto> findAllByRequest(CarDtoRequest request);
}
