package com.monitoring.service;

import com.monitoring.model.DeviceDto;
import com.monitoring.model.DeviceDtoRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DataDeviceService {
    Optional<DeviceDto> findById(UUID id);
    DeviceDto create(DeviceDto device);
    List<DeviceDto> findAllByRequest(DeviceDtoRequest request);
}
