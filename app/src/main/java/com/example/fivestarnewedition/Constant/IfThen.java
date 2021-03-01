package com.example.fivestarnewedition.Constant;

public class IfThen {
    private Device ifDevice ;
    private Device andDevice;
    private Device thenDevice;
    private boolean active = false;

    public IfThen(Device ifDevice, Device andDevice, Device thenDevice) {
        this.ifDevice = ifDevice;
        this.andDevice = andDevice;
        this.thenDevice = thenDevice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
