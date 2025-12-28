package com.iot.healthconnect.repository;

import com.iot.healthconnect.entity.DeviceSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceSettingsRepository extends JpaRepository<DeviceSettings, Long> {
    DeviceSettings findByDeviceName(String name);
}
