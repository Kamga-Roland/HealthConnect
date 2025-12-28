package com.iot.healthconnect.service;

import com.iot.healthconnect.model.Oxymetre;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class OxymetreSimulator implements DataSimulator {

    private final Oxymetre ox;
    private final Random random = new Random();
    private Timer timer;

    public OxymetreSimulator(Oxymetre ox) {
        this.ox = ox;
    }

    @Override
    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int spo2 = 90 + random.nextInt(10); // 90–99%
                ox.setSpo2(spo2);
            }
        }, 0, 4000);
    }

    @Override
    public void stop() {
        if (timer != null) timer.cancel();
    }
}

