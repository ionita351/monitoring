package com.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MeasurementDto {
    private UUID id;
    private String deviceNumber;
    private LocalDateTime timeStamp;
    private Double latitude;
    private Double longitude;
    private boolean alert;
}
