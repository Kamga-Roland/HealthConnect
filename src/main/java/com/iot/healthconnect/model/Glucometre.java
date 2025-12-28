package com.iot.healthconnect.model;

import lombok.Data;

@Data
public class Glucometre extends IoTDevice {

    private double glucose; // mg/dL

    public Glucometre(String name, boolean subscribed) {
        super(name, subscribed);
    }

    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }

    public double getGlucose() {
        return glucose;
    }

    @Override
    public String getType() {
        return "Glucomètre";
    }

    @Override
    public String getStatus() {
        return glucose + " mg/dL";
    }
}
