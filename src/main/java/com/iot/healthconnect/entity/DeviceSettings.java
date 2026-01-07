package com.iot.healthconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "device_settings")
public class DeviceSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String deviceName;

    private String deviceType;

    private Double minValue;
    private Double maxValue;

    private Integer simulationInterval;

    private String colorTheme;

    private String iconName;
}
