package com.iot.healthconnect.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "configurations")
public class ConfigurationEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String parameter; // e.g., max_systolic
        private String value;     // threshold value
        private String unit;

        @ManyToOne
        @JoinColumn(name = "device_id")
        private DeviceEntity device;

}
