package com.monitoring.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
@Entity
public class Car {
    @Id
    private UUID id;
    private LocalDateTime timeStamp;
    private String stateSign;
    private String brand;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;

    @PrePersist
    void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
