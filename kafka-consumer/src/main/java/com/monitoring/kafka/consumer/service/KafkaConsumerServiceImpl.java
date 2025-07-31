package com.monitoring.kafka.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl<T> {
    private final KafkaConsumerService<T> kafkaConsumerService;

    @KafkaListener(topics = "${kafka.topic:measurement}", groupId = "measurement")
    public void consume(T message) {
        kafkaConsumerService.receive(message);
    }
}
