package com.monitoring.kafka.producer.service;

import com.monitoring.model.MeasurementDto;
import org.springframework.stereotype.Service;

public interface KafkaProducerService {
    void send(String topic, MeasurementDto message);
}
