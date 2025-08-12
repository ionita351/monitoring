package com.monitoring.server.reactive.controller;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.server.reactive.service.MeasurementService;
import com.monitoring.service.MeasurementServer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;

    @RequestMapping("/")
    public Mono<ResponseDto> receiveOne(@RequestBody MeasurementDto measurement) {
        return measurementService.receiveOne(measurement);
    }

    @RequestMapping("/list")
    public Mono<ResponseDto> receiveMany(@RequestBody List<MeasurementDto> measurements) {
        return measurementService.receiveMany(measurements);
    }
}
