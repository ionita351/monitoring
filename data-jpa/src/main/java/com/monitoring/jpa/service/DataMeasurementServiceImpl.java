package com.monitoring.jpa.service;

import com.monitoring.jpa.entity.Measurement;
import com.monitoring.jpa.mapper.MeasurementMapper;
import com.monitoring.jpa.repository.DeviceRepository;
import com.monitoring.jpa.repository.MeasurementRepository;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.MeasurementDtoRequest;
import com.monitoring.service.DataMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<MeasurementDto> findAllByRequest(MeasurementDtoRequest request) {
        List<Specification<Measurement>> specifications = new ArrayList<>();
        if (request != null) {
            if (request.getDeviceNumber() != null) {
                specifications.add(MeasurementRepository.Spec.byDeviceNumber(request.getDeviceNumber()));
            }
            if (request.getTimeStampFrom() != null) {
                specifications.add(MeasurementRepository.Spec.byTimeStampGreaterThen(request.getTimeStampFrom()));
            }
            if (request.getTimeStampTo() != null) {
                specifications.add(MeasurementRepository.Spec.byTimeStampLessThen(request.getTimeStampTo()));
            }
        }
        return measurementRepository.findAll(specifications.isEmpty()
                        ? null
                        : Specification.allOf(specifications)).stream()
                .map(MAPPER::toMeasurementDto)
                .toList();
    }
}
