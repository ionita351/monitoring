package com.monitoring.client.rest.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
public class MeasurementRestClient implements MeasurementClient {
    private static final RestClient CLIENT = RestClient.builder()
            .baseUrl("http://localhost:9090/measurement")
            .defaultHeader("content-type", "application/json")
            .defaultHeader("accept", "application/json")
            .build();

    @Override
    public ResponseDto sendOne(MeasurementDto measurement) {
        ResponseEntity<ResponseDto> responseEntity = CLIENT.post()
                .body(measurement)
                .retrieve()
                .toEntity(ResponseDto.class);
        return extractBodyFromResponseEntity(responseEntity);
    }

    @Override
    public ResponseDto sendMany(List<MeasurementDto> measurements) {
        ResponseEntity<ResponseDto> responseEntity = CLIENT.post()
                .uri("/list")
                .body(measurements)
                .retrieve()
                .toEntity(ResponseDto.class);
        return extractBodyFromResponseEntity(responseEntity);
    }

    private static ResponseDto extractBodyFromResponseEntity(ResponseEntity<ResponseDto> responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseDto.error("Http return code: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
