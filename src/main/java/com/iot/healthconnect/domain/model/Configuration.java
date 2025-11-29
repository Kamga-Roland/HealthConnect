package com.iot.healthconnect.domain.model;

public class Configuration {
    private Long id;

    private String parameter; // e.g., max_systolic
    private String value;     // threshold value
    private String unit;

    private Device device;
}
