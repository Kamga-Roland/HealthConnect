package com.iot.healthconnect.service;

import com.iot.healthconnect.entity.DeviceSettings;
import com.iot.healthconnect.model.IoTDevice;
import com.iot.healthconnect.repository.DeviceSettingsRepository;
import org.springframework.stereotype.Service;
@Service
public class SettingsService {

    private final DeviceSettingsRepository repo;

    public SettingsService(DeviceSettingsRepository repo) {
        this.repo = repo;
    }

    // Création explicite des settings
    public DeviceSettings createSettingsWithThresholds(IoTDevice device, double min, double max) {
        DeviceSettings s = new DeviceSettings();
        s.setDeviceName(device.getName());
        s.setDeviceType(device.getType());
        s.setSimulationInterval(3000);
        s.setColorTheme("light");
        s.setIconName("default");
        s.setMinValue(min);
        s.setMaxValue(max);
        return repo.save(s);
    }

    // Chargement simple, sans création automatique
    public DeviceSettings loadSettings(IoTDevice device) {
        return repo.findByDeviceName(device.getName());
    }

    public DeviceSettings loadSettingsByName(String deviceName) {
        return repo.findByDeviceName(deviceName);
    }

    public DeviceSettings saveSettings(DeviceSettings settings) {
        return repo.save(settings);
    }
}
