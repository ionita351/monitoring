package com.monitoring.server.reactive.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Device {
    @Id
    private UUID id;
    private LocalDateTime timeStamp;
    private String deviceNumber;
    private UUID measurement_id;
}
