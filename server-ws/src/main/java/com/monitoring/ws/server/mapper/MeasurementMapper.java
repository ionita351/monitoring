package com.monitoring.ws.server.mapper;

import com.monitoring.model.MeasurementDto;
import monitoring.com.measurement.WsMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeasurementMapper {
    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    WsMeasurement toWsMeasurement(MeasurementDto dto);
    MeasurementDto toMeasurementDto(WsMeasurement wsMeasurement);
}
