package com.monitoring.kafka.producer.service;

import com.monitoring.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, MeasurementDto> kafkaTemplate;

    @Override
    public void send(String topic, MeasurementDto message) {
        try {
            kafkaTemplate.send(topic, message);
        } catch (Throwable throwable) {
            log.error("Sending to KAFKA error: " + throwable.getLocalizedMessage());
        }
    }
}
