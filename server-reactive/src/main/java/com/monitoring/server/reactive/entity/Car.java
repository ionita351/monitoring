package com.monitoring.server.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Car {
    @Id
    private UUID id;
    private LocalDateTime timeStamp;
    private String stateSign;
    private String brand;
    private Device device;
}
