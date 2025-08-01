package com.monitoring.kafka.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaListenerService<T> {
    private final KafkaConsumerService<T> kafkaConsumerService;

    @KafkaListener(topics = "${kafka.topic:measurement}", groupId = "measurement")
    public void consume(T message) {
        kafkaConsumerService.receive(((ConsumerRecord<String, T>) message).value());
    }
}
