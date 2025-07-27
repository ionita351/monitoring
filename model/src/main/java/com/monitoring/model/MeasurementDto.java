package com.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeasurementDto {
    private UUID id;
    private String deviceNumber;
    private LocalDateTime timeStamp;
    private Double latitude;
    private Double longitude;
    private boolean alert;
}
