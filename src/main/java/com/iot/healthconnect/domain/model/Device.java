package com.iot.healthconnect.domain.model;

import java.util.List;

public class Device {

    private Long id;

    private String name;
    private String type; // bp_monitor, glucometer, etc.
    private String status; // connected/disconnected
    private boolean subscriptionActive;
    private int samplingInterval;

    private User user;

    private List<Reading> readings;

    private List<Alarm> alarms;

    private List<Configuration> configurations;
}
