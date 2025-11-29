package com.iot.healthconnect.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "devices")
public class DeviceEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String type; // bp_monitor, glucometer, etc.
        private String status; // connected/disconnected
        private boolean subscriptionActive;
        private int samplingInterval;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;

        @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
        private List<ReadingEntity> readings;

        @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
        private List<AlarmEntity> alarms;

        @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
        private List<ConfigurationEntity> configurations;
}
