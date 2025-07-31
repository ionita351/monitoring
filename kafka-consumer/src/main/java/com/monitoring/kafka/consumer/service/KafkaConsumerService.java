package com.monitoring.kafka.consumer.service;

import com.monitoring.model.MeasurementDto;

public interface KafkaConsumerService {

    void receive(MeasurementDto dto);
}
