package com.iot.healthconnect.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "readings")
public class ReadingEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime timestamp;
        private Double value1; // e.g., systolic, glucose, weight
        private Double value2; // e.g., diastolic, HR
        private Double value3; // optional extra metric
        private String unit;

        @ManyToOne
        @JoinColumn(name = "device_id")
        private DeviceEntity device;

}
