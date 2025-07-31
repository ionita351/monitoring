package com.monitoring.kafka.producer.service;


public interface KafkaProducerService<T> {
    void send(String key, T message);
}
