package com.example.fivestarnewedition.MQTT;


import androidx.appcompat.app.AppCompatActivity;

import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Log.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;

public class MqttCallbackBus implements MqttCallback {
    AppCompatActivity appCompatActivity ;

    public MqttCallbackBus(AppCompatActivity activity){
        appCompatActivity = activity;
    }

    @Override
    public void connectionLost(Throwable cause) {
        Constant.addLogs(appCompatActivity, new Log("MQTT Connection Lost", Calendar.getInstance().getTime().toString()));
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        updateMessageCenter(topic, message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    private void updateMessageCenter(String topic, MqttMessage message) {
        if (topic.contains("topic")){
            MQTTMessageCenter.setTopic(message.getPayload().toString());
        }
        else if (topic.contains("SE001")){
            MQTTMessageCenter.setSE001(message.getPayload().toString());
        }
        else if (topic.contains("Out1")){
            MQTTMessageCenter.setOut1(message.getPayload().toString());
        }
        else if (topic.contains("Temp1")){
            MQTTMessageCenter.setTemp1(message.getPayload().toString());
        }
        else if (topic.contains("Hum1")){
            MQTTMessageCenter.setHum1(message.getPayload().toString());
        }
        else if (topic.contains("Secure")){
            MQTTMessageCenter.setSecur(message.getPayload().toString());
        }

    }

}