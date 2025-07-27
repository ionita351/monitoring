package com.monitoring.jpa.service;

import com.monitoring.jpa.mapper.DeviceMapper;
import com.monitoring.jpa.repository.DeviceRepository;
import com.monitoring.model.DeviceDto;
import com.monitoring.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private DeviceMapper MAPPER = DeviceMapper.INSTANCE;

    @Override
    public Optional<DeviceDto> findById(UUID id) {
        return deviceRepository.findById(id).map(MAPPER::deviceToDeviceDto);
    }

    @Override
    public DeviceDto create(DeviceDto device) {
        return MAPPER.deviceToDeviceDto(deviceRepository.save(MAPPER.deviceDtoToDevice(device)));
    }
}
