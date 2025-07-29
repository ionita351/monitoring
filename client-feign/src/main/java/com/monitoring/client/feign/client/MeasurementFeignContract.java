package com.monitoring.client.feign.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("SERVER-REST")
public interface MeasurementFeignContract {

    @PostMapping(value = "/measurement", produces = "application/json", consumes = "application/json")
    ResponseEntity<ResponseDto> sendOne(MeasurementDto measurement);

    @PostMapping(value = "/measurement/list", produces = "application/json", consumes = "application/json")
    ResponseEntity<ResponseDto> sendMany(List<MeasurementDto> measurements);
}
