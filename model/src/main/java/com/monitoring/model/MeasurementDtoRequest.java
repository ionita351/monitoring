package com.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MeasurementDtoRequest {
    private String deviceNumber;
    private LocalDateTime timeStampFrom;
    private LocalDateTime timeStampTo;
}
