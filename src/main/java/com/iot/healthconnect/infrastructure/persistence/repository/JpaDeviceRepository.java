package com.iot.healthconnect.infrastructure.persistence.repository;

import com.iot.healthconnect.infrastructure.persistence.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDeviceRepository extends JpaRepository<DeviceEntity,Long> {
}
