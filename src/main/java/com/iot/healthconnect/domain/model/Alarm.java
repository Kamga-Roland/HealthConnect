package com.iot.healthconnect.domain.model;

import java.time.LocalDateTime;

public class Alarm {
    private Long id;

    private String severity; // INFO, WARNING, CRITICAL
    private String message;
    private boolean acknowledged;
    private LocalDateTime timestamp;
    private Device device;
    private Reading reading; // optional link to reading
}
