package com.example.fivestarnewedition.MQTTCenter;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Account;
import com.example.fivestarnewedition.Constant.Constant;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTT {
    private AppCompatActivity activity;
    private MqttCallback mqttCallback;
    private MqttAndroidClient client;
    private MqttConnectOptions options;
    private Account account;
    private boolean isConnected = false;

    public MQTT(AppCompatActivity activity, Account account) {
        this.activity = activity;
        this.account = account;
        mqttCallback = new MQTTAnswer();
        initial();
        connect();
    }

    private void initial() {
        try {
            options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(false);
            client = new MqttAndroidClient(activity, account.getURL(), account.getID());
            client.setCallback(mqttCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        if (client != null) {
            try {
                IMqttToken token = client.connect();
                token.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken iMqttToken) {
                        isConnected = true;
                    }

                    @Override
                    public void onFailure(IMqttToken iMqttToken, Throwable throwable) {

                    }
                });
            } catch (Exception e) {
            }
        }
    }

    public void disconnect(){
        if (client != null && client.isConnected()) {
            try {
                client.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public void publish(String code,String topic){
        String topicName = account.getIMEI()+"/" +topic;
        int qos = 2;
        if (client != null && client.isConnected()) {

            MqttMessage message = new MqttMessage(code.getBytes());
            message.setQos(qos);

            try {
                client.publish(topicName, message);
            } catch (MqttException e) {
            }

        }
    }

    public void subscribe(String code){
        if (client != null && client.isConnected()) {
            try {
                client.subscribe(code, 2);
            } catch (MqttException e) {

            }
        }
    }

    public AppCompatActivity getActivity(){
        return activity;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
