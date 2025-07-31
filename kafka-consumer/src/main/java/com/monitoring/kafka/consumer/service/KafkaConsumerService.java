package com.monitoring.kafka.consumer.service;

public interface KafkaConsumerService<T> {
    void receive(T dto);
}
