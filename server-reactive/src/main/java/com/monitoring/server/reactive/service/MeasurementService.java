package com.monitoring.server.reactive.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.MeasurementDtoRequest;
import com.monitoring.model.ResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface MeasurementService {
    Mono<ResponseDto> receiveOne(MeasurementDto measurement);
    Mono<ResponseDto> receiveMany(List<MeasurementDto> measurements);

    Mono<MeasurementDto> create(MeasurementDto measurement);
    Mono<MeasurementDto> findById(UUID id);
    Flux<MeasurementDto> findAllByRequest(MeasurementDtoRequest request);
}
