package com.monitoring.client.reactive.client;

import com.monitoring.model.MeasurementDto;
import com.monitoring.model.ResponseDto;
import com.monitoring.service.MeasurementClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeasurementReactiveClient implements MeasurementClient {
    private final WebClient.Builder webClientBuilder;

    @Override
    public ResponseDto sendOne(MeasurementDto measurement) {
        return sendData(measurement);
    }

    @Override
    public ResponseDto sendMany(List<MeasurementDto> measurements) {
        return sendData(measurements);
    }

    private <T> ResponseDto sendData(T measurements) {
        return (ResponseDto) webClientBuilder.build().post()
                .body(BodyInserters.fromValue(measurements))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(ResponseDto.class);
                    } else {
                        return Mono.just(ResponseDto.error("Response status code: " + clientResponse.statusCode()));
                    }
                }).block();
    }
}
