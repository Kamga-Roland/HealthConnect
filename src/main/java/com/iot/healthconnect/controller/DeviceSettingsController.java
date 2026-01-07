package com.iot.healthconnect.controller;

import com.iot.healthconnect.entity.DeviceSettings;
import com.iot.healthconnect.service.SettingsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class DeviceSettingsController {

    private final SettingsService settingsService;

    public DeviceSettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/{deviceName}")
    public DeviceSettings getSettings(@PathVariable String deviceName) {
        return settingsService.loadSettingsByName(deviceName);
    }

    @PutMapping("/{deviceName}")
    public DeviceSettings updateSettings(@PathVariable String deviceName,
                                         @RequestBody DeviceSettings newSettings) {

        DeviceSettings existing = settingsService.loadSettingsByName(deviceName);
        if (existing == null) {
            newSettings.setDeviceName(deviceName);
            return settingsService.saveSettings(newSettings);
        }

        existing.setMinValue(newSettings.getMinValue());
        existing.setMaxValue(newSettings.getMaxValue());
        existing.setSimulationInterval(newSettings.getSimulationInterval());
        existing.setColorTheme(newSettings.getColorTheme());
        existing.setIconName(newSettings.getIconName());

        return settingsService.saveSettings(existing);
    }
}
