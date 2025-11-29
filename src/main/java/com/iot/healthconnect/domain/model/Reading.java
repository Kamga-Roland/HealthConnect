package com.iot.healthconnect.domain.model;

import java.time.LocalDateTime;

public class Reading {
    private Long id;

    private LocalDateTime timestamp;
    private Double value1; // e.g., systolic, glucose, weight
    private Double value2; // e.g., diastolic, HR
    private Double value3; // optional extra metric
    private String unit;

    private Device device;
}
