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
public class CarDto {
    private UUID id;
    private String stateSign;
    private String brand;
    private DeviceDto device;
}
