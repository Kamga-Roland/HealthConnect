package com.iot.healthconnect.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarms")
public class AlarmEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String severity; // INFO, WARNING, CRITICAL
        private String message;
        private boolean acknowledged;
        private LocalDateTime timestamp;

        @ManyToOne
        @JoinColumn(name = "device_id")
        private DeviceEntity device;

        @ManyToOne
        @JoinColumn(name = "reading_id", nullable = true)
        private ReadingEntity reading; // optional link to reading
}
