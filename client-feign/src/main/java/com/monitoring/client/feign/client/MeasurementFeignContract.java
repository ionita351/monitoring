package com.monitoring.client.feign.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "measurementFeignClient", url = "http://localhost:9090/measurement")
public interface MeasurementFeignContract {

    @PostMapping(produces = "application/json", consumes = "application/json")
    ResponseEntity<Response> sendOne(MeasurementDto measurement);

    @PostMapping(value = "/list", produces = "application/json", consumes = "application/json")
    ResponseEntity<Response> sendMany(List<MeasurementDto> measurements);
}
