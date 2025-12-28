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

    public DeviceSettings loadSettings(IoTDevice device) {
        DeviceSettings s = repo.findByDeviceName(device.getName());
        if (s == null) {
            s = new DeviceSettings();
            s.setDeviceName(device.getName());
            s.setDeviceType(device.getType());
            s.setSimulationInterval(3000);
            s.setColorTheme("light");
            s.setIconName("default");
            repo.save(s);
        }
        return s;
    }

    public void saveSettings(DeviceSettings settings) {
        repo.save(settings);
    }
}
