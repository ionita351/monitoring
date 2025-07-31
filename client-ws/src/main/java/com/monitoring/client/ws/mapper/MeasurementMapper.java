package com.monitoring.client.ws.mapper;

import com.monitoring.model.MeasurementDto;
import com.monitoring.ws.wsdl.WsMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeasurementMapper {
    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    WsMeasurement toWsMeasurement(MeasurementDto dto);
    MeasurementDto toMeasurementDto(WsMeasurement wsMeasurement);
}