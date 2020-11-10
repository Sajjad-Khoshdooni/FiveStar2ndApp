package com.example.fivestarnewedition.Constant;

public class Device {
    private String zone;
    private String name;
    private DeviceType deviceType;
    private int delay;
    private Boolean notify;
    private int onIcon;
    private int offIcon;
    private String sendCode;

    public Device(String zone, String name, String deviceType, int delay, Boolean notify, int onIcon, int offIcon,String sendCode) {
        this.zone = zone;
        this.name = name;
        this.deviceType = getType(deviceType);
        this.delay = delay;
        this.notify = notify;
        this.onIcon = onIcon;
        this.offIcon = offIcon;
        this.sendCode = sendCode;
    }

    public Device(String zone, String name, String deviceType, Boolean notify, int onIcon, int offIcon, String sendCode) {
        this.zone = zone;
        this.name = name;
        this.deviceType = getType(deviceType);
        this.delay = 0;
        this.notify = notify;
        this.onIcon = onIcon;
        this.offIcon = offIcon;
        this.sendCode = sendCode;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public int getOnIcon() {
        return onIcon;
    }

    public void setOnIcon(int onIcon) {
        this.onIcon = onIcon;
    }

    public int getOffIcon() {
        return offIcon;
    }

    public void setOffIcon(int offIcon) {
        this.offIcon = offIcon;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    private DeviceType getType(String type){
        if (type.contains("CLOSE")){return DeviceType.NORMALLY_CLOSE;}
        else if (type.contains("OPEN")){return DeviceType.NORMALLY_OPEN;}
        else if (type.contains("TOGGLE")){return DeviceType.TOGGLE;}
        else if (type.contains("DELAY")){return DeviceType.DELAY;}
        else if (type.contains("RESET")){return DeviceType.RESET;}
        else if (type.contains("SET")){return DeviceType.SET;}
        return DeviceType.NORMALLY_CLOSE;
    }
}
