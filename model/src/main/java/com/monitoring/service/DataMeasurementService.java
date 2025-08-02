package com.monitoring.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.MeasurementDtoRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DataMeasurementService {
    MeasurementDto create(MeasurementDto measurement);
    Optional<MeasurementDto> findById(UUID uuid);
    List<MeasurementDto> findAllByRequest(MeasurementDtoRequest request);
}
