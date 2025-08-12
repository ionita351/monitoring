package com.monitoring.server.reactive.repository;

import com.monitoring.server.reactive.entity.Car;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface R2CarRepository extends R2dbcRepository<Car, UUID> {
}
