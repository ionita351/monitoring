package com.monitoring.service;

import com.monitoring.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImp implements MeasurementService {
    private final DataMeasurementService dataMeasurementService;

    @Override
    public MeasurementDto create(MeasurementDto measurement) {
        return dataMeasurementService.create(measurement);
    }
}
