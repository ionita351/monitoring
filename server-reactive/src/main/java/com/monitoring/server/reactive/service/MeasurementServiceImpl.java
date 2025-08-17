package com.monitoring.server.reactive.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.MeasurementDtoRequest;
import com.monitoring.model.ResponseDto;
import com.monitoring.server.reactive.entity.Measurement;
import com.monitoring.server.reactive.mapper.MeasurementMapper;
import com.monitoring.server.reactive.repository.R2DeviceRepository;
import com.monitoring.server.reactive.repository.R2MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.domain.Sort.by;
import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    private static final MeasurementMapper MAPPER = MeasurementMapper.INSTANCE;
    private final R2MeasurementRepository measurementRepository;
    private final R2DeviceRepository deviceRepository;
    private final R2dbcEntityTemplate entityTemplate;

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
    public Mono<MeasurementDto> create(MeasurementDto measurement) {
        return measurementRepository.save(MAPPER.toMeasurement(measurement))
                .map(MAPPER::toMeasurementDto)
                .doOnNext(e -> deviceRepository.findByDeviceNumber(e.getDeviceNumber())
                    .map(d -> { d.setMeasurement_id(e.getId()); return d; })
                    .flatMap(deviceRepository::save)
                        .subscribe());
    }

    @Override
    public Mono<MeasurementDto> findById(UUID id) {
        return measurementRepository.findById(id).map(MAPPER::toMeasurementDto);
    }

    @Override
    public Flux<MeasurementDto> findAllByRequest(MeasurementDtoRequest request) {
        List<Criteria> querySteps = new ArrayList<>();
        if (request.getDeviceNumber() != null) {
            querySteps.add(where("device_number").is(request.getDeviceNumber()));
        }
        if (request.getTimeStampFrom() != null) {
            querySteps.add(where("time_stamp").greaterThan(request.getTimeStampFrom()));
        }
        if (request.getTimeStampTo() != null) {
            querySteps.add(where("time_stamp").lessThan(request.getTimeStampTo()));
        }
        Criteria criteria;
        if (querySteps.isEmpty()) {
            criteria = Criteria.empty();
        } else {
            Optional<Criteria> list = querySteps.stream()
                    .reduce((a, b) -> a == null ? b : a.and(b));
            criteria = list.get();
        }
        return entityTemplate.select(Measurement.class)
                .matching(query(criteria).sort(by("time_stamp")))
                .all()
                .map(MAPPER::toMeasurementDto);
    }
}
