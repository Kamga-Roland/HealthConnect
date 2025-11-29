package com.iot.healthconnect.domain.model;

import java.util.List;

public class User {
    private Long id;

    private String name;
    private String role; // patient, clinician, admin
    private String email;
    private String passwordHash;
    private List<Device> devices;
}
