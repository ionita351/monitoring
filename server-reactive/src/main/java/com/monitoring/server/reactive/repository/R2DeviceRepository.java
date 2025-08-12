package com.monitoring.server.reactive.repository;

import com.monitoring.server.reactive.entity.Device;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface R2DeviceRepository extends R2dbcRepository<Device, UUID> {
}
