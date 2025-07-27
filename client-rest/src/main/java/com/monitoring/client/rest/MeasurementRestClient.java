package com.monitoring.client.rest;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.Response;
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
    public Response sendOne(MeasurementDto measurement) {
        ResponseEntity<Response> responseEntity = CLIENT.post()
                .body(measurement)
                .retrieve()
                .toEntity(Response.class);
        return extractBodyFromResponseEntity(responseEntity);
    }

    @Override
    public Response sendMany(List<MeasurementDto> measurements) {
        ResponseEntity<Response> responseEntity = CLIENT.post()
                .uri("/list")
                .body(measurements)
                .retrieve()
                .toEntity(Response.class);
        return extractBodyFromResponseEntity(responseEntity);
    }

    private static Response extractBodyFromResponseEntity(ResponseEntity<Response> responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return Response.error("Http return code: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
