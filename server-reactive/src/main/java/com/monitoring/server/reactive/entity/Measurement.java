package com.monitoring.server.reactive.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Measurement {
    @Id
    private UUID id;
    private String deviceNumber;
    private LocalDateTime timeStamp;
    private Double latitude;
    private Double longitude;
    private boolean alert;
}
