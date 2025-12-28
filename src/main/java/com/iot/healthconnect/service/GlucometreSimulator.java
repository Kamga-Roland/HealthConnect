package com.iot.healthconnect.service;

import com.iot.healthconnect.model.Glucometre;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GlucometreSimulator implements DataSimulator {

    private final Glucometre device;
    private final Random random = new Random();
    private Timer timer;

    public GlucometreSimulator(Glucometre device) {
        this.device = device;
    }

    @Override
    public void start() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                double glucose = 70 + random.nextDouble() * 100; // 70–170 mg/dL
                device.setGlucose(glucose);
            }
        }, 0, 4000);
    }

    @Override
    public void stop() {
        if (timer != null) timer.cancel();
    }
}
