package com.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceDto {
    private UUID id;
    private String deviceNumber;
    private MeasurementDto measurement;
}
