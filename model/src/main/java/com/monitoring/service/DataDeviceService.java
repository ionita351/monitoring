package com.monitoring.service;

import com.monitoring.model.DeviceDto;

import java.util.Optional;
import java.util.UUID;

public interface DataDeviceService {
    Optional<DeviceDto> findById(UUID id);
    DeviceDto create(DeviceDto device);
}
