package com.example.fivestarnewedition.Constant;

import java.util.ArrayList;
import java.util.List;

public class Senario {
    private String name;
    private int image;
    private List<Device> devices = new ArrayList<>();

    public Senario(String name, int image, List<Device> devices) {
        this.name = name;
        this.image = image;
        this.devices = devices;
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public void removeDevice(Device device){
        devices.remove(device);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
