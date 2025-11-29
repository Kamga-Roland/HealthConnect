package com.iot.healthconnect.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String role; // patient, clinician, admin
        private String email;
        private String passwordHash;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<DeviceEntity> devices;

}
