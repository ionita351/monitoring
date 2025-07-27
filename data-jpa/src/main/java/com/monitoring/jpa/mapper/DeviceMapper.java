package com.monitoring.jpa.mapper;

import com.monitoring.jpa.entity.Device;
import com.monitoring.model.DeviceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceMapper {
    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    DeviceDto deviceToDeviceDto(Device device);
    Device deviceDtoToDevice(DeviceDto deviceDto);
}
