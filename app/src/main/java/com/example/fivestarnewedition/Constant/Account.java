package com.example.fivestarnewedition.Constant;

public class Account {
    private String name;
    private String address;
    private String ID;
    private String IMEI;
    private boolean isMain = false;

    public Account(String name, String address, String ID, String IMEI) {
        this.name = name;
        this.address = address;
        this.ID = ID;
        this.IMEI = IMEI;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL(){
        return address;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
