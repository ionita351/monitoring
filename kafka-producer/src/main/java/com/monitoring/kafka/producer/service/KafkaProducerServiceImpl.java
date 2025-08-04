package com.monitoring.kafka.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl<T> implements KafkaProducerService<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;

    @Value("${kafka.topic:measurement}")
    public String topic;

    @Override
    public void send(String key, T message) {
        kafkaTemplate.send(topic, key, message);
    }
}
