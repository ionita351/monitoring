package com.monitoring.service;

import com.monitoring.model.MeasurementDto;

import java.util.Optional;
import java.util.UUID;

public interface DataMeasurementService {
    MeasurementDto create(MeasurementDto measurement);
    Optional<MeasurementDto> findById(UUID uuid);
}
