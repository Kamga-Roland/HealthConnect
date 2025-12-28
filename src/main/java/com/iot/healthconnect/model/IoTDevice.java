package com.iot.healthconnect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class IoTDevice {

    protected String name;
    protected boolean subscribed;

    public abstract String getType();
    public abstract String getStatus();

}
