package com.iot.healthconnect.model;

import lombok.Data;

@Data
public class Oxymetre extends IoTDevice {

    private int spo2;

    public Oxymetre(String name, boolean subscribed) {
        super(name, subscribed);
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    @Override
    public String getType() { return "Oxymètre"; }

    @Override
    public String getStatus() { return spo2 + "% SpO₂"; }
}

