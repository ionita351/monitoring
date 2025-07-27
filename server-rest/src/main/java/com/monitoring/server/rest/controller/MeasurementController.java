package com.monitoring.server.rest.controller;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.Response;
import com.monitoring.service.MeasurementServer;
import com.monitoring.service.MeasurementService;
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
    public Response receiveOne(@RequestBody MeasurementDto measurement) {
        try {
            measurementService.create(measurement);
            return Response.success();
        } catch (Exception ex) {
            return Response.error(ex.getMessage());
        }
    }

    @PostMapping("/list")
    @Override
    public Response receiveMany(@RequestBody List<MeasurementDto> measurements) {
        try {
            measurements.forEach(measurementService::create);
            return Response.success();
        } catch (Exception ex) {
            return Response.error(ex.getMessage());
        }
    }
}
