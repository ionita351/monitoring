package com.monitoring.server.reactive.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.MeasurementDtoRequest;
import com.monitoring.model.ResponseDto;
import com.monitoring.server.reactive.mapper.MeasurementMapper;
import com.monitoring.server.reactive.repository.R2MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    private static final MeasurementMapper MAPPER = MeasurementMapper.INSTANCE;
    private final R2MeasurementRepository measurementRepository;

    @Override
    public Mono<ResponseDto> receiveOne(MeasurementDto dto) {
        return measurementRepository.save(MAPPER.toMeasurement(dto))
                .doOnError(e -> ResponseDto.error(e.getMessage()))
                .then(Mono.just(ResponseDto.success()));
    }

    @Override
    public Mono<ResponseDto> receiveMany(List<MeasurementDto> dtos) {
        return measurementRepository.saveAll(dtos.stream().map(MAPPER::toMeasurement).toList())
                .doOnError(e -> ResponseDto.error(e.getMessage()))
                .then(Mono.just(ResponseDto.success()));
    }

    @Override
    public Mono<MeasurementDto> findById(UUID id) {
        return measurementRepository.findById(id).map(MAPPER::toMeasurementDto);
    }

    @Override
    public Flux<MeasurementDto> findAllByRequest(MeasurementDtoRequest request) {
        return null;
    }
}
