package com.monitoring.server.grpc.converter;

import com.monitoring.model.MeasurementDto;
import com.monitoring.stub.Measurement;

public class MeasurementConverter {

    private MeasurementConverter() {}

    public static MeasurementDto toMeasurementDto(Measurement measurement) {
        MeasurementDto dto = new MeasurementDto();
        dto.setDeviceNumber(measurement.getDeviceNumber());
        dto.setLatitude(measurement.getLatitude());
        dto.setLongitude(measurement.getLongitude());
        dto.setAlert(measurement.getAlert());
        return dto;
    }
}
