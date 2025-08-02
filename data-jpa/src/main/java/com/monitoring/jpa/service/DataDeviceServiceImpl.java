package com.monitoring.jpa.service;

import com.monitoring.jpa.entity.Device;
import com.monitoring.jpa.mapper.DeviceMapper;
import com.monitoring.jpa.repository.CarRepository;
import com.monitoring.jpa.repository.DeviceRepository;
import com.monitoring.model.DeviceDto;
import com.monitoring.model.DeviceDtoRequest;
import com.monitoring.service.DataDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DataDeviceServiceImpl implements DataDeviceService {
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

    public List<DeviceDto> findAllByRequest(DeviceDtoRequest request) {
        List<Specification<Device>> specifications = new ArrayList<>();
        if (request != null) {
            if (request.getDeviceNumber() != null) {
                specifications.add(DeviceRepository.Spec.byDeviceNumber(request.getDeviceNumber()));
            }
            if (request.getTimeStampFrom() != null) {
                specifications.add(DeviceRepository.Spec.byTimeStampGreaterThen(request.getTimeStampFrom()));
            }
            if (request.getTimeStampTo() != null) {
                specifications.add(DeviceRepository.Spec.byTimeStampLessThen(request.getTimeStampTo()));
            }
        }
        return deviceRepository.findAll(specifications.isEmpty()
                ? null
                : Specification.allOf(specifications)).stream()
                .map(MAPPER::deviceToDeviceDto)
                .toList();

    }
}
