package com.monitoring.client.rest.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeasurementRestClient implements MeasurementClient {
    private final DiscoveryClient eurekaClient;

    private static final RestClient CLIENT = RestClient.builder()
            .baseUrl("http://localhost:9090/measurement")
            .defaultHeader("content-type", "application/json")
            .defaultHeader("accept", "application/json")
            .build();

    @Override
    public ResponseDto sendOne(MeasurementDto measurement) {
        return send("/measurement", measurement);
    }

    @Override
    public ResponseDto sendMany(List<MeasurementDto> measurements) {
        return send("/measurement/list", measurements);
    }

    private <T> ResponseDto send(String uri, T body) {
        try {
            ServiceInstance instance = eurekaClient.getInstances("server-rest")
                    .stream().findAny().orElseThrow(() -> new IllegalStateException(""));
            ResponseEntity<ResponseDto> responseEntity = CLIENT.post()
                    .uri(instance.getUri() + uri)
                    .body(body)
                    .retrieve()
                    .toEntity(ResponseDto.class);
            return extractBodyFromResponseEntity(responseEntity);
        } catch (Throwable throwable) {
            return ResponseDto.error(throwable.getMessage());
        }
    }

    private static ResponseDto extractBodyFromResponseEntity(ResponseEntity<ResponseDto> responseEntity) {
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseDto.error("Http return code: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
