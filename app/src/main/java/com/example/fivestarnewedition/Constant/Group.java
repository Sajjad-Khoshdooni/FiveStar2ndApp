package com.example.fivestarnewedition.Constant;

import java.util.List;

public class Group {
    private String name;
    private List<Device> devices;

    public Group(String name, List<Device> devices) {
        this.name = name;
        this.devices = devices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
