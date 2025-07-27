package com.monitoring.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;

import java.util.List;

public interface MeasurementServer {
    ResponseDto receiveOne(MeasurementDto measurement);
    ResponseDto receiveMany(List<MeasurementDto> measurements);
}
