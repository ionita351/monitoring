package com.monitoring.service;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;

import java.util.List;

public interface MeasurementClient {
    ResponseDto sendOne(MeasurementDto measurement);
    ResponseDto sendMany(List<MeasurementDto> measurements);
}
