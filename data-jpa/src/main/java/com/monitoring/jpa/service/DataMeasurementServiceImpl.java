package com.monitoring.jpa.service;

import com.monitoring.jpa.entity.Measurement;
import com.monitoring.jpa.mapper.MeasurementMapper;
import com.monitoring.jpa.repository.DeviceRepository;
import com.monitoring.jpa.repository.MeasurementRepository;
import com.monitoring.model.MeasurementDto;
import com.monitoring.service.DataMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DataMeasurementServiceImpl implements DataMeasurementService {
    private static final MeasurementMapper MAPPER = MeasurementMapper.INSTANCE;

    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;

    @Override
    public MeasurementDto create(MeasurementDto locationDto) {
        Measurement measurement = measurementRepository
                .save(MAPPER.toMeasurement(locationDto));
        deviceRepository.findByDeviceNumber(measurement.getDeviceNumber())
                .ifPresent(device -> device.setMeasurement(measurement));
        return MAPPER.toMeasurementDto(measurement);
    }

    @Override
    public Optional<MeasurementDto> findById(UUID uuid) {
        return measurementRepository.findById(uuid).map(MeasurementMapper.INSTANCE::toMeasurementDto);
    }
}
