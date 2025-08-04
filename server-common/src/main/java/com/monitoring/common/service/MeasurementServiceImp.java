package com.monitoring.common.service;

import com.monitoring.kafka.producer.service.KafkaProducerService;
import com.monitoring.model.CarDto;
import com.monitoring.model.MeasurementDto;
import com.monitoring.service.DataCarService;
import com.monitoring.service.DataMeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementServiceImp implements MeasurementService {
    private final DataMeasurementService dataMeasurementService;
    private final DataCarService dataCarService;
    private final KafkaProducerService<CarDto> kafkaProducerService;

    private final Map<String, MeasurementDto> currentValues = new ConcurrentHashMap<>();
    private final Map<String, UUID> sentMeasurements = new HashMap<>();

    @Override
    public MeasurementDto create(MeasurementDto measurement) {
        measurement =  dataMeasurementService.create(measurement);
        currentValues.put(measurement.getDeviceNumber(), measurement);
        return measurement;
    }

    @Scheduled(fixedDelay = 1000L)
    public void sendMessagesToKafka() {
        log.debug("Scheduled started at " + LocalDateTime.now());
        try {
            currentValues.values().forEach(e -> {
                UUID id = sentMeasurements.get(e.getDeviceNumber());
                if (id == null || !id.equals(e.getId())) {
                    sentMeasurements.put(e.getDeviceNumber(), e.getId());
                    Optional<CarDto> optCarDto = dataCarService.findByDeviceNumber(e.getDeviceNumber());
                    if (optCarDto.isPresent()) {
                        kafkaProducerService.send(e.getDeviceNumber(), optCarDto.get());
                    } else {
                        log.warn("Device number " + e.getDeviceNumber() + " isn't bound to car.");
                    }
                }
            });
        } catch (Throwable throwable) {
            log.error("Sending to KAFKA error: " + throwable.toString());
        }
        log.debug("Scheduled stoped ad " + LocalDateTime.now());
    }
}
