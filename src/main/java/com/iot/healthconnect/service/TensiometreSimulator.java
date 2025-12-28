package com.iot.healthconnect.service;

import com.iot.healthconnect.model.Tensiometre;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TensiometreSimulator implements DataSimulator {

    private final Tensiometre device;
    private final Random random = new Random();
    private Timer timer;

    public TensiometreSimulator(Tensiometre device) {
        this.device = device;
    }

    @Override
    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int sys = 100 + random.nextInt(60);  // 100–160
                int dia = 60 + random.nextInt(40);   // 60–100
                device.setValues(sys, dia);
            }
        }, 0, 3000); // toutes les 3 secondes
    }

    @Override
    public void stop() {
        if (timer != null) timer.cancel();
    }
}
