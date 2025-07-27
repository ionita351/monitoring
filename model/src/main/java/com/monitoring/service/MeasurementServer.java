package com.monitoring.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.Response;

import java.util.List;

public interface MeasurementServer {
    Response receiveOne(MeasurementDto measurement);
    Response receiveMany(List<MeasurementDto> measurements);
}
