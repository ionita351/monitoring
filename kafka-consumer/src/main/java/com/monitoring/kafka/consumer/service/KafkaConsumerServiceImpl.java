package com.monitoring.kafka.consumer.service;

import com.monitoring.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl {
    private final KafkaConsumerService kafkaConsumerService;

    @KafkaListener(topics = "${kafka.topic:measurement}", groupId = "measurement")
    public void consume(MeasurementDto message) {
        kafkaConsumerService.receive(message);
    }
}
