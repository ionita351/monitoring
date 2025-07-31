package com.monitoring.client.grpc.mapper;

import com.monitoring.model.MeasurementDto;
import com.monitoring.stub.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeasurementMapper {
    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    Measurement toMeasurement(MeasurementDto dto);
    MeasurementDto toMeasurementDto(Measurement measurement);
}
