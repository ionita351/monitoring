package com.monitoring.server.reactive.repository;

import com.monitoring.server.reactive.entity.Measurement;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface R2MeasurementRepository extends R2dbcRepository<Measurement, UUID> {
}
