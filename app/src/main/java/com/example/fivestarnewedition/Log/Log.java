package com.example.fivestarnewedition.Log;

public class Log {
    private String Log;
    private String date;

    public Log(String log, String date) {
        Log = log;
        this.date = date;
    }

    public String getLog() {
        return Log;
    }

    public void setLog(String log) {
        Log = log;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
