package com.monitoring.server.rest.controller;

import com.monitoring.common.service.MeasurementService;
import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementServer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measurement")
@RequiredArgsConstructor
public class MeasurementController implements MeasurementServer {
    private final MeasurementService measurementService;

    @PostMapping
    @Override
    public ResponseDto receiveOne(@RequestBody MeasurementDto measurement) {
        try {
            measurementService.create(measurement);
            return ResponseDto.success();
        } catch (Exception ex) {
            return ResponseDto.error(ex.getMessage());
        }
    }

    @PostMapping("/list")
    @Override
    public ResponseDto receiveMany(@RequestBody List<MeasurementDto> measurements) {
        try {
            measurements.forEach(measurementService::create);
            return ResponseDto.success();
        } catch (Exception ex) {
            return ResponseDto.error(ex.getMessage());
        }
    }
}
