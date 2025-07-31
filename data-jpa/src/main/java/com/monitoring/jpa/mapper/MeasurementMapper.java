package com.monitoring.jpa.mapper;

import com.monitoring.jpa.entity.Measurement;
import com.monitoring.model.MeasurementDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeasurementMapper {

    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    MeasurementDto toMeasurementDto(Measurement location);
    Measurement toMeasurement(MeasurementDto locationDto);
}
