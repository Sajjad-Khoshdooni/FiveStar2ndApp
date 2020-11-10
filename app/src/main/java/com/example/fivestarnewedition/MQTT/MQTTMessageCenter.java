package com.example.fivestarnewedition.MQTT;

public class MQTTMessageCenter {
    static private String topic = "";
    static private String SE001 = "";
    static private String Out1 = "";
    static private String Temp1 = "";
    static private String Hum1 = "";
    static private String Secur = "";

    public static String getTopic() {
        return topic;
    }

    public static void setTopic(String topic) {
        MQTTMessageCenter.topic = topic;
    }

    public static String getSE001() {
        return SE001;
    }

    public static void setSE001(String SE001) {
        MQTTMessageCenter.SE001 = SE001;
    }

    public static String getOut1() {
        return Out1;
    }

    public static void setOut1(String out1) {
        Out1 = out1;
    }

    public static String getTemp1() {
        return Temp1;
    }

    public static void setTemp1(String temp1) {
        Temp1 = temp1;
    }

    public static String getHum1() {
        return Hum1;
    }

    public static void setHum1(String hum1) {
        Hum1 = hum1;
    }

    public static String getSecur() {
        return Secur;
    }

    public static void setSecur(String secur) {
        Secur = secur;
    }
}
