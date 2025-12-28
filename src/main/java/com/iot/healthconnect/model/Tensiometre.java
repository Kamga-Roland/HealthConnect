package com.iot.healthconnect.model;


import lombok.Data;

@Data
public class Tensiometre extends IoTDevice {

    private int systolic;
    private int diastolic;

    public Tensiometre(String name, boolean subscribed) {
        super(name, subscribed);
    }

    public void setValues(int sys, int dia) {
        this.systolic = sys;
        this.diastolic = dia;
    }

    @Override
    public String getType() {
        return "Tensiomètre";
    }

    @Override
    public String getStatus() {
        return "SYS: " + systolic + " / DIA: " + diastolic;
    }

}
