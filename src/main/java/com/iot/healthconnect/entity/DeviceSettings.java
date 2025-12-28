package com.iot.healthconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DeviceSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;
    private String deviceType;

    // Alarm thresholds
    private Double minValue;
    private Double maxValue;

    // Simulation speed (ms)
    private Integer simulationInterval;

    // UI theme
    private String colorTheme;

    // Icon name
    private String iconName;
}
