package com.monitoring.stomp.service.service;

import com.monitoring.kafka.consumer.service.KafkaConsumerService;
import com.monitoring.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListenerService implements KafkaConsumerService<MeasurementDto> {
    private final SimpMessageSendingOperations messagingTemplate;


    @Override
    public void receive(MeasurementDto dto) {
        log.info("Have got message: " + dto);
        messagingTemplate.convertAndSend("/topic/measurements", dto);
    }
}
