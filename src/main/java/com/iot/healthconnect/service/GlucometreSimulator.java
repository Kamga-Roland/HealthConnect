package com.iot.healthconnect.service;

import com.iot.healthconnect.entity.DeviceSettings;
import com.iot.healthconnect.model.Glucometre;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GlucometreSimulator implements DataSimulator {

    private final Glucometre device;
    private final DeviceSettings settings;
    private final Random random = new Random();
    private Timer timer;

    public GlucometreSimulator(Glucometre device, DeviceSettings settings) {
        this.device = device;
        this.settings = settings;
    }

    @Override
    public void start() {
        timer = new Timer();

        int interval = settings.getSimulationInterval() != null ? settings.getSimulationInterval() : 4000;

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double glucose = 70 + random.nextDouble() * 100; // 70–170 mg/dL
                device.setGlucose(glucose);
            }
        }, 0, interval);
    }

    @Override
    public void stop() {
        if (timer != null) timer.cancel();
    }
}
